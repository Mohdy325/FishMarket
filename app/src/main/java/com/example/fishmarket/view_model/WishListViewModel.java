package com.example.fishmarket.view_model;

import static com.example.fishmarket.MainActivity.userId;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.example.fishmarket.adapter.HomeProductAdapter;
import com.example.fishmarket.adapter.WishListAdapter;
import com.example.fishmarket.api_services.ApiManager;
import com.example.fishmarket.api_services.UrlContainer;
import com.example.fishmarket.model.CommonModel;
import com.example.fishmarket.model.ProductPOJO;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishListViewModel extends ViewModel {
    public MutableLiveData<CommonModel> successLiveData=new MutableLiveData<>();
    public MutableLiveData<String> errorLiveData=new MutableLiveData<>();

    public WishListAdapter wishListAdapter = new WishListAdapter();

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void getFavouriteList(){
        HashMap<String,String> hashMap=new HashMap<>();
        if (!userId.isEmpty()) {
            hashMap.put("user_id", userId);
        }

        ApiManager.getApiService().commonRequest(UrlContainer.MY_FAVOURITE,hashMap).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful() && response.body().status){

                    wishListAdapter.updateList(response.body().favourite);
                    successLiveData.postValue(response.body());

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

                    wishListAdapter.remove(model);
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
