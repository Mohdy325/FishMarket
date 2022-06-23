package com.example.fishmarket.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishmarket.databinding.FragMyOrderBinding;
import com.example.fishmarket.databinding.FragmentAccountBinding;
import com.example.fishmarket.utils.BaseFragment;
import com.example.fishmarket.view_model.AccountViewModel;
import com.example.fishmarket.view_model.OrderViewModel;

public class MyOrderFragment extends BaseFragment {
    FragMyOrderBinding binding;
    OrderViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragMyOrderBinding.inflate(inflater,container,false);
        viewModel=new ViewModelProvider(this).get(OrderViewModel.class);
        viewModel.setOrderData();
        binding.rvOrders.setAdapter(viewModel.orderAdapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
