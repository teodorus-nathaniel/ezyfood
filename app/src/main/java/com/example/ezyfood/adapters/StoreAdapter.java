package com.example.ezyfood.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezyfood.R;
import com.example.ezyfood.models.CartItem;
import com.example.ezyfood.models.Store;
import com.example.ezyfood.viewholders.CartViewHolder;
import com.example.ezyfood.viewholders.StoreViewHolder;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreViewHolder>  {
    private ArrayList<Store> stores;
    Context c;

    public StoreAdapter(Context c, ArrayList<Store> stores) {
        this.c = c;
        this.stores = stores;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_viewholder, parent, false);
        StoreViewHolder vh = new StoreViewHolder(v, c);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        Store store = stores.get(position);
        holder.setData(store.id, store.address);
    }

    @Override
    public int getItemCount() {
        Log.d("CHECKING_", "getItemCount: " + stores.size());
        return stores.size();
    }
}
