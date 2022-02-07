package com.example.bolteco.ModeClass;

import java.io.Serializable;

public class Feature_mode_class implements Serializable {
    String productName;
    Double price;
    String productImage;
    String description;

    public Feature_mode_class() {}

    public Feature_mode_class(String productName, Double price, String productImage,String description) {
        this.productName = productName;
        this.price = price;
        this.productImage = productImage;
        this.description= description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
