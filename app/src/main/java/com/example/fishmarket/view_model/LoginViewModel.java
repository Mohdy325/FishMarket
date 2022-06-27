package com.example.fishmarket.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fishmarket.model.CommonModel;

public class LoginViewModel extends ViewModel {
    MutableLiveData<CommonModel> commonModelMutableLiveData=new MutableLiveData<>();
    public LiveData<CommonModel> modelLiveData=commonModelMutableLiveData;


}
