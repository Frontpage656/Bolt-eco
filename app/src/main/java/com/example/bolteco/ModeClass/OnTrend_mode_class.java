package com.example.bolteco.ModeClass;

import java.io.Serializable;

public class OnTrend_mode_class implements Serializable {
    String name,manufacture_name,description,image_url;
    Double num_buyers,price;
    Integer likes_count;


    public OnTrend_mode_class() {}

    public OnTrend_mode_class(String name, String manufacture_name, String description, String image_url, Double num_buyers, Integer likes_count, Double price) {
        this.name = name;
        this.manufacture_name = manufacture_name;
        this.description = description;
        this.image_url = image_url;
        this.price = price;
        this.likes_count = likes_count;
        this.num_buyers = num_buyers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacture_name() {
        return manufacture_name;
    }

    public void setManufacture_name(String manufacture_name) {
        this.manufacture_name = manufacture_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Double getNum_buyers() {
        return num_buyers;
    }

    public void setNum_buyers(Double num_buyers) {
        this.num_buyers = num_buyers;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(Integer likes_count) {
        this.likes_count = likes_count;
    }
}
