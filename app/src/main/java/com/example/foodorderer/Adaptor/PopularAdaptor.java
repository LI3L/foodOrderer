package com.example.foodorderer.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorderer.Domain.FoodDomain;
import com.example.foodorderer.R;

import java.util.ArrayList;

public class PopularAdaptor extends  RecyclerView.Adapter<PopularAdaptor.PopularViewHolder>{
    ArrayList<FoodDomain> popularFood;

    public PopularAdaptor(ArrayList<FoodDomain> popularFood) {
        this.popularFood = popularFood;
    }
    @Override
    public PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
        return new PopularViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        holder.title.setText(popularFood.get(position).getTitle());
        holder.fee.setText(String.valueOf(popularFood.get(position).getFee()));
        String picUrl = "";

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popularFood.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return popularFood.size();
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder {
        TextView title,fee,addBtn;
        ImageView pic;
        ConstraintLayout mainLayout;
        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            fee = itemView.findViewById(R.id.fee);
            pic = itemView.findViewById(R.id.pic);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}
