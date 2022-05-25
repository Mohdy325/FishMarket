package com.example.fishmarket.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fishmarket.databinding.FragSellBinding;
import com.example.fishmarket.utils.BaseFragment;

public class SellFragment extends BaseFragment {
FragSellBinding binding;
String[] typeOfFish={"Select Type of Fish","Papda","Red Tilapia"};
String[] sizeOfFish={"Select Size of Fish","Spawn","Zero","1 Inches","2 Inches","3 Inches"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragSellBinding.inflate(inflater,container,false);
        binding.spTypeOfFish.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,typeOfFish));
        binding.spSizeOfFish.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,sizeOfFish));

        return binding.getRoot();
    }
}

