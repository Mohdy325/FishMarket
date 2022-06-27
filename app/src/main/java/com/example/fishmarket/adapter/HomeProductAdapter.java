package com.example.fishmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishmarket.R;
import com.example.fishmarket.databinding.InflateHomeListBinding;
import com.example.fishmarket.databinding.InflateHomeProductsBinding;
import com.example.fishmarket.model.ProductPOJO;

import java.util.ArrayList;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.HomeProductHolder> {
    Context context;
    ArrayList<ProductPOJO> productPOJOS=new ArrayList<>();

    public void updateList(ArrayList<ProductPOJO> arrayList){
        productPOJOS=arrayList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public HomeProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context==null){
            context= parent.getContext();
        }

        View view= LayoutInflater.from(context).inflate(R.layout.inflate_home_products,parent,false);
        return new HomeProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProductHolder holder, int position) {
        holder.binding.setModel(productPOJOS.get(position)  );
    }

    @Override
    public int getItemCount() {
       /* if (productPOJOS==null || productPOJOS.size()==0) {
            return 6;
        }else {*/
            return productPOJOS.size();
      //  }
    }

    public class HomeProductHolder extends RecyclerView.ViewHolder {
        InflateHomeProductsBinding binding;
        public HomeProductHolder(@NonNull View itemView) {
            super(itemView);
            binding = InflateHomeProductsBinding.bind(itemView);
        }
    }
}
