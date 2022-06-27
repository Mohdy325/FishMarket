package com.example.fishmarket.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;


import com.example.fishmarket.MainActivity;
import com.example.fishmarket.R;
import com.example.fishmarket.api_services.UrlContainer;
import com.example.fishmarket.databinding.ActivityAddAddressBinding;
import com.example.fishmarket.model.AddressPOJO;
import com.example.fishmarket.search_autocomplete.GeocodingLocation;
import com.example.fishmarket.search_autocomplete.PlacesAutoCompleteAdapter;
import com.example.fishmarket.search_autocomplete.Prediction;
import com.example.fishmarket.utils.UtilityFunction;
import com.example.fishmarket.view.bottom_dialogs.LocationDialogFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAddressActivity extends AppCompatActivity {
    ActivityAddAddressBinding binding;
    Context context;
    Activity activity;
   String TITLE="";


    public final String OFFICE = "Office";
    public final String HOME = "Home";
    public final String OTHERS = "Others";
    public String ADDRESS_TYPE = HOME;

    AddressPOJO addressPOJO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=activity=this;
        binding= DataBindingUtil.setContentView(this, R.layout.activity_add_address);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        if (getIntent().hasExtra(UrlContainer.TRANSFER_STRING)) {
            TITLE = getIntent().getStringExtra(UrlContainer.TRANSFER_STRING);
            getSupportActionBar().setTitle(TITLE);
        }

        binding.radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_mr) {
                    title = "Mr.";
                } else if (checkedId == R.id.rb_mrs) {
                    title = "Mrs.";
                } else if (checkedId == R.id.rb_miss) {
                    title = "Miss.";
                }
            }
        });
        binding.ivLocation.setOnClickListener(view -> {

            LocationDialogFragment bf = new LocationDialogFragment((address, latitude, longitude) -> {
                binding.etLocality.setText(address);
            });
           // bf.show(getSupportFragmentManager(), bf.getTag());
        });
        binding.btnSaveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gotoNextActivity(MapsActivity.class, false);
                if (binding.etName.getText().toString().equals("")){
                    binding.etName.requestFocus();
                    binding.etName.setError("Please enter name");
                }else if (binding.etPhone.getText().toString().equals("")){
                    binding.etPhone.requestFocus();
                    binding.etPhone.setError("Please enter mobile number");
                }else if (binding.etPhone.getText().toString().length()<10){
                    binding.etPhone.requestFocus();
                    binding.etPhone.setError("Please enter valid mobile number");
                }else if (binding.etFlat.getText().toString().equals("")){
                    binding.etFlat.requestFocus();
                    binding.etFlat.setError("Please enter flat number");
                }else if (binding.etLocality.getText().toString().equals("")){
                    binding.etLocality.requestFocus();
                    binding.etLocality.setError("Please enter locality");
                }else if (binding.etPincode.getText().toString().equals("")){
                    binding.etPincode.requestFocus();
                    binding.etPincode.setError("Please enter pincode");
                }else {
                    if (addressPOJO==null) {
                       // saveAddress();
                    }else {
                        //updateAddress();
                    }
                }


            }
        });
        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ADDRESS_TYPE = HOME;
                unselected(binding.btnOthers);
                unselected(binding.btnOffice);
                selected(binding.btnHome);


            }
        });
        binding.btnOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ADDRESS_TYPE = OFFICE;
                unselected(binding.btnOthers);
                selected(binding.btnOffice);
                unselected(binding.btnHome);


            }
        });
        binding.btnOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ADDRESS_TYPE = OTHERS;
               selected(binding.btnOthers);
               unselected(binding.btnOffice);
               unselected(binding.btnHome);

            }
        });

        loadDataToCity();
        binding.etLocality.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("beofre", "before");
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("textch", "before");
            }
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("afterchanged", "before");
                            GeocodingLocation locationAddress = new GeocodingLocation();
                            locationAddress.getAddressFromLocation(binding.etLocality.getText().toString(),
                                    AddAddressActivity.this, new GeocoderHandlerto());
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        });
        try {
            addressPOJO = (AddressPOJO) getIntent().getSerializableExtra(UrlContainer.TRANSFER_MODEL);
            if (addressPOJO != null && addressPOJO.address!=null) {
                UtilityFunction.checkEditTextSetValue(addressPOJO.name, binding.etName);
                UtilityFunction.checkEditTextSetValue(addressPOJO.mobileNo, binding.etPhone);
                UtilityFunction.checkEditTextSetValue(addressPOJO.houseNo, binding.etFlat);
                UtilityFunction.checkEditTextSetValue(addressPOJO.streetNo, binding.etStreet);
                UtilityFunction.checkEditTextSetValue(addressPOJO.address, binding.etLocality);
                UtilityFunction.checkEditTextSetValue(addressPOJO.pinCode,binding.etPincode);
                if (addressPOJO.addressType.equalsIgnoreCase("Office")){
                    binding.btnOffice.performClick();
                }
                if (addressPOJO.addressType.equalsIgnoreCase("Home")){
                    binding.btnHome.performClick();
                }
                if (addressPOJO.addressType.equalsIgnoreCase("Others")){
                    binding.btnOthers.performClick();
                }
                if (addressPOJO.title.equalsIgnoreCase("Mr.")){
                    binding.rbMr.setChecked(true);
                }
                if (addressPOJO.title.equalsIgnoreCase("Mrs.")){
                    binding.rbMrs.setChecked(true);
                }
                if (addressPOJO.title.equalsIgnoreCase("Miss.")){
                    binding.rbMiss.setChecked(true);
                }



            }else {
                setTitle("Add Address");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void selected(TextView button){
        button.setBackgroundResource(R.drawable.rectangular_selected);
        button.setTextColor(getResources().getColor(R.color.white));
    }
    private void unselected(TextView button){
        button.setBackgroundResource(R.drawable.rectangular);
        button.setTextColor(getResources().getColor(R.color.purple_500));
    }



    public static final int ADDRESS_REQUEST_CODE=123;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK && requestCode==ADDRESS_REQUEST_CODE && data!=null){
            binding.etLocality.setText(data.getStringExtra("address"));
        }
    }

    private void loadDataToCity() {
        List<Prediction> predictions = new ArrayList<>();
        PlacesAutoCompleteAdapter placesAutoCompleteAdapter = new PlacesAutoCompleteAdapter(getApplicationContext(), predictions);
        binding.etLocality.setThreshold(1);
        binding.etLocality.setAdapter(placesAutoCompleteAdapter);
        //dtocity.setText();
//      Log.d("PlaceId",Utility.prediction.getPlaceId());
        //placesAutoCompleteAdapter.getItem();
        // autoCompleteTextViewPlace.setOn
    }

    class GeocoderHandlerto extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            //Toast.makeText(Customerinformationplus.this,""+locationAddress,Toast.LENGTH_LONG);
            Log.d("logi", locationAddress);

            try {
                int i = locationAddress.length();
                String[] address = locationAddress.split(",", i);
                if (address.length > 3) {

                    //dstate.setText(address[1]);
                    //dcountry.setText(address[2]);
                    // dzip.setText(address[3]);
                    // last1 = address[4];
                    // last2 = address[5];
                    if (address[3]!=null && !address[3].startsWith("null")){
                        binding.etPincode.setText(address[3]);
                    }
                    latitude = Double.parseDouble(address[4]);
                    longitude = Double.parseDouble(address[5]);


                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            // auto_to_city.setText(address[0]);
            //   daddress.setText(dcity.getText().toString() + ", " + dstate.getText().toString() + ", " + dcountry.getText().toString());


        }
    }


    double latitude = 0, longitude = 0;
    String title = "Mr";

  /*  private void saveAddress() {
        try {
            if (UtilityFunction.isNetworkConnected(this)) {
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("title",title);
                hashMap.put("name",binding.etName.getText().toString());
                hashMap.put("mobile",binding.etPhone.getText().toString());
                hashMap.put("address1",binding.etFlat.getText().toString());
                hashMap.put("address2",binding.etStreet.getText().toString());
                hashMap.put("locality",binding.etLocality.getText().toString());
                hashMap.put("pin_code",binding.etPincode.getText().toString());
                hashMap.put("address_as",ADDRESS_TYPE);
                hashMap.put("latitude",latitude+"");
                hashMap.put("longitude",longitude+"");
                UtilityFunction.showLoading(context,"Please wait...");

                ApiExecutor.getApiService().postData(
                        "Bearer "+ PrefManager.GetLoginData(context).token,
                        UrlContainer.ADD_ADDRESS,hashMap).enqueue(new Callback<OtpResponse>() {
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
                            Toast.makeText(context, response.body().msg, Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }else {
                            Toast.makeText(context, response.body().msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<OtpResponse> call, Throwable t) {
                        UtilityFunction.hideLoading();
                    }
                });
            } else {
                Toast.makeText(this, "Network is not availble", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateAddress() {
        try {
            if (UtilityFunction.isNetworkConnected(this)) {
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("id",addressPOJO.addressID+"");
                hashMap.put("title",title);
                hashMap.put("name",binding.etName.getText().toString());
                hashMap.put("mobile",binding.etPhone.getText().toString());
                hashMap.put("address1",binding.etFlat.getText().toString());
                hashMap.put("address2",binding.etStreet.getText().toString());
                hashMap.put("locality",binding.etLocality.getText().toString());
                hashMap.put("pin_code",binding.etPincode.getText().toString());
                hashMap.put("address_as",ADDRESS_TYPE);
                hashMap.put("latitude",latitude+"");
                hashMap.put("longitude",longitude+"");
                UtilityFunction.showLoading(context,"Please wait...");

                ApiExecutor.getApiService().postData(
                        "Bearer "+ PrefManager.GetLoginData(context).token,
                        UrlContainer.UPDATE_ADDRESS,hashMap).enqueue(new Callback<OtpResponse>() {
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
                            Toast.makeText(context, response.body().msg, Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }else {
                            Toast.makeText(context, response.body().msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<OtpResponse> call, Throwable t) {
                        UtilityFunction.hideLoading();
                    }
                });
            } else {
                Toast.makeText(this, "Network is not availble", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


}
