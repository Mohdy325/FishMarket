package com.example.fishmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishmarket.R;
import com.example.fishmarket.databinding.InflateProductSubcategoryBinding;
import com.example.fishmarket.model.CategoryPOJO;

import java.util.ArrayList;

public class ProductSubCategoryAdapter extends RecyclerView.Adapter<ProductSubCategoryAdapter.ProductSubHolder> {
    Context context;
    ArrayList<CategoryPOJO> categoryPOJOS;

    public ProductSubCategoryAdapter(ArrayList<CategoryPOJO> categoryPOJOS) {
        this.categoryPOJOS = categoryPOJOS;
    }
    @NonNull
    @Override
    public ProductSubCategoryAdapter.ProductSubHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context==null){
            context= parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.inflate_product_subcategory,parent,false);
        return new ProductSubHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSubCategoryAdapter.ProductSubHolder holder, int position) {
        try {
            SubCategoryAdapter subCategoryAdapter=new SubCategoryAdapter(context,categoryPOJOS.get(position).subCategoryPOJOS);
            holder.binding.rvSubCategory.setAdapter(subCategoryAdapter);
            if (categoryPOJOS.get(position).isExpand){
                holder.binding.rvSubCategory.setVisibility(View.VISIBLE);
                holder.binding.ivExpandLess.setVisibility(View.VISIBLE);
                holder.binding.ivExpandMore.setVisibility(View.GONE);
            }else {
                holder.binding.rvSubCategory.setVisibility(View.GONE);
                holder.binding.ivExpandLess.setVisibility(View.GONE);
                holder.binding.ivExpandMore.setVisibility(View.VISIBLE);
            }
            holder.binding.ivExpandMore.setOnClickListener(view -> {
                categoryPOJOS.get(position).isExpand=true;
                notifyDataSetChanged();
            });
            holder.binding.ivExpandLess.setOnClickListener(view -> {
                categoryPOJOS.get(position).isExpand=false;
                notifyDataSetChanged();
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return categoryPOJOS.size();
    }

    public class ProductSubHolder extends RecyclerView.ViewHolder {
        InflateProductSubcategoryBinding binding;
        public ProductSubHolder(@NonNull View itemView) {
            super(itemView);
            binding=InflateProductSubcategoryBinding.bind(itemView);
        }
    }
}
