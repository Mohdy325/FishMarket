package com.example.fishmarket.view_model;

import androidx.lifecycle.ViewModel;

import com.example.fishmarket.R;
import com.example.fishmarket.model.LoginPOJO;

public class AccountViewModel extends ViewModel {
    public LoginPOJO loginPOJO=new LoginPOJO();
    public void setLoginData(){
        loginPOJO.name="Ram Singh Chandal";
        loginPOJO.image= R.drawable.user;
    }
}
