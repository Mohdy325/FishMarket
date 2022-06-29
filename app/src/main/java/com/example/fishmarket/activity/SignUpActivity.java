package com.example.fishmarket.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishmarket.MainActivity;
import com.example.fishmarket.api_services.UrlContainer;
import com.example.fishmarket.databinding.ActivitySignupBinding;
import com.example.fishmarket.model.LoginPOJO;
import com.example.fishmarket.utils.BaseActivity;
import com.example.fishmarket.utils.PrefManager;
import com.example.fishmarket.utils.UtilityFunction;
import com.example.fishmarket.utils.ValidateField;
import com.example.fishmarket.view_model.LoginViewModel;

import java.util.HashMap;


public class SignUpActivity extends BaseActivity {
    ActivitySignupBinding binding;
    LoginViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel=new ViewModelProvider(this).get(LoginViewModel.class);
        //binding.btnSignUp.setOnClickListener(view -> );
        binding.tvSignIn.setOnClickListener(view -> onBackPressed());

        viewModel.modelLiveData.observe(this,commonModel -> {
            UtilityFunction.hideLoading();
           try {
               if (commonModel.status){
                   if (commonModel.isLogin){
                       LoginPOJO loginPOJO=commonModel.user;
                       loginPOJO.mobile=binding.etMobile.getText().toString();
                       loginPOJO.email=binding.etEmail.getText().toString();
                       loginPOJO.password=binding.etPassword.getText().toString();
                       PrefManager.SaveLoginData(context,loginPOJO);
                       startActivity(goTo(MainActivity.class).putExtra(UrlContainer.TRANSFER_MODEL,"Data"));
                       finish();
                   }else {
                       HashMap<String,String> hashMap=new HashMap<>();
                       //hashMap.put("name",binding.etName.getText().toString());
                       hashMap.put("email",binding.etMobile.getText().toString());
                       //  hashMap.put("email",binding.etEmail.getText().toString());
                       hashMap.put("password",binding.etPassword.getText().toString());
                       UtilityFunction.showLoading(context,"Please wait...");

                       viewModel.login(hashMap);
                   }
                   Toast.makeText(context, commonModel.message, Toast.LENGTH_SHORT).show();

               }else {
                   Toast.makeText(context, commonModel.message, Toast.LENGTH_SHORT).show();
               }
           }catch (Exception e){
               e.printStackTrace();
           }
        });
        binding.btnSignUp.setOnClickListener(view -> {
            if (ValidateField.isEmptyField(binding.etName,"Name")){
                return;
            }
            if (ValidateField.isEmptyField(binding.etMobile,"Mobile No")){
                return;
            }
            if (ValidateField.isValidCount(binding.etMobile,10,"Valid Mobile No")){
                return;
            }
            if (ValidateField.isEmptyField(binding.etPassword,"Password")){
                return;
            }
            if (ValidateField.isValidCount(binding.etPassword,6,"Valid Password")){
                return;
            }
            if (ValidateField.isEmptyField(binding.etConfirmaPassword,"Confirm Password")){
                return;
            }
            if (ValidateField.isValidCount(binding.etConfirmaPassword,6,"Valid Confirm Password")){
                return;
            }
            if (!binding.etPassword.getText().toString().equals(binding.etConfirmaPassword.getText().toString())){
                ValidateField.error(binding.etConfirmaPassword,"Password does'nt match");
                return;
            }
            UtilityFunction.showLoading(context,"Please wait...");
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("name",binding.etName.getText().toString());
            hashMap.put("mobile",binding.etMobile.getText().toString());
            hashMap.put("email",binding.etEmail.getText().toString());
            hashMap.put("password",binding.etPassword.getText().toString());

            viewModel.register(hashMap);


        });
    }

}
