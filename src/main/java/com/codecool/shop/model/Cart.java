package com.codecool.shop.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> cart = new HashMap<>();

    public void addItemToCart(int quantity){

    }

    public void deleteItemFromCart(int quantity){

    }

    public void checkout(){

    }


    public Map<Product, Integer> getCart() {
        return cart;
    }


}
