package com.controller;

import com.entities.ProductEntity;

public class CartItemBean {

    private ProductEntity productEntity;
    private String number;
    private String description;
    private String image;
    private double price;
    private int quantity;
    private double TotalCost;

    public CartItemBean(){

    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(double TotalCost) {
        this.TotalCost = TotalCost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
