package com.example.fishmarket.utils;

import static com.example.fishmarket.MainActivity.userId;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fishmarket.R;


public class BaseActivity extends AppCompatActivity {
    public Context context;
    public Activity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=activity=this;
        if (userId.isEmpty() && PrefManager.GetLoginData(context)!=null && PrefManager.GetLoginData(context).id!=null){
            userId= PrefManager.GetLoginData(this).id;
        }

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (findViewById(R.id.iv_back)!=null){
            findViewById(R.id.iv_back).setOnClickListener(view -> onBackPressed());
        }
    }

    public Intent goTo(Class cls){
        Intent intent=new Intent(context,cls);
        return intent;
    }
}
