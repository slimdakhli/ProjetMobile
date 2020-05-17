package com.example.miniproject.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.miniproject.Interface.ItemClickListener;
import com.example.miniproject.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView textMenuName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;
    public  MenuViewHolder(View itemView){
        super(itemView );
        textMenuName = (TextView)itemView.findViewById(R.id.menu_name);
        imageView = (ImageView)itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void  onClick(View view){
        itemClickListener.onClick(view, getAdapterPosition(),false);

    }
}
