package com.example.fishmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fishmarket.R;
import com.example.fishmarket.databinding.InflateAddedImagesBinding;
import com.example.fishmarket.model.ImagesPOJO;
import com.example.fishmarket.utils.OnItemClick;

import java.util.ArrayList;

public class AddedImageAdapter extends RecyclerView.Adapter<AddedImageAdapter.AddedImageHolder> {
    Context context;
    ArrayList<ImagesPOJO> imagesPOJOS;
    OnItemClick onItemClick;

    public AddedImageAdapter(Context context, ArrayList<ImagesPOJO> imagesPOJOS, OnItemClick onItemClick) {
        this.context = context;
        this.imagesPOJOS = imagesPOJOS;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public AddedImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.inflate_added_images,parent,false);
        return new AddedImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddedImageHolder holder, int position) {
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return imagesPOJOS.size();
    }

    public class AddedImageHolder extends RecyclerView.ViewHolder {
        InflateAddedImagesBinding binding;
        public AddedImageHolder(@NonNull View itemView) {
            super(itemView);
            binding=InflateAddedImagesBinding.bind(itemView);
        }
    }
}
