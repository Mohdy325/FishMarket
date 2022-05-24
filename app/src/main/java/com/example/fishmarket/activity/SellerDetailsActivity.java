package com.example.fishmarket.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.fishmarket.databinding.ActivitySellerDetailsBinding;
import com.example.fishmarket.utils.BaseActivity;

public class SellerDetailsActivity extends BaseActivity {
    ActivitySellerDetailsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySellerDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.ivBack.setOnClickListener(view -> onBackPressed());
        binding.toolbar.tvTitle.setText("Seller Details");

    }
}
