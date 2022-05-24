package com.example.fishmarket.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.fishmarket.databinding.ActivityLoginBinding;
import com.example.fishmarket.utils.BaseActivity;

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvVerify.setOnClickListener(view -> startActivity(goTo(OtpVerificationActivity.class)));
    }
}
