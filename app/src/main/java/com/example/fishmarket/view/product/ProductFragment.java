package com.example.fishmarket.view.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishmarket.databinding.FragProductBinding;
import com.example.fishmarket.utils.BaseFragment;
import com.example.fishmarket.view_model.ProductViewModel;

public class ProductFragment extends BaseFragment {
  FragProductBinding binding;
  ProductViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragProductBinding.inflate(inflater,container,false);
        viewModel=new ViewModelProvider(this).get(ProductViewModel.class);

        viewModel.mutableLiveData.observe(getViewLifecycleOwner(),arrayList -> {
            viewModel.homeAdapter.setFragment(this);
            binding.rvProducts.setAdapter(viewModel.homeAdapter);
        });
        viewModel.getProducts();
        return binding.getRoot();
    }
}
