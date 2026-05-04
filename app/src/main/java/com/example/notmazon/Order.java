package com.example.notmazon;
// Order data model
public class Order {
    String id;
    String date;
    double price;

    public Order(String id, String date, double price) {
        this.id = id;
        this.date = date;
        this.price = price;
    }
}