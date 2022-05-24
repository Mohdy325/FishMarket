package com.example.fishmarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishmarket.R;
import com.example.fishmarket.activity.SellerDetailsActivity;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {
    Context context;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(context).inflate(R.layout.inflate_home_list,parent,false);
        return new HomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
        holder.itemView.setOnClickListener(view -> {
            context.startActivity(new Intent(context, SellerDetailsActivity.class));
        });

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class HomeHolder extends RecyclerView.ViewHolder {
        public HomeHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
