package com.example.ezyfood.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezyfood.R;
import com.example.ezyfood.models.CartItem;
import com.example.ezyfood.utils.PriceFormatter;

public class PaymentDetailViewHolder extends RecyclerView.ViewHolder {

    TextView tvName, tvPrice, tvSubtotal;

    public PaymentDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name);
        tvPrice = itemView.findViewById(R.id.tv_price);
        tvSubtotal = itemView.findViewById(R.id.tv_subtotal);
    }

    public void setData(CartItem cartItem) {
        tvName.setText(cartItem.getItem().getName());
        tvPrice.setText(PriceFormatter.format(cartItem.getItem().getPrice()) + " * " + cartItem.getQty());
        tvSubtotal.setText(PriceFormatter.format(cartItem.getItem().getPrice() * cartItem.getQty()));
    }
}
