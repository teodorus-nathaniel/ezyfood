package com.example.ezyfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ezyfood.adapters.CartAdapter;
import com.example.ezyfood.data.Cart;
import com.example.ezyfood.utils.PriceFormatter;

public class CartActivity extends AppCompatActivity {

    RecyclerView rvCart;
    TextView tvNoItem, tvTotal;
    Button btnAuto, btnMap;
    
    private final static int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initComponents();
        checkItemCount();
        updateTotal();

        Cart.observe(new Runnable() {
            @Override
            public void run() {
                checkItemCount();
                updateTotal();
            }
        });
    }

    private void updateTotal() {
        tvTotal.setText(PriceFormatter.format(Cart.getTotal()));
    }

    @Override
    protected void onStop() {
        super.onStop();
        Cart.unObserve();
    }

    private void checkItemCount() {
        if(Cart.getCartItems().size() == 0) {
            tvNoItem.setVisibility(View.VISIBLE);
        } else {
            tvNoItem.setVisibility(View.GONE);
        }
    }

    private void initComponents() {
        rvCart = findViewById(R.id.rv_cart);

        rvCart.setLayoutManager(new LinearLayoutManager(this));
        rvCart.setHasFixedSize(true);
        rvCart.setAdapter(new CartAdapter(Cart.getCartItems()));

        tvTotal = findViewById(R.id.tv_total);
        tvNoItem = findViewById(R.id.tv_no_item);
        btnAuto = findViewById(R.id.btn_auto);
        btnMap = findViewById(R.id.btn_select_store);

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cart.getCartItems().size() == 0) {
                    Toast.makeText(CartActivity.this, "No item in your cart!", Toast.LENGTH_SHORT).show();
                } else {
                    if(!gotoMaps(true))
                        ActivityCompat.requestPermissions( CartActivity.this,
                            new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
                }
            }
        });

        btnAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cart.getCartItems().size() == 0) {
                    Toast.makeText(CartActivity.this, "No item in your cart!", Toast.LENGTH_SHORT).show();
                } else {
                    if(!gotoMaps(false))
                        ActivityCompat.requestPermissions( CartActivity.this,
                                new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
                }
            }
        });
    }

    private boolean gotoMaps(boolean auto) {
        if (ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Intent i = new Intent(CartActivity.this, MapsActivity.class);
            if(auto) {
                i.putExtra("auto", 1);
            }
            startActivity(i);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
        case PERMISSION_REQUEST_CODE:
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                gotoMaps(false);
            } else {
                Toast.makeText(this, "To order, you need to allow permission access.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
