package com.example.fishmarket.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishmarket.MainActivity;
import com.example.fishmarket.R;
import com.example.fishmarket.api_services.UrlContainer;
import com.example.fishmarket.databinding.InflateProductCategoryBinding;
import com.example.fishmarket.model.CategoryPOJO;
import com.example.fishmarket.view.product.ProductCategoryFragment;

import java.util.ArrayList;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.CategoryHolder> {
    Context context;
    ArrayList<CategoryPOJO> categoryPOJOS;

    public ProductCategoryAdapter(ArrayList<CategoryPOJO> categoryPOJOS) {
        this.context = context;
        this.categoryPOJOS = categoryPOJOS;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if (context ==null){
           context=parent.getContext();
       }
        View view= LayoutInflater.from(context).inflate(R.layout.inflate_product_category,parent,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
       holder.binding.setModel(categoryPOJOS.get(position));
       holder.itemView.setOnClickListener(view -> {
           //((ProductCategoryFragment)context).openSubCategory(position);
           Bundle bundle=new Bundle();
           bundle.putSerializable(UrlContainer.TRANSFER_MODEL,categoryPOJOS.get(position));
           if (context instanceof MainActivity){
               ((MainActivity)context).navController.navigate(R.id.action_nav_product_to_nav_sub_category,bundle);
           }
       });
    }

    @Override
    public int getItemCount() {
        return categoryPOJOS.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {
        InflateProductCategoryBinding binding;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            binding=InflateProductCategoryBinding.bind(itemView);
        }
    }
}
