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
import com.example.fishmarket.databinding.InflateHomeCategoryBinding;
import com.example.fishmarket.model.CategoryPOJO;
import com.example.fishmarket.model.ProductPOJO;

import java.util.ArrayList;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.HomeCategoryHolder> {
    Context context;

    ArrayList<CategoryPOJO> categoryPOJOS=new ArrayList<>();

    public HomeCategoryAdapter(ArrayList<CategoryPOJO> categoryPOJOS) {
        this.categoryPOJOS = categoryPOJOS;
    }
    public HomeCategoryAdapter() {
    }
    public void updateList(ArrayList<CategoryPOJO> arrayList){
        categoryPOJOS=arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context==null){
            context= parent.getContext();
        }
       View view= LayoutInflater.from(context).inflate(R.layout.inflate_home_category,parent,false);
        return new HomeCategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCategoryHolder holder, int position) {
        holder.binding.setCategoryModel(categoryPOJOS.get(position));
        holder.itemView.setOnClickListener(view -> {
            //((ProductCategoryFragment)context).openSubCategory(position);
            Bundle bundle=new Bundle();
            bundle.putSerializable(UrlContainer.TRANSFER_MODEL,categoryPOJOS.get(position));
            if (context instanceof MainActivity){
                ((MainActivity)context).navController.navigate(R.id.action_nav_home_to_nav_sub_category,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryPOJOS.size();
    }

    public class HomeCategoryHolder extends RecyclerView.ViewHolder {
       InflateHomeCategoryBinding binding;
        public HomeCategoryHolder(@NonNull View itemView) {
            super(itemView);
            binding=InflateHomeCategoryBinding.bind(itemView);
        }
    }
}
