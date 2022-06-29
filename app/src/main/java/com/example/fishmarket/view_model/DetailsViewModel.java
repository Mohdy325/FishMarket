package com.example.fishmarket.view_model;

import static com.example.fishmarket.MainActivity.userId;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fishmarket.api_services.ApiManager;
import com.example.fishmarket.api_services.UrlContainer;
import com.example.fishmarket.model.CommonModel;
import com.example.fishmarket.model.ProductPOJO;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsViewModel extends ViewModel {
    public MutableLiveData<CommonModel> successLiveData=new MutableLiveData<>();
    public MutableLiveData<String> errorLiveData=new MutableLiveData<>();

    public void getProductDetails(ProductPOJO model){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("product_id",model.getId().toString());
        if (!userId.isEmpty()) {
            hashMap.put("user_id", userId);
        }
        ApiManager.getApiService().commonRequest(UrlContainer.PRODUCT_DETAILS,hashMap).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful() && response.body().status){
                    model.setProduct_favourite_status("Yes");

                    //   wishListAdapter.updateIndex(model);
                    successLiveData.postValue(response.body());

                }else if (response.message()!=null){
                    errorLiveData.postValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                errorLiveData.postValue("Something went wrong...");
            }
        });
    }
    public void addToFavourite(ProductPOJO model){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("product_id",model.getId().toString());
        if (!userId.isEmpty()) {
            hashMap.put("user_id", userId);
        }
        ApiManager.getApiService().commonRequest(UrlContainer.FAVOURITE,hashMap).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful() && response.body().status){
                    model.setProduct_favourite_status("Yes");
                    response.body().product_details=model;
                    successLiveData.postValue(response.body());

                }else if (response.message()!=null){
                    errorLiveData.postValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                errorLiveData.postValue("Something went wrong...");
            }
        });
    }
    public void removeFromFavourite(ProductPOJO model){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("product_id",model.getId().toString());
        if (!userId.isEmpty()) {
            hashMap.put("user_id", userId);
        }

        ApiManager.getApiService().commonRequest(UrlContainer.UNFAVOURITE,hashMap).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful() && response.body().status){
                    model.setProduct_favourite_status("No");

                    response.body().product_details=model;
                    successLiveData.postValue(response.body());

                }else if (response.message()!=null){
                    errorLiveData.postValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                errorLiveData.postValue("Something went wrong...");
            }
        });
    }

}
