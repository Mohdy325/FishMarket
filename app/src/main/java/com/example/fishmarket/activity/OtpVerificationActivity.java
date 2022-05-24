package com.example.fishmarket.activity;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import com.example.fishmarket.MainActivity;
import com.example.fishmarket.databinding.ActivityOtpVerificationBinding;
import com.example.fishmarket.utils.BaseActivity;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.TimeUnit;

public class OtpVerificationActivity extends BaseActivity {
    ActivityOtpVerificationBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOtpVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvVerify.setOnClickListener(view -> {
            //if(getIntent().hasExtra(UrlContainer.TRANSFER_MODEL))
                startActivity(goTo(MainActivity.class));
          /*  else if(getIntent().hasExtra(UrlContainer.TRANSFER_ID))
                startActivity(goTo(ChangePasswordActivity.class).putExtra(UrlContainer.TRANSFER_ID,"Change"));
            else
                startActivity(goTo(ChangePasswordActivity.class));*/
        });

        binding.tvResendCode.setOnClickListener(view -> {
            startCountDown();
        });
        startCountDown();

        binding.etOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length()>0){
                    binding.etOtp2.requestFocus();
                }
            }
        });
        binding.etOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length()>0){
                    binding.etOtp3.requestFocus();
                }else {
                    binding.etOtp1.requestFocus();
                }
            }
        });
        binding.etOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length()>0){
                    binding.etOtp4.requestFocus();
                }else {
                    binding.etOtp2.requestFocus();
                }
            }
        });
        binding.etOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length()>0){
                    binding.etOtp5.requestFocus();
                }else {
                    binding.etOtp3.requestFocus();
                }
            }
        });
        binding.etOtp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length()>0){
                    binding.etOtp6.requestFocus();
                }else {
                    binding.etOtp4.requestFocus();
                }
            }
        });
        binding.etOtp6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length()>0){
                    //binding.etOtp3.requestFocus();
                }else {
                    binding.etOtp5.requestFocus();
                }
            }
        });

    }



    //private TextView tv_resend_code, tv_OtpTimer;
    private CountDownTimer countDownTimer;

    private void startCountDown() {

        binding.tvOtpTimer.setVisibility(View.VISIBLE);
        binding.tvResendCode.setVisibility(View.GONE);
        countDownTimer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                //tv_resend_code.setVisibility(View.VISIBLE);

                //tv_Timer.setText("Resend OTP in: " + millisUntilFinished / 1000);
                binding.tvOtpTimer.setText("" + String.format("%d:%d sec",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {

                binding.tvOtpTimer.setVisibility(View.GONE);
                binding.tvResendCode.setVisibility(View.VISIBLE);
            }

        }.start();

    }

    @Override
    public void onResume() {

        Task<Void> task = SmsRetriever.getClient(context).startSmsUserConsent(null);
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.e("succcessss", "done");

            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("failerrr", e.toString());
            }
        });

        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        context.registerReceiver(smsVerificationReceiver, intentFilter);

        super.onResume();

    }

    private BroadcastReceiver smsVerificationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
                Bundle extras = intent.getExtras();
                Status smsRetrieverStatus = (Status) extras.get(SmsRetriever.EXTRA_STATUS);

                switch (smsRetrieverStatus.getStatusCode()) {
                    case CommonStatusCodes.SUCCESS:
                        // Get consent intent
                        Intent consentIntent = extras.getParcelable(SmsRetriever.EXTRA_CONSENT_INTENT);
                        try {
                            // Start activity to show consent dialog to user, activity must be started in
                            // 5 minutes, otherwise you'll receive another TIMEOUT intent
                            startActivityForResult(consentIntent, SMS_CONSENT_REQUEST);
                        } catch (ActivityNotFoundException e) {
                            // Handle the exception ...
                        }
                        break;
                    case CommonStatusCodes.TIMEOUT:
                        // Time out occurred, handle the error.
                        break;
                }
            }
        }
    };


    @Override
    public void onPause() {
        super.onPause();
        try {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(smsVerificationReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final int SMS_CONSENT_REQUEST = 2;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SMS_CONSENT_REQUEST) {
            if (resultCode == RESULT_OK) {
                // Get SMS message content
                assert data != null;
                String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                // Extract one-time code from the message and complete verification
                // `sms` contains the entire text of the SMS message, so you will need
                // to parse the string.
                String oneTimeCode = parseOneTimeCode(message); // define this function

                assert oneTimeCode != null;
                String fullMsg = oneTimeCode.replaceAll("[^0-9]", "");

                try {
                    //binding.etOtp.setText(fullMsg);
                    char o1 = fullMsg.charAt(0);
                    char o2 = fullMsg.charAt(1);
                    char o3 = fullMsg.charAt(2);
                    char o4 = fullMsg.charAt(3);
                    char o5 = fullMsg.charAt(4);
                    char o6 = fullMsg.charAt(5);


                    binding.etOtp1.setText(o1+"");
                    binding.etOtp2.setText(o2+"");
                    binding.etOtp3.setText(o3+"");
                    binding.etOtp4.setText(o4+"");
                    binding.etOtp5.setText(o5+"");
                    binding.etOtp6.setText(o6+"");
                    // et_otp.setText(o1 + o2 + o3 + o4 + o5 + o6);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //verifyOtp();
                //saveUserVerified();

                Log.e("codeeeee", oneTimeCode.replaceAll("[^0-9]", ""));

                //   ToastMessage.makeText(getActivity(),oneTimeCode,ToastMessage.LENGTH_SHORT).show();
                // send one time code to the server
            } else {
                // Consent canceled, handle the error ...
            }
        }
    }

    private String parseOneTimeCode(String message) {
        return message;
    }
}
