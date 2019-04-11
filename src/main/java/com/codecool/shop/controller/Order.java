package com.codecool.shop.controller;

import java.util.ArrayList;

public class Order {

    private ArrayList<LineItem> itemList;
    private int sum = 0;
    private String billingAddress;
    private String shoppingAdress;
    private String phone;
    private String name;
    private String email;

    public Order(ArrayList<LineItem> itemList) {
        this.itemList = itemList;
        for (LineItem item : itemList) {
            sum += item.addUpPrice;
        }
    }
    public ArrayList<LineItem> getItemList(){
        return itemList;
    }

    public void saveData(String billingAddress, String shoppingAdress, String phone, String name, String email) {
        this.billingAddress = billingAddress;
        this.shoppingAdress = shoppingAdress;
        this.phone = phone;
        this.name = name;
        this.email = email;
    }
}
