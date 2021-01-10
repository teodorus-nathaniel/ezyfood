package com.example.ezyfood.viewholders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezyfood.R;
import com.example.ezyfood.models.Item;
import com.example.ezyfood.utils.PriceFormatter;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    public static final String ITEM = "ITEM";

    private Context ctx;
    private Class target;

    private CardView card;
    private ImageView image;
    private TextView name, price;

    public ItemViewHolder(Context ctx, View itemView, Class target) {
        super(itemView);

        this.ctx = ctx;
        this.target = target;
        card = itemView.findViewById(R.id.cv_item);
        image = itemView.findViewById(R.id.iv_item);
        name = itemView.findViewById(R.id.tv_name);
        price = itemView.findViewById(R.id.tv_price);
    }

    public void setItem(final Item item) {
        image.setImageResource(item.getImageId());
        name.setText(item.getName());
        price.setText(PriceFormatter.format(item.getPrice()));

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, target);
                i.putExtra(ITEM, item);
                ctx.startActivity(i);
            }
        });
    }
}
