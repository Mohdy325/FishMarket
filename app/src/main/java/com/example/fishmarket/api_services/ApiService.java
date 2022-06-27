package com.example.fishmarket.api_services;

import androidx.annotation.NonNull;

import com.example.fishmarket.model.CommonModel;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @NonNull
    @POST(UrlContainer.HOME)
    Call<CommonModel> callHomeApi();
    @NonNull
    @POST(UrlContainer.SUB_CATEGORY)
    Call<CommonModel> getSubCategory(
            @Query("category_id") String categoryId
    );
    @NonNull
    @POST(UrlContainer.PRODUCT_LIST_BY_SUBCATEGORY)
    Call<CommonModel> getProductList(
            @Query("sub_category_id") String sub_category_id
    );
    @NonNull
    @POST(UrlContainer.REGISTER)
    Call<CommonModel> register(
            @Query("name") String name,
            @Query("mobile") String mobile,
            @Query("email") String email,
            @Query("password") String password
    );
}
