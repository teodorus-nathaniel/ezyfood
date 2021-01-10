package com.example.ezyfood.models;

public class StoreDetail {
    public int stock;
    public Item item;

    public StoreDetail(Item item, int stock) {
        this.item = item;
        this.stock = stock;
    }
}
