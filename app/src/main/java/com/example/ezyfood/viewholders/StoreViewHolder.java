package com.example.ezyfood.viewholders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezyfood.CartCalculationActivity;
import com.example.ezyfood.R;
import com.example.ezyfood.data.Cart;
import com.example.ezyfood.models.CartItem;
import com.example.ezyfood.utils.PriceFormatter;

public class StoreViewHolder extends RecyclerView.ViewHolder {

    Context c;
    TextView tvAddress;
    Button btnSelect;

    public StoreViewHolder(@NonNull View itemView, final Context c) {
        super(itemView);

        this.c = c;
        tvAddress = itemView.findViewById(R.id.tv_address);
        btnSelect = itemView.findViewById(R.id.btn_select);
    }

    public void setData(final int id, final String address) {
        tvAddress.setText(address);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c, CartCalculationActivity.class);
                i.putExtra("store_id", id);
                c.startActivity(i);
            }
        });
    }

}
