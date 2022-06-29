package com.example.fishmarket.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishmarket.R;
import com.example.fishmarket.api_services.UrlContainer;
import com.example.fishmarket.databinding.ActivitySellerDetailsBinding;
import com.example.fishmarket.model.ProductPOJO;
import com.example.fishmarket.utils.BaseActivity;
import com.example.fishmarket.view_model.DetailsViewModel;
import com.example.fishmarket.view_model.ProductViewModel;

public class SellerDetailsActivity extends BaseActivity {
    ActivitySellerDetailsBinding binding;
    public DetailsViewModel viewModel;
    ProductPOJO productPOJO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySellerDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.ivBack.setOnClickListener(view -> onBackPressed());
        binding.toolbar.tvTitle.setText("Seller Details");
        viewModel=new ViewModelProvider(this).get(DetailsViewModel.class);
        productPOJO= (ProductPOJO) getIntent().getSerializableExtra(UrlContainer.TRANSFER_MODEL);
        binding.setModel(productPOJO);
       binding.tvMrp.setPaintFlags(binding.tvMrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        viewModel.successLiveData.observe(this,commonModel -> {
            Toast.makeText(context, commonModel.message, Toast.LENGTH_SHORT).show();
            if (commonModel.product_details!=null) {
                productPOJO = commonModel.product_details;
                setUpWishlistAddedNot();
            }
        });

        binding.llFavourite.setOnClickListener(view -> {
            if (productPOJO.getProduct_favourite_status().equals("Yes")){
                viewModel.removeFromFavourite(productPOJO);
            }else {
                viewModel.addToFavourite(productPOJO);

            }
        });
        setUpWishlistAddedNot();
        //viewModel.getProductDetails(productPOJO);

    }
    private void setUpWishlistAddedNot(){
        if (productPOJO.getProduct_favourite_status().equals("Yes")){
            binding.ivFavourite.setImageResource(R.drawable.ic_baseline_favorite_24);
        }else {
            binding.ivFavourite.setImageResource(R.drawable.ic_baseline_heart);

        }
    }
}
