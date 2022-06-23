package com.example.fishmarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fishmarket.R;
import com.example.fishmarket.databinding.InflateSubcategoryBinding;
import com.example.fishmarket.model.SubCategoryPOJO;

import java.util.ArrayList;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryHolder> {
    Context context;
    ArrayList<SubCategoryPOJO> subCategoryPOJOS;

    public SubCategoryAdapter(Context context, ArrayList<SubCategoryPOJO> subCategoryPOJOS) {
        this.context = context;
        this.subCategoryPOJOS = subCategoryPOJOS;
    }

    @NonNull
    @Override
    public SubCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.inflate_subcategory,parent,false);

        return new SubCategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(context, ProductListActivity.class);
                //context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subCategoryPOJOS.size();
    }

    public class SubCategoryHolder extends RecyclerView.ViewHolder {
       InflateSubcategoryBinding binding;
        public SubCategoryHolder(@NonNull View itemView) {
            super(itemView);
            binding=InflateSubcategoryBinding.bind(itemView);
        }
    }
}
