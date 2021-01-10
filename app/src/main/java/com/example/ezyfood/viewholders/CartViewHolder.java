package com.example.ezyfood.viewholders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezyfood.R;
import com.example.ezyfood.data.Cart;
import com.example.ezyfood.models.CartItem;
import com.example.ezyfood.utils.PriceFormatter;

public class CartViewHolder extends RecyclerView.ViewHolder {

    RecyclerView.Adapter adapter;

    ImageView ivCart;
    TextView tvName, tvPrice, tvSubtotal;
    ImageButton ibDelete;

    public CartViewHolder(@NonNull View itemView, RecyclerView.Adapter adapter) {
        super(itemView);
        this.adapter = adapter;
    }

    public void setCartItem(final CartItem cartItem) {
        ivCart = itemView.findViewById(R.id.iv_item);
        tvName = itemView.findViewById(R.id.tv_name);
        tvPrice = itemView.findViewById(R.id.tv_price);
        tvSubtotal = itemView.findViewById(R.id.tv_subtotal);
        ibDelete = itemView.findViewById(R.id.ib_delete);

        ivCart.setImageResource(cartItem.getItem().getImageId());
        tvName.setText(cartItem.getItem().getName());
        tvPrice.setText(PriceFormatter.format(cartItem.getItem().getPrice()) + " * " + cartItem.getQty());
        tvSubtotal.setText(PriceFormatter.format(cartItem.getItem().getPrice() * cartItem.getQty()));

        ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart.removeItem(cartItem.getItem());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
