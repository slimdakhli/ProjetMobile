package com.example.miniproject.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.miniproject.Interface.ItemClickListener;
import com.example.miniproject.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView textOrderId, textOrderStatus, textOrderAddress, textOrderPhone ;
    private ItemClickListener itemClickListener;
    public OrderViewHolder(View itemView){
        super(itemView);
        textOrderId = (TextView)itemView.findViewById(R.id.order_id);
        textOrderStatus = (TextView)itemView.findViewById(R.id.order_status);
        textOrderAddress = (TextView)itemView.findViewById(R.id.order_address);
        textOrderPhone = (TextView)itemView.findViewById(R.id.order_phone);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);

    }
}
