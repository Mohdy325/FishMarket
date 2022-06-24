package com.example.fishmarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishmarket.R;
import com.example.fishmarket.activity.SellerDetailsActivity;
import com.example.fishmarket.databinding.InflateHomeListBinding;
import com.example.fishmarket.model.ProductPOJO;
import com.example.fishmarket.view.home.HomeFragment;
import com.example.fishmarket.view.product.ProductFragment;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {
    Context context;
    Fragment fragment;

    public HomeAdapter(Fragment fragment) {
        this.fragment = fragment;
    }
    public HomeAdapter() {
    }
    ArrayList<ProductPOJO> productPOJOS;
    public void updateList(ArrayList<ProductPOJO> arrayList){
        productPOJOS=arrayList;
        notifyDataSetChanged();
    }
    public void setFragment(Fragment fragment){
        this.fragment=fragment;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context==null){
            context= parent.getContext();
        }
      View view= LayoutInflater.from(context).inflate(R.layout.inflate_home_list,parent,false);
        return new HomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
        if (fragment instanceof HomeFragment){
            Log.e("fdsa","Home");
        }else  if (fragment instanceof ProductFragment){
            Log.e("fdsa","Home");
            holder.binding.setModel(productPOJOS.get(position));
          //  holder.binding.tvName.setText();
        }else {
            Log.e("fdsa","Other");

        }
        holder.itemView.setOnClickListener(view -> {
            context.startActivity(new Intent(context, SellerDetailsActivity.class));
        });

    }

    @Override
    public int getItemCount() {
        if (productPOJOS==null || productPOJOS.size()==0) {
            return 6;
        }else {
            return productPOJOS.size();
        }
    }

    public class HomeHolder extends RecyclerView.ViewHolder {
        InflateHomeListBinding binding;
        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            binding=InflateHomeListBinding.bind(itemView);
        }
    }
}
