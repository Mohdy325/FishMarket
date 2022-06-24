package com.example.fishmarket.view.product;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishmarket.adapter.ProductSubCategoryAdapter;
import com.example.fishmarket.databinding.FragProductSubCategoryBinding;
import com.example.fishmarket.model.CategoryPOJO;
import com.example.fishmarket.model.SubCategoryPOJO;
import com.example.fishmarket.utils.BaseFragment;
import com.example.fishmarket.view_model.ProductViewModel;

import java.util.ArrayList;

public class SubCategoryFragment extends BaseFragment {

   // ProductViewModel viewModel;
    private FragProductSubCategoryBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragProductSubCategoryBinding.inflate(inflater, container, false);
        //View root = binding.getRoot();

       // viewModel=new ViewModelProvider(this).get(ProductViewModel.class);
      //  viewModel.setUpSubCategoryData();
        binding.progressBar.setVisibility(View.GONE);


setUpSubCategoryData();

        return binding.getRoot();
    }

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
        ProductSubCategoryAdapter subCategoryAdapter=new ProductSubCategoryAdapter(subCategoryList);
        binding.rvProducts.setAdapter(subCategoryAdapter);
        subCategoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
