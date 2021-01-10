package com.example.ezyfood.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezyfood.R;
import com.example.ezyfood.models.CartItem;
import com.example.ezyfood.viewholders.CartViewHolder;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private ArrayList<CartItem> cartItems;

    public CartAdapter(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_viewholder, parent, false);
        CartViewHolder vh = new CartViewHolder(v, this);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.setCartItem(cartItems.get(position));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
