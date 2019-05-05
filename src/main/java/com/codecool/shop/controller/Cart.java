package com.codecool.shop.controller;

import java.util.ArrayList;

public class Cart {


    private static Cart instance = null;

    private Cart(){}

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }


    ArrayList<LineItem> productsInCart = new ArrayList<>();

    float sumOfCart = 0;
    int itemsTotal = 0;


    public ArrayList<LineItem> getProductsInCart() {
        return productsInCart;
    }

    public float getSumOfCart() {
        return sumOfCart;
    }

    public int getItemsTotal() { return itemsTotal; }

    public void setItemsTotal(int increase) {
        this.itemsTotal += increase;
    }

    public void recalculateItemsTotal() {
        int tempTotal = 0;
        for (LineItem item : getProductsInCart()) {
            tempTotal += item.getQuantity();
        }
        itemsTotal = tempTotal;
    }

    public void addToCart(LineItem item) {

        productsInCart.add(item);
        sumOfCart += item.getProduct().getDefaultPrice();
        itemsTotal += item.getQuantity();
    }

    public void removeFromCart(LineItem item){
        productsInCart.remove(item);
    }

    @Override
    public String toString() {
        String cartString = "";
        for (LineItem item : productsInCart) {
            cartString += "\nThe quantity of " + item.getProduct().getName() + " in the cart is " + item.getQuantity() + ".\n";
        }
        return cartString;
    }
}
