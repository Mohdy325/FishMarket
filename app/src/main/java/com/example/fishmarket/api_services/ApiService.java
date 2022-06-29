package com.example.fishmarket.api_services;

import androidx.annotation.NonNull;

import com.example.fishmarket.model.CommonModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

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
            @QueryMap HashMap<String,String> hashMap
            /*@Query("name") String name,
            @Query("mobile") String mobile,
            @Query("email") String email,
            @Query("password") String password*/
    );
    @NonNull
    @POST()
    Call<CommonModel> commonRequest(
            @Url String url,
            @QueryMap HashMap<String,String> hashMap
            /*@Query("name") String name,
            @Query("mobile") String mobile,
            @Query("email") String email,
            @Query("password") String password*/
    );
    @NonNull
    @GET()
    Call<CommonModel> getRequest(
            @Url String url
    );
    @NonNull
    @POST()
    Call<CommonModel> getRequest(
            @Url String url,
            @QueryMap HashMap<String,String> hashMap
    );
}
