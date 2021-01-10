package com.example.ezyfood.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezyfood.ItemDetailActivity;
import com.example.ezyfood.R;
import com.example.ezyfood.models.CartItem;
import com.example.ezyfood.viewholders.ItemViewHolder;
import com.example.ezyfood.viewholders.PaymentDetailViewHolder;

import java.util.ArrayList;

public class PaymentDetailAdapter extends RecyclerView.Adapter<PaymentDetailViewHolder> {

    private ArrayList<CartItem> dataset;

    public PaymentDetailAdapter(ArrayList<CartItem> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public PaymentDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_detail_viewholder, parent, false);
        PaymentDetailViewHolder vh = new PaymentDetailViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentDetailViewHolder holder, int position) {
        holder.setData(dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
