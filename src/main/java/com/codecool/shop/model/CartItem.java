package com.codecool.shop.model;

public class CartItem {

    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }
    public int getQuantity(){
        return quantity;
    }

    public Product getProduct(){
        return product;
    }

    public Product getProductById(int id) {
        if(product.getId() == id)
            return product;
        return null;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }



}
