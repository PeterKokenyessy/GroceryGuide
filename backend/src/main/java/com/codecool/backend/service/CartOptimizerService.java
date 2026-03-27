package com.codecool.backend.service;

import com.codecool.backend.dto.CartDto;

import com.codecool.backend.dto.CartStoresDto;
import com.codecool.backend.mapper.CartItemMapper;

import com.codecool.backend.model.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CartOptimizerService {

    private final CartItemMapper cartItemMapper;

    public CartOptimizerService(CartItemMapper cartItemMapper) {
        this.cartItemMapper = cartItemMapper;
    }

    public CartDto optimizeCart (Cart cart) {
        Set<CartItem> remaining = new HashSet<>(cart.getItems());


        Map<String,StoreTmp> storeMap = new HashMap<>();

        double totalPrice = 0;

        Map<String,Store> stores = extractStores(cart);

        while (!remaining.isEmpty()) {

            StoreEvaluation best = findBestStore(stores,remaining);

            if(best.store() == null) {
                break;
            }
            totalPrice += buyFromStore(
                    best.store(),
                    remaining,
                    storeMap
                    );
        }
        double savedMoney = calculateExpensivePossible(cart) - totalPrice;

        List<CartStoresDto> result = storeMap.values().stream()
                .map(acc -> new CartStoresDto(
                        acc.getStoreName(),
                        acc.getStoreTotal(),
                        stores.get(acc.getStoreName()).getColor(),
                        acc.getItems()
                )).toList();

        return new CartDto(totalPrice,savedMoney,result);

    }

    private double buyFromStore(Store store, Set<CartItem> remaining, Map<String,StoreTmp> storeMap) {

        double total = 0;


        StoreTmp acc = storeMap.computeIfAbsent(store.getName(), StoreTmp::new);

        Iterator<CartItem> iterator = remaining.iterator();

        while (iterator.hasNext()){

            CartItem item = iterator.next();

            Optional<Price> price = findPrice(item.getProduct(),store);

            if(price.isPresent()){

                double itemTotal = price.get().getPrice() * item.getQuantity();

                total += itemTotal;

                acc.getItems().add(cartItemMapper.toDto(item));
                acc.storeTotal += itemTotal;

                iterator.remove();
            }
        }
        return total;
    }

    private StoreEvaluation findBestStore(Map<String,Store> stores, Set<CartItem> remaining) {
        Store beststore = null;
        int bestCoverage = 0;
        double bestPrice = Double.MAX_VALUE;

        for(Store store : stores.values()) {
            int coverage = 0;
            double priceSum = 0;

            for(CartItem item : remaining) {

                Optional<Price> price = findPrice(item.getProduct(),store);

                if(price.isPresent()){
                    coverage++;
                    priceSum += price.get().getPrice() * item.getQuantity();
                }
            }
            if(coverage > bestCoverage || (coverage == bestCoverage && priceSum < bestPrice)) {
                beststore = store;
                bestCoverage = coverage;
                bestPrice = priceSum;
            }
        }
        return new StoreEvaluation(beststore,bestCoverage,bestPrice);
    }

    private Optional<Price> findPrice(Product product, Store store) {
        return product.getPrices()
                .stream()
                .filter(p -> p.getStore().equals(store))
                .findFirst();
    }

    private Map<String, Store> extractStores(Cart cart) {
        return cart.getItems().stream()
                .flatMap(item -> item.getProduct().getPrices().stream())
                .map(Price::getStore)
                .collect(Collectors.toMap(
                        Store::getName,   // kulcs
                        store -> store,   // érték
                        (existing, replacement) -> existing // duplikátum kezelés
                ));
    }

    private double calculateExpensivePossible(Cart cart) {
        double total = 0;

        for (CartItem item : cart.getItems()) {
            double maxPrice = 0;

            for (Price price : item.getProduct().getPrices()) {

                if (price.getPrice() > maxPrice) {
                    maxPrice = price.getPrice();
                }
            }
            total += maxPrice * item.getQuantity();
        }
        return total;
    }
}
