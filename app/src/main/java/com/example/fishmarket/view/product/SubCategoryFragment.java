package com.example.fishmarket.view.product;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishmarket.adapter.ProductSubCategoryAdapter;
import com.example.fishmarket.api_services.UrlContainer;
import com.example.fishmarket.databinding.FragProductSubCategoryBinding;
import com.example.fishmarket.model.CategoryPOJO;
import com.example.fishmarket.model.SubCategoryPOJO;
import com.example.fishmarket.utils.BaseFragment;
import com.example.fishmarket.view_model.ProductViewModel;
import com.example.fishmarket.view_model.SubCategoryViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SubCategoryFragment extends BaseFragment {

    SubCategoryViewModel viewModel;
    private FragProductSubCategoryBinding binding;
    CategoryPOJO categoryPOJO;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragProductSubCategoryBinding.inflate(inflater, container, false);
        //View root = binding.getRoot();

        viewModel=new ViewModelProvider(this).get(SubCategoryViewModel.class);
      //  viewModel.setUpSubCategoryData();
        categoryPOJO= (CategoryPOJO) getArguments().getSerializable(UrlContainer.TRANSFER_MODEL);
        viewModel.getSubCategory(categoryPOJO.id+"");

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

       // setUpSubCategoryData();

        return binding.getRoot();
    }

/*
    public ArrayList<CategoryPOJO> subCategoryList=new ArrayList<>();

    public void setUpSubCategoryData(){

        subCategoryList=new ArrayList<>();
        ArrayList<SubCategoryPOJO> subCategoryPOJOS=new ArrayList<>();
        subCategoryPOJOS.add(new SubCategoryPOJO());
        subCategoryPOJOS.add(new SubCategoryPOJO());
        subCategoryPOJOS.add(new SubCategoryPOJO());
        subCategoryPOJOS.add(new SubCategoryPOJO());
        subCategoryPOJOS.add(new SubCategoryPOJO());
        subCategoryPOJOS.add(new SubCategoryPOJO());
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));



        // categoryPOJOS.add(new CategoryPOJO("AQUARIUM", R.drawable.feed));
        binding.rvProducts.setAdapter(subCategoryAdapter);
        subCategoryAdapter.notifyDataSetChanged();

    }
*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
