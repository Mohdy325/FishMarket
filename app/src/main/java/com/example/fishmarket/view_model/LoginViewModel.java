package com.example.fishmarket.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fishmarket.api_services.ApiManager;
import com.example.fishmarket.api_services.UrlContainer;
import com.example.fishmarket.model.CommonModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    MutableLiveData<CommonModel> commonModelMutableLiveData=new MutableLiveData<>();
    public LiveData<CommonModel> modelLiveData=commonModelMutableLiveData;

    public void register(HashMap<String,String> hashMap){
        ApiManager.getApiService().register(hashMap).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful()){
                    commonModelMutableLiveData.postValue(response.body());

                }else {
                    CommonModel model=new CommonModel();
                    model.status=false;
                        model.message = "Error Occured";
                    commonModelMutableLiveData.postValue(model);
                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                CommonModel model=new CommonModel();
                model.status=false;
                if (t.getLocalizedMessage()!=null) {
                    model.message = t.getLocalizedMessage();
                }
                commonModelMutableLiveData.postValue(model);

            }
        });
    }
    public void login(HashMap<String,String> hashMap){
        ApiManager.getApiService().commonRequest(UrlContainer.LOGIN,hashMap).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        response.body().isLogin=true;
                    }
                    commonModelMutableLiveData.postValue(response.body());
                }else {
                    CommonModel model=new CommonModel();
                    model.status=false;
                        model.message = "Error Occured";
                    commonModelMutableLiveData.postValue(model);
                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                CommonModel model=new CommonModel();
                model.status=false;
                if (t.getLocalizedMessage()!=null) {
                    model.message = t.getLocalizedMessage();
                }
                commonModelMutableLiveData.postValue(model);

            }
        });
    }

}
