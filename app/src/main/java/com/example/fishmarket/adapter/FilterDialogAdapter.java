package com.example.fishmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishmarket.R;
import com.example.fishmarket.databinding.InflateFilterHomeBinding;
import com.example.fishmarket.model.CategoryPOJO;

import java.util.ArrayList;

public class FilterDialogAdapter extends RecyclerView.Adapter<FilterDialogAdapter.FilterHolder> {
   Context context;
   ArrayList<CategoryPOJO> categoryPOJOS;

    public FilterDialogAdapter(Context context, ArrayList<CategoryPOJO> categoryPOJOS) {
        this.context = context;
        this.categoryPOJOS = categoryPOJOS;
    }

    @NonNull
    @Override
    public FilterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.inflate_filter_home,parent,false);

        return new FilterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterHolder holder, int position) {
        CategoryPOJO pojo =categoryPOJOS.get(position);
        if (pojo.getSelected()){
            holder.binding.chCategory.setChecked(true);
        }else {
            holder.binding.chCategory.setChecked(false);
        }
        holder.binding.chCategory.setText(pojo.getName());
        holder.binding.chCategory.setOnCheckedChangeListener((compoundButton, b) -> {
            if (categoryPOJOS.get(position).getSelected()){
                categoryPOJOS.get(position).setSelected(false);
            }else {
                categoryPOJOS.get(position).setSelected(true);
            }
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return categoryPOJOS.size();
    }

    public class FilterHolder extends RecyclerView.ViewHolder {
        InflateFilterHomeBinding binding;
        public FilterHolder(@NonNull View itemView) {
            super(itemView);
            binding=InflateFilterHomeBinding.bind(itemView);

        }
    }
}
