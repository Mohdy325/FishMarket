package com.example.fishmarket.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.example.fishmarket.R;
import com.example.fishmarket.adapter.AddressAdapter;
import com.example.fishmarket.databinding.ActivityAddressListBinding;
import com.example.fishmarket.model.AddressPOJO;
import com.example.fishmarket.utils.PrefManager;
import com.example.fishmarket.utils.UtilityFunction;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressActivity extends AppCompatActivity implements AddressAdapter.OnSelected {
    ActivityAddressListBinding binding;
    Context context;
    Activity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=activity=this;
      binding=  DataBindingUtil.setContentView(this, R.layout.activity_address_list);
        binding.btnAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fraimCheckOut,new ConfirmPaymentActivity()).addToBackStack("").commit();
*/

                Intent intent=new Intent(context, AddAddressActivity.class);
                startActivity(intent);
            }
        });
        addressPOJOS=new ArrayList<>();
        addressPOJOS.add(new AddressPOJO());
        addressPOJOS.add(new AddressPOJO());
        addressPOJOS.add(new AddressPOJO());
        adapter=new AddressAdapter(context,addressPOJOS,AddressActivity.this);
        binding.rvAddressList.setAdapter(adapter);

        if (addressPOJOS.size()==0){
            binding.tvNoData.setVisibility(View.VISIBLE);
            binding.rvAddressList.setVisibility(View.GONE);
        }else {
            binding.tvNoData.setVisibility(View.GONE);
            binding.rvAddressList.setVisibility(View.VISIBLE);
        }
      //getAddressList();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
       // getAddressList();
    }

    AddressAdapter adapter;
    ArrayList<AddressPOJO> addressPOJOS;
/*
    private void getAddressList(){
        UtilityFunction.showLoading(context,"Please wait...");
        ApiExecutor.getApiService().getData(
                "Bearer "+ PrefManager.GetLoginData(context).token,
                UrlContainer.MY_ADDRESS
        ).enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                UtilityFunction.hideLoading();
                if (response != null && response.message() != null && response.message().toLowerCase().contains("unauthorized")) {
                    UtilityFunction.gotoLoginSessionExpire(activity);
                    return;
                }
                if (response.body() != null && response.body().msg != null && response.body().msg.toLowerCase().contains("token is expired")) {
                    UtilityFunction.gotoLoginSessionExpire(activity);
                    return;
                }
                if (response.isSuccessful() && !response.body().error){
                    addressPOJOS=response.body().my_address;
                    if (PrefManager.GetDefaultAddressData(context)!=null && PrefManager.GetDefaultAddressData(context).address!=null){
                        for (AddressPOJO addressPOJO : addressPOJOS){
                            if (PrefManager.GetDefaultAddressData(context).addressID==addressPOJO.addressID){
                                PrefManager.SaveDefualtAddressData(context,addressPOJO);
                            }
                        }
                    }
                    adapter=new AddressAdapter(context,addressPOJOS,AddressActivity.this);
                    binding.rvAddressList.setAdapter(adapter);

                    if (addressPOJOS.size()==0){
                        binding.tvNoData.setVisibility(View.VISIBLE);
                        binding.rvAddressList.setVisibility(View.GONE);
                    }else {
                        binding.tvNoData.setVisibility(View.GONE);
                        binding.rvAddressList.setVisibility(View.VISIBLE);
                    }

                }

            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {
                UtilityFunction.hideLoading();
            }
        });
    }
*/

    @Override
    public void onSelected(int position, AddressPOJO addressPOJO) {

    }

    @Override
    public void onUnSelected(int position, AddressPOJO addressPOJO) {

    }
}
