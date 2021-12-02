package com.example.foodorderapp.Models;

public class OrdersModel {

    int orderImage;

    public OrdersModel(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    int totalAmount;

    public OrdersModel() { }

    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int orderImage) {
        this.orderImage = orderImage;
    }

    public String getSoldItemName() {
        return soldItemName;
    }

    public void setSoldItemName(String soldItemName) {
        this.soldItemName = soldItemName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public OrdersModel(int orderImage, String soldItemName, String orderNumber, String price) {
        this.orderImage = orderImage;
        this.soldItemName = soldItemName;
        this.orderNumber = orderNumber;
        this.price = price;
    }

    String soldItemName, orderNumber, price;
}
