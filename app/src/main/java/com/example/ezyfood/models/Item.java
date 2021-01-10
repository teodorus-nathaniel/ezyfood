package com.example.ezyfood.models;

import java.io.Serializable;

public class Item implements Serializable {
    private int imageId;
    private String name;
    private int price;
    private String category;
    private int id;

    public Item(int id, String category, int imageResource, String name, int price) {
        this.id = id;
        this.category = category;
        this.imageId = imageResource;
        this.name = name;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
