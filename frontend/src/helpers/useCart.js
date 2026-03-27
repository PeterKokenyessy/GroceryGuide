import { useEffect, useState } from "react";

const CART_KEY = "cart";

export function useCart() {
  const [cart, setCart] = useState(() => {
    if (typeof window === "undefined") return [];
    const stored = localStorage.getItem(CART_KEY);
    return stored ? JSON.parse(stored) : [];
  });

  useEffect(() => {
    localStorage.setItem(CART_KEY, JSON.stringify(cart));
  }, [cart]);

 const addToCart = (product, amount = 1) => {
  setCart(prev => {
    const existing = prev.find(item => item.id === product.id);

    if (existing) {
      return prev.map(item =>
        item.id === product.id
          ? { ...item, amount: item.amount + amount }
          : item
      );
    }

    return [
      ...prev,
      {
        id: product.id,
        name: product.name,
        brand: product.brand?.name,
        category: product.category?.name,
        quantity: product.quantity,
        unit: product.unit,
        checked: false,
        amount,
        prices: product.prices,
      },
    ];
  });
};


  const toggleItem = (id) => {
    setCart((prev) =>
      prev.map((item) =>
        item.id === id ? { ...item, checked: !item.checked } : item
      )
    );
  };

  const removeFromCart = (id) => {
    setCart((prev) => prev.filter((item) => item.id !== id));
  };

  const updateAmount = (id, delta) => {
    setCart((prev) =>
      prev.map((item) =>
        item.id === id
          ? { ...item, amount: Math.max(1, item.amount + delta) }
          : item
      )
    );
  };

  const clearCart = () => setCart([]);

  return {
    cart,
    addToCart,
    removeFromCart,
    updateAmount,
    clearCart,
    toggleItem,
  };
}
