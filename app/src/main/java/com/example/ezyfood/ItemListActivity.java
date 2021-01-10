package com.example.ezyfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ezyfood.adapters.ItemAdapter;
import com.example.ezyfood.database.Products;
import com.example.ezyfood.enums.ItemType;
import com.example.ezyfood.models.Item;

import java.util.ArrayList;

public class ItemListActivity extends AppCompatActivity {

    private ItemType type;
    private ArrayList<Item> displayedItems;

    private RecyclerView rvItems;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        this.getExtraData();
        this.initComponents();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initComponents() {
        tvTitle = findViewById(R.id.tv_title);
        String title = "";
        switch (type) {
            case FOOD:
                title = "Foods";
                break;
            case DRINK:
                title = "Drinks";
                break;
            case SNACK:
                title = "Snacks";
                break;
        }
        tvTitle.setText(title);

        rvItems = findViewById(R.id.rv_items);

        rvItems.setLayoutManager(new LinearLayoutManager(this));
        rvItems.setHasFixedSize(true);
        rvItems.setAdapter(new ItemAdapter(displayedItems));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.menu_cart:
                startActivity(new Intent(this, CartActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getExtraData() {
        type = (ItemType) this.getIntent().getSerializableExtra(MainActivity.ITEM_TYPE);
        Products products = new Products(this);
        try {
            switch(type) {
                case FOOD:
                    displayedItems = products.getFoods();
                    break;
                case DRINK:
                    displayedItems = products.getDrinks();
                    break;
                case SNACK:
                    displayedItems = products.getSnacks();
                    break;
            }
        } catch(Exception e) {
        }
    }

}
