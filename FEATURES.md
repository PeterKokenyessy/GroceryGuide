# Project features

A collection of planned features for the price tracking and comparison project.  
This document provides an overview of the core functionalities, described using user stories and acceptance criteria.

## Table of Contents

- [Fetch product price from Tesco Online store](#fetch-product-price-from-tesco-online-store)
- [Fetch product price from another store (e.g. Auchan)](#fetch-product-price-from-another-store-eg-auchan)
- [Compare product prices across stores](#compare-product-prices-across-stores)
- [Create shopping list](#create-a-shopping-list)
- [Recommend the cheapest store for the entire shopping list](#recommend-the-cheapest-store-for-the-entire-shopping-list)

> ## Fetch product price from Tesco Online store
***User story***

As a user, I want to retrieve the current price of a specific product from the Tesco online store so that I can quickly check how much it costs without browsing the website manually.

***Acceptance criteria***
- The system retrieves product data using web scraping
- The response includes product name and its current price
- If the product cannot be found, a clear error message is returned


> ## Fetch product price from another store (e.g. Auchan)
***User story***

As a user, I want to retrieve the current price of a specific product from another online store (e.g. Auchan) so that I can quickly check how much it costs without browsing the website manually.

***Acceptance criteria***
- The system retrieves product data using web scraping
- The response includes product name and its current price
- If the product cannot be foundnd, a clear error message is returned

> ## Compare product prices across stores

***User story***

As a user, I want to compare the price of a specific product across multiple online stores so that I can easily find the best available offer.

***Acceptance criteria***
- The system retrieves product data from all supported stores (e.g. Tesco, Auchan)

- The comparison includes:
  - product name  
  - price

- The system returns a unified JSON response containing the results from each store

- If one of the stores fails to provide data, the system still returns the results from the others

- Errors from individual stores are included in the response in a structured way

- The comparison logic correctly identifies the lowest price

- The system handles differences in product naming or formatting between stores

> ## Create a shopping list
***User story***

As a user, I want to create and manage a shopping list so that I can keep track of the products I plan to buy.

***Acceptance criteria*** 
- The user can create a new shopping list

- The user can add products to the shopping list

- The user can remove products from the shopping list

- The shopping list stores:
  - product name
  - optional quantity

- The system persists the shopping list between sessions

- The user can view the full shopping list at any time

- If a product is added multiple times, the system handles it (increments quantity)

> ## Recommend the cheapest store for the entire shopping list

***User story***

As a user, I want to see the total cost of my entire shopping list at each supported store, so that I can easily identify where the full list is the cheapest and how much I can save.

***Acceptance criteria***
- The system calculates the total price of the shopping list for each supported store

- The result includes, for every store:
  - total price of the full list  
  - list of items with their individual prices  
  - indication if an item is unavailable (and how it affects the total)

- The system highlights:
  - which store is the cheapest
  - how much cheaper it is compared to the other option

- If a store cannot provide data (e.g. scraping error), the system still returns results for the others

- The response is returned in a structured JSON format

- The system handles missing or mismatched product names

