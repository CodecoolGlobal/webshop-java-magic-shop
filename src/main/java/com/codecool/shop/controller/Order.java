package com.codecool.shop.controller;

import java.util.ArrayList;

public class Order {

    ArrayList<LineItem> itemList;
    int sum = 0;
    String billingAddress;
    String shoppingAdress;
    String phone;
    String name;
    String email;

    public Order(ArrayList<LineItem> itemList) {
        this.itemList = itemList;
        for (LineItem item : itemList) {
            sum += item.addUpPrice;
        }
    }

    public void saveData(String billingAddress, String shoppingAdress, String phone, String name, String email) {
        this.billingAddress = billingAddress;
        this.shoppingAdress = shoppingAdress;
        this.phone = phone;
        this.name = name;
        this.email = email;
    }
}
