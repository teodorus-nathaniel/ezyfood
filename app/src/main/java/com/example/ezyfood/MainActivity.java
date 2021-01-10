package com.example.ezyfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ezyfood.enums.ItemType;

public class MainActivity extends AppCompatActivity {

    public static final String ITEM_TYPE = "ITEM_TYPE";

    ImageButton btnFood, btnDrink, btnSnack, btnTopUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initComponents();
        this.initEventListeners();
    }

    private void initComponents() {
        btnFood = findViewById(R.id.btn_food);
        btnDrink = findViewById(R.id.btn_drink);
        btnSnack = findViewById(R.id.btn_snack);
        btnTopUp = findViewById(R.id.btn_top_up);
    }

    private void initEventListeners() {
        btnFood.setOnClickListener(new ButtonClickListener(this, ItemListActivity.class, ItemType.FOOD));
        btnDrink.setOnClickListener(new ButtonClickListener(this, ItemListActivity.class, ItemType.DRINK));
        btnSnack.setOnClickListener(new ButtonClickListener(this, ItemListActivity.class, ItemType.SNACK));
        btnTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Feature not implemented yet!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_cart:
                startActivity(new Intent(this, CartActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class ButtonClickListener implements View.OnClickListener {

        private ItemType type;
        private Class target;
        private Context ctx;

        public ButtonClickListener(Context ctx, Class target, ItemType type) {
            this.ctx = ctx;
            this.target = target;
            this.type = type;
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(this.ctx, this.target);
            i.putExtra(ITEM_TYPE, this.type);
            startActivity(i);
        }
    }
}
