package com.example.ezyfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ezyfood.data.Cart;
import com.example.ezyfood.models.CartItem;
import com.example.ezyfood.models.Item;
import com.example.ezyfood.utils.PriceFormatter;
import com.example.ezyfood.viewholders.ItemViewHolder;

public class ItemDetailActivity extends AppCompatActivity {

    Item item;
    ImageView ivItem;
    TextView tvName, tvPrice;
    EditText etQty;
    Button btnAddCart, btnOrderMore;
    ImageButton ibPlus, ibMinus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        this.item = (Item) getIntent().getSerializableExtra(ItemViewHolder.ITEM);
        this.initComponents();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initComponents() {
        ivItem = findViewById(R.id.iv_item);
        tvName = findViewById(R.id.tv_name);
        tvPrice = findViewById(R.id.tv_price);
        etQty = findViewById(R.id.et_qty);
        btnAddCart = findViewById(R.id.btn_add_cart);
        btnOrderMore = findViewById(R.id.btn_order_more);
        ibPlus = findViewById(R.id.ib_plus);
        ibMinus = findViewById(R.id.ib_minus);

        this.fillContent();
    }

    private void updateButtonAndQuantityFromCart() {
        CartItem cartItem = Cart.find(item);
        if(cartItem != null) {
            etQty.setText(cartItem.getQty() + "");
            btnAddCart.setText("Update Cart");
        }
    }

    private void fillContent() {
        ivItem.setImageResource(item.getImageId());

        tvName.setText(item.getName());
        tvPrice.setText(PriceFormatter.format(item.getPrice()));

        updateButtonAndQuantityFromCart();

        ibPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    etQty.setText(Integer.parseInt(etQty.getText().toString()) + 1 + "");
                } catch (Exception e) {
                    etQty.setText(0 + "");
                }
            }
        });

        ibMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int value = Integer.parseInt(etQty.getText().toString()) - 1;
                    if(value < 1)   value = 1;
                    etQty.setText(value + "");
                } catch (Exception e) {
                    etQty.setText(0 + "");
                }
            }
        });

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int qty = Integer.parseInt(etQty.getText().toString());
                    Cart.addItem(item, qty);
                    Toast.makeText(ItemDetailActivity.this, "Added to cart!", Toast.LENGTH_SHORT).show();
                    updateButtonAndQuantityFromCart();
                    ItemDetailActivity.this.finish();
                } catch(Exception e) {
                    Toast.makeText(ItemDetailActivity.this, "Quantity not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnOrderMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
