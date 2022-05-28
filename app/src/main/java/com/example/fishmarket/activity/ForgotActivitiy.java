package com.example.fishmarket.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.fishmarket.databinding.ActivityForgotPasswordBinding;
import com.example.fishmarket.databinding.ActivityLoginBinding;
import com.example.fishmarket.utils.BaseActivity;

public class ForgotActivitiy extends BaseActivity {
    ActivityForgotPasswordBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvSend.setOnClickListener(view -> startActivity(goTo(OtpVerificationActivity.class)));
        binding.tvSignIn.setOnClickListener(view -> onBackPressed());
        binding.tvSignUp.setOnClickListener(view -> startActivity(goTo(SignUpActivity.class)));
    }
}
