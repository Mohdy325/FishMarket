package com.example.fishmarket.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishmarket.activity.AddressActivity;
import com.example.fishmarket.activity.EditProfileActivity;
import com.example.fishmarket.activity.WishListActivity;
import com.example.fishmarket.databinding.FragmentAccountBinding;
import com.example.fishmarket.utils.BaseFragment;
import com.example.fishmarket.view_model.AccountViewModel;

public class AccountFragment extends BaseFragment {
    FragmentAccountBinding binding;
    AccountViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding=FragmentAccountBinding.inflate(inflater,container,false);
       viewModel=new ViewModelProvider(this).get(AccountViewModel.class);
       viewModel.setLoginData();
       binding.setModel(viewModel.loginPOJO);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.llProfile.setOnClickListener(view1 -> startActivity(new Intent(context, EditProfileActivity.class)));
        binding.llMyFavourite.setOnClickListener(view1 -> startActivity(new Intent(context, WishListActivity.class)));
        binding.llMyAddresses.setOnClickListener(view1 -> startActivity(new Intent(context, AddressActivity.class)));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
