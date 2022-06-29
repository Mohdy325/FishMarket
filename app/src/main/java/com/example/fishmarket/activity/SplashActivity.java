package com.example.fishmarket.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.example.fishmarket.MainActivity;
import com.example.fishmarket.databinding.ActivitySplashBinding;
import com.example.fishmarket.utils.BaseActivity;
import com.example.fishmarket.utils.PrefManager;


public class SplashActivity extends BaseActivity {
    ActivitySplashBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        new Handler().postDelayed(() -> {
            if (PrefManager.GetLoginData(context)!=null && PrefManager.GetLoginData(context).id!=null){
                startActivity(goTo(MainActivity.class));

            }else {
                startActivity(goTo(LoginActivity.class));
            }
            finish();

        }, 2500);
    }
}
