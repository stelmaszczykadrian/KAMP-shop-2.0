package com.codecool.shop.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private int totalPrice;
    private HashMap<Product, Integer> cart;

    public Cart(){
        this.cart = new HashMap<>();
    }

    public void addProductToCart(Product product) {
        if (cart.containsKey(product)) {
            cart.replace(product, cart.get(product) + 1);
        } else {
            cart.put(product, 1);
        }
    }

    public void deleteProductFromCart(Product product) {
            if(cart.containsKey(product)) {
                if(cart.get(product) > 1)
                    cart.replace(product, cart.get(product) - 1);

                else if (cart.get(product) == 1) cart.remove(product);
            }
        }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();

            CartItem cartItem = new CartItem(product, quantity);
            totalPrice += cartItem.getPrice() * quantity;
        }
        return totalPrice;
    }

    public void checkout(){
    }
    public HashMap<Product, Integer> getCartItems(){
        return cart;
    }

    public void setCartItems(HashMap<Product, Integer> cart) {
        this.cart = cart;
    }

}
