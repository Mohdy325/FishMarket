package com.example.fishmarket.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishmarket.R;
import com.example.fishmarket.databinding.ActivityWishlistBinding;
import com.example.fishmarket.utils.BaseActivity;
import com.example.fishmarket.view_model.WishListViewModel;
import com.google.gson.Gson;

public class WishListActivity extends BaseActivity {
    ActivityWishlistBinding binding;
    public WishListViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_wishlist);
        binding.toolbar.setTitle("My Favourite Products");
        binding.toolbar.setNavigationOnClickListener(view -> onBackPressed());
        viewModel=new ViewModelProvider(this).get(WishListViewModel.class);
        binding.setViewModel(viewModel);
        binding.tvNoData.setVisibility(View.GONE);
        viewModel.successLiveData.observe(this,commonModel -> {
            binding.progressBar.setVisibility(View.GONE);
            if (viewModel.wishListAdapter.getItemCount()>0){
                binding.tvNoData.setVisibility(View.GONE);
            }else {
                binding.tvNoData.setVisibility(View.VISIBLE);
            }
        });
        viewModel.errorLiveData.observe(this,text ->{
            binding.progressBar.setVisibility(View.GONE);
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        });
       // viewModel.getFavouriteList();

    }
}
