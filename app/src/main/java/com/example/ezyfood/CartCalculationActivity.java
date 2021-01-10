package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.ezyfood.database.Stores;

import java.util.ArrayList;

public class CartCalculationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_calculation);

        int id = getIntent().getIntExtra("store_id", -1);
        if(id == -1) {
            Intent i = new Intent();
            ArrayList<String> messages = new ArrayList<>();
            messages.add("Store not found");
            i.putStringArrayListExtra("messages", messages);
            startActivity(i);

            this.finish();

            return;
        }

        Stores stores = new Stores(this);
        ArrayList<String> messages = stores.checkout(id);
        if(messages.size() == 0) {
            Intent i = new Intent(this, CheckOutActivity.class);
            i.putExtra("store_id", id);
            startActivity(i);
        } else {
            Intent i = new Intent(this, FailOrderActivity.class);
            i.putStringArrayListExtra("messages", messages);
            startActivity(i);
        }
        this.finish();
    }
}
