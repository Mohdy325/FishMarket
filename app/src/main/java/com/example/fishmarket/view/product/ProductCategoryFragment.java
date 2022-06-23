package com.example.fishmarket.view.product;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishmarket.databinding.FragProductCategoryBinding;
import com.example.fishmarket.utils.BaseFragment;
import com.example.fishmarket.view_model.ProductViewModel;

public class ProductCategoryFragment extends BaseFragment {
    FragProductCategoryBinding binding;
    ProductViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragProductCategoryBinding.inflate(inflater,container,false);
        viewModel=new ViewModelProvider(this).get(ProductViewModel.class);
        viewModel.setUpData();
        binding.rvProducts.setAdapter(viewModel.categoryAdapter);
        binding.progressBar.setVisibility(View.GONE);
        return binding.getRoot();
    }




}
