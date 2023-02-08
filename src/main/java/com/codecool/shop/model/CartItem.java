package com.codecool.shop.model;

//import javax.persistence.ManyToAny;

public class CartItem {

    private int id;
    private int quantity;

    //@ManyToAny(fetch = FetchType.EAGER)
    private Product product;

   /* public CartItem() {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    } */

    public int getId() {
        return id;
    }

    public int getQuantity(){
        return quantity;
    }

    public Product getProduct(){
        return product;
    }

    // co z tym nullem ?
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
