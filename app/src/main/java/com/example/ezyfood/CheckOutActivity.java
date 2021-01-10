package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ezyfood.adapters.CartAdapter;
import com.example.ezyfood.adapters.PaymentDetailAdapter;
import com.example.ezyfood.data.Cart;
import com.example.ezyfood.database.Stores;
import com.example.ezyfood.models.CartItem;
import com.example.ezyfood.utils.PriceFormatter;

public class CheckOutActivity extends AppCompatActivity {

    private RecyclerView rvOrders;
    private Button btnBackHome;
    private TextView tvTotal;
    private int storeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        storeId = getIntent().getIntExtra("store_id", -1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initComponents();
    }

    private void initComponents() {
        rvOrders = findViewById(R.id.rv_orders);
        btnBackHome = findViewById(R.id.btn_back_home);

        rvOrders.setLayoutManager(new LinearLayoutManager(this));
        rvOrders.setHasFixedSize(true);
        rvOrders.setAdapter(new PaymentDetailAdapter(Cart.getCartItems()));

        tvTotal = findViewById(R.id.tv_total);
        tvTotal.setText(PriceFormatter.format(Cart.getTotal()));

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Stores(CheckOutActivity.this).finalCheckout(storeId);
                Cart.clearCart();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Cart.clearCart();
    }
}
