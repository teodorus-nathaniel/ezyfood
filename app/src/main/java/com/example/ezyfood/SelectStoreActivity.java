package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.ezyfood.adapters.CartAdapter;
import com.example.ezyfood.adapters.StoreAdapter;
import com.example.ezyfood.data.Cart;
import com.example.ezyfood.database.Stores;
import com.example.ezyfood.models.Store;

import java.util.ArrayList;

public class SelectStoreActivity extends AppCompatActivity {

    RecyclerView rvStores;
    ArrayList<Integer> nearest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_store);

        nearest = getIntent().getIntegerArrayListExtra("nearest");
        this.initComponents();
    }

    public void initComponents() {
        Stores db = new Stores(this);
        ArrayList<Store> stores = new ArrayList<>();
        for (Integer id : nearest) {
            try {
                ArrayList<Store> foundStores = db.getStores(id);
                if(foundStores.size() > 0) {
                    stores.add(foundStores.get(0));
                }
            } catch(Exception e) {

            }
        }

        rvStores = findViewById(R.id.rv_stores);

        rvStores.setLayoutManager(new LinearLayoutManager(this));
        rvStores.setHasFixedSize(true);
        rvStores.setAdapter(new StoreAdapter(this, stores));
    }
}
