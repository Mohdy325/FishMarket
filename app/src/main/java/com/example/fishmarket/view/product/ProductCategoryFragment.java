package com.example.fishmarket.view.product;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;

import com.example.fishmarket.MainActivity;
import com.example.fishmarket.R;
import com.example.fishmarket.api_services.UrlContainer;
import com.example.fishmarket.databinding.FragProductCategoryBinding;
import com.example.fishmarket.utils.BaseFragment;
import com.example.fishmarket.view.home.HomeViewModel;
import com.example.fishmarket.view_model.ProductViewModel;
import com.google.gson.Gson;

public class ProductCategoryFragment extends BaseFragment {
    FragProductCategoryBinding binding;
    HomeViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragProductCategoryBinding.inflate(inflater,container,false);
        viewModel=new ViewModelProvider(this).get(HomeViewModel.class);
        binding.setViewModel(viewModel);

        viewModel.successLiveData.observe(getViewLifecycleOwner(),commonModel -> {
            //binding.refreshLayout.setRefreshing(false);
            binding.progressBar.setVisibility(View.GONE);
            Log.e("fdassfa",new Gson().toJson(commonModel.categoryPOJOS));
        });
        viewModel.errorLiveData.observe(getViewLifecycleOwner(),text ->{
           // binding.refreshLayout.setRefreshing(false);
            binding.progressBar.setVisibility(View.GONE);
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        });
        viewModel.getCategoryList();
      //  binding.rvProducts.setAdapter(viewModel.categoryAdapter);
       // binding.progressBar.setVisibility(View.GONE);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /*public void openSubCategory(int i){
            Bundle bundle=new Bundle();
            bundle.putSerializable(UrlContainer.TRANSFER_MODEL,viewModel.categoryPOJOS.get(i));
            if (context instanceof MainActivity){
                ((MainActivity)context).navController.navigate(R.id.action_nav_product_to_nav_sub_category,bundle);
            }
        }*/
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
