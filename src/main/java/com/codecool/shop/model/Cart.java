package com.codecool.shop.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int totalPrice;

    private int itemsNumber;

    // Set czy mapa?
    private Set<CartItem> cartItems = new HashSet<>();

    public void addItemToCart(int quantity){
    }

    public void deleteItemFromCart(int quantity){
    }

    public void checkout(){
    }

    public Set<CartItem> getCartItems(){
        return cartItems;
    }

}
