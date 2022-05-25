package com.example.fishmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishmarket.R;
import com.example.fishmarket.databinding.InflateDiscoverBinding;
import com.example.fishmarket.model.DiscoverPOJO;

import java.util.ArrayList;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.DiscoverHolder> {
    Context context;
    ArrayList<DiscoverPOJO> discoverPOJOS;

    public DiscoverAdapter(Context context, ArrayList<DiscoverPOJO> discoverPOJOS) {
        this.context = context;
        this.discoverPOJOS = discoverPOJOS;
    }

    @NonNull
    @Override
    public DiscoverHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.inflate_discover,parent,false);
        return new DiscoverHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverHolder holder, int position) {
        try {
            DiscoverPOJO pojo=discoverPOJOS.get(position);
            if (position%2==0){
                holder.binding.ivImage.setBackgroundResource(R.color.light_blue);
            }else {
                holder.binding.ivImage.setBackgroundResource(R.color.light_purple);
            }
            holder.binding.ivImage.setImageResource(pojo.image);
            holder.binding.tvName.setText(pojo.name);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return discoverPOJOS.size();
    }

    public class DiscoverHolder extends RecyclerView.ViewHolder {
        InflateDiscoverBinding binding;
        public DiscoverHolder(@NonNull View itemView) {
            super(itemView);
            binding=InflateDiscoverBinding.bind(itemView);
        }
    }
}
