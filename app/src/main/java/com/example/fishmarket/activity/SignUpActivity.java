package com.example.fishmarket.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.fishmarket.api_services.UrlContainer;
import com.example.fishmarket.databinding.ActivitySignupBinding;
import com.example.fishmarket.utils.BaseActivity;


public class SignUpActivity extends BaseActivity {
    ActivitySignupBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnSignUp.setOnClickListener(view -> startActivity(goTo(OtpVerificationActivity.class).putExtra(UrlContainer.TRANSFER_MODEL,"Data")));
        binding.tvSignIn.setOnClickListener(view -> onBackPressed());

    }
}
