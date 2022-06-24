package com.example.fishmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishmarket.R;
import com.example.fishmarket.model.OrderPOJO;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {
    Context context;
    ArrayList<OrderPOJO> orderPOJOS;

    public OrderAdapter(ArrayList<OrderPOJO> orderPOJOS) {
        this.orderPOJOS = orderPOJOS;
    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.inflate_order,parent,false);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return orderPOJOS.size();
    }

    public class OrderHolder extends RecyclerView.ViewHolder {
        public OrderHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
