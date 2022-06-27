package com.example.fishmarket.view.product;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishmarket.api_services.UrlContainer;
import com.example.fishmarket.databinding.FragProductBinding;
import com.example.fishmarket.model.SubCategoryPOJO;
import com.example.fishmarket.utils.BaseFragment;
import com.example.fishmarket.view_model.ProductViewModel;
import com.google.gson.Gson;

public class ProductFragment extends BaseFragment {
  FragProductBinding binding;
  ProductViewModel viewModel;
  SubCategoryPOJO categoryPOJO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragProductBinding.inflate(inflater,container,false);
        viewModel=new ViewModelProvider(this).get(ProductViewModel.class);

        categoryPOJO= (SubCategoryPOJO) getArguments().getSerializable(UrlContainer.TRANSFER_MODEL);
        /*viewModel.mutableLiveData.observe(getViewLifecycleOwner(),arrayList -> {
            viewModel.homeAdapter.setFragment(this);
            binding.rvProducts.setAdapter(viewModel.homeAdapter);
        });*/
        binding.setViewModel(viewModel);
        viewModel.successLiveData.observe(getViewLifecycleOwner(),commonModel -> {
            binding.progressBar.setVisibility(View.GONE);
            Log.e("fdassfa",new Gson().toJson(commonModel.in_trending));
        });
        viewModel.errorLiveData.observe(getViewLifecycleOwner(),text ->{
            binding.progressBar.setVisibility(View.GONE);
            binding.tvNoData.setVisibility(View.VISIBLE);
            //Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

        });
        viewModel.getProducts(categoryPOJO.id+"");
        return binding.getRoot();
    }
}
