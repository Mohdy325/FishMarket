package com.example.fishmarket.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    public Context context;
    public Activity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=activity=getActivity();
    }

    public Intent goTo(Class cls){
        Intent intent=new Intent(context,cls);
        return intent;
    }
}
