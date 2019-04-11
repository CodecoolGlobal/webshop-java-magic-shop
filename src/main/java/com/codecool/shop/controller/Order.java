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
    private String method;
    private String card_num;

    public Order(ArrayList<LineItem> itemList) {
        this.itemList = itemList;
        for (LineItem item : itemList) {
            sum += item.addUpPrice;
        }
    }
    public ArrayList<LineItem> getItemList(){
        return itemList;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public void saveData(String billingAddress, String shoppingAdress, String phone, String name, String email) {
        this.billingAddress = billingAddress;
        this.shoppingAdress = shoppingAdress;
        this.phone = phone;
        this.name = name;
        this.email = email;
    }
}
