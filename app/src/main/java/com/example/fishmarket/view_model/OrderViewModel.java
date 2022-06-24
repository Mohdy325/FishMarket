package com.example.fishmarket.view_model;

import androidx.lifecycle.ViewModel;

import com.example.fishmarket.adapter.OrderAdapter;
import com.example.fishmarket.model.OrderPOJO;

import java.util.ArrayList;

public class OrderViewModel extends ViewModel {

    public OrderAdapter orderAdapter;
    public ArrayList<OrderPOJO> orderPOJOS=new ArrayList<>();
    public void setOrderData(){
        orderPOJOS=new ArrayList<>();
        orderPOJOS.add(new OrderPOJO());
        orderPOJOS.add(new OrderPOJO());
        orderPOJOS.add(new OrderPOJO());
        orderPOJOS.add(new OrderPOJO());
        orderPOJOS.add(new OrderPOJO());
        orderPOJOS.add(new OrderPOJO());
        orderPOJOS.add(new OrderPOJO());
        orderAdapter=new OrderAdapter(orderPOJOS);
        orderAdapter.notifyDataSetChanged();
    }
}
