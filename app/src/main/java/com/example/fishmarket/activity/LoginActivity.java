package com.example.fishmarket.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishmarket.MainActivity;
import com.example.fishmarket.api_services.UrlContainer;
import com.example.fishmarket.databinding.ActivityLoginBinding;
import com.example.fishmarket.model.LoginPOJO;
import com.example.fishmarket.utils.BaseActivity;
import com.example.fishmarket.utils.PrefManager;
import com.example.fishmarket.utils.UtilityFunction;
import com.example.fishmarket.utils.ValidateField;
import com.example.fishmarket.view_model.LoginViewModel;

import java.util.HashMap;

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding binding;
    LoginViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel=new ViewModelProvider(this).get(LoginViewModel.class);
        binding.tvForgot.setOnClickListener(view -> startActivity(goTo(ForgotActivitiy.class)));
        binding.tvSkip.setOnClickListener(view -> {startActivity(goTo(MainActivity.class));
        finish();
        });
        binding.tvSignUp.setOnClickListener(view -> startActivity(goTo(SignUpActivity.class)));
        viewModel.modelLiveData.observe(this,commonModel -> {
            UtilityFunction.hideLoading();
            if (commonModel.status){
               // if (commonModel.isLogin){
                    LoginPOJO loginPOJO=commonModel.user;

                    loginPOJO.mobile=binding.etEmail.getText().toString();
                   // loginPOJO.email=binding.etEmail.getText().toString();
                    loginPOJO.password=binding.etPassword.getText().toString();
                    PrefManager.SaveLoginData(context,loginPOJO);
                    startActivity(goTo(MainActivity.class).putExtra(UrlContainer.TRANSFER_MODEL,"Data"));
                    finish();

                Toast.makeText(context, commonModel.message, Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(context, commonModel.message, Toast.LENGTH_SHORT).show();
            }
        });
        binding.tvLogin.setOnClickListener(view -> {
          /*  if (ValidateField.isEmptyField(binding.etName,"Name")){
                return;
            }*/
            if (ValidateField.isEmptyField(binding.etEmail,"Mobile No")){
                return;
            }
            if (ValidateField.isValidCount(binding.etEmail,10,"Valid Mobile No")){
                return;
            }
            if (ValidateField.isEmptyField(binding.etPassword,"Password")){
                return;
            }
            if (ValidateField.isValidCount(binding.etPassword,6,"Valid Password")){
                return;
            }

            UtilityFunction.showLoading(context,"Please wait...");
            HashMap<String,String> hashMap=new HashMap<>();
           // hashMap.put("name",binding.etName.getText().toString());
           // hashMap.put("mobile",binding.etEmail.getText().toString());
           hashMap.put("email",binding.etEmail.getText().toString());
            hashMap.put("password",binding.etPassword.getText().toString());

            viewModel.login(hashMap);


        });
    }
}
