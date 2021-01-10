package com.example.ezyfood.models;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Store {
    public int id;
    public String address;
    public LatLng position;
    public ArrayList<StoreDetail> details;

    public Store(int id, String address, LatLng position) {
        this.id = id;
        this.address = address;
        this.position = position;
        Log.d("ERROR_MARK", "Store: " + position);
        this.details = new ArrayList<>();
    }

    public void addDetail(StoreDetail detail) {
        this.details.add(detail);
    }
}
