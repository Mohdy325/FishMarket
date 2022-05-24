package com.example.fishmarket.view.bottom_dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.fishmarket.R;
import com.example.fishmarket.databinding.FragLocationDialogBinding;
import com.example.fishmarket.search_autocomplete.GeocodingLocation;
import com.example.fishmarket.search_autocomplete.PlacesAutoCompleteAdapter;
import com.example.fishmarket.search_autocomplete.Prediction;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class LocationDialogFragment extends BottomSheetDialogFragment {
    FragLocationDialogBinding binding;
    //create custom theme for your bottom sheet modal
    Context context;
    public interface OnAddressChoose{
        void onChooseAddress(String address,Double latitude,Double longitude);
    }
    OnAddressChoose addressChoose;

    public LocationDialogFragment(OnAddressChoose addressChoose) {
        this.addressChoose = addressChoose;
    }

    @Override
    public int getTheme() {
        //return super.getTheme();
        return R.style.AppBottomSheetDialogTheme;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        return new BottomSheetDialog(requireContext(), getTheme());  //set your created theme here

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         binding= FragLocationDialogBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view1, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view1, savedInstanceState);


        View contentView = view1;
        context = contentView.getContext();
        //tv_title.setText(getString(R.string.app_name)); R.style.AppBottomSheetDialogTheme
        binding.ivBack.setOnClickListener(view -> dismiss());
        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        int maxHeight = (int) (height*0.7); //custom height of bottom sheet

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();
        ((BottomSheetBehavior) behavior).setPeekHeight(maxHeight);  //changed default peek height of bottom sheet

        if (behavior != null && behavior instanceof BottomSheetBehavior)
        {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback()
            {

                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState)
                {
                    String state = "";
                    switch (newState)
                    {
                        case BottomSheetBehavior.STATE_DRAGGING: {
                            //imgBtnClose.setVisibility(View.INVISIBLE);
                            state = "DRAGGING";
                            break;
                        }
                        case BottomSheetBehavior.STATE_SETTLING: {
                            // imgBtnClose.setVisibility(View.INVISIBLE);
                            state = "SETTLING";
                            break;
                        }
                        case BottomSheetBehavior.STATE_EXPANDED: {
                            // imgBtnClose.setVisibility(View.VISIBLE);
                            state = "EXPANDED";
                            break;
                        }
                        case BottomSheetBehavior.STATE_COLLAPSED: {
                            //imgBtnClose.setVisibility(View.INVISIBLE);
                            state = "COLLAPSED";
                            break;
                        }
                        case BottomSheetBehavior.STATE_HIDDEN: {
                            // imgBtnClose.setVisibility(View.INVISIBLE);
                            dismiss();
                            state = "HIDDEN";
                            break;
                        }
                    }
                    Log.i("BottomSheetFrag", "onStateChanged: "+ state);
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });
        }

        loadDataToCity();
        binding.etAddress.addTextChangedListener(new TextWatcher() {
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
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("afterchanged", "before");
                            GeocodingLocation locationAddress = new GeocodingLocation();
                            locationAddress.getAddressFromLocation(binding.etAddress.getText().toString(),
                                    getContext(), new GeocoderHandlerto());
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        });

        //close dialog
        //iv_closeDialog.setOnClickListener(view -> dismiss());

    }


    private void loadDataToCity() {
        List<Prediction> predictions = new ArrayList<>();
        PlacesAutoCompleteAdapter placesAutoCompleteAdapter = new PlacesAutoCompleteAdapter(getActivity(), predictions);
        binding.etAddress.setThreshold(1);
        binding.etAddress.setAdapter(placesAutoCompleteAdapter);


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
                  /*  if (address[0]!=null && !address[0].startsWith("null")){
                        binding.etCity.setText(address[0]);
                    }
                    if (address[1]!=null && !address[1].startsWith("null")){
                        binding.etState.setText(address[1]);
                    }
                    if (address[2]!=null && !address[2].startsWith("null")){
                        binding.etCountry.setText(address[2]);
                    }
                    if (address[3]!=null && !address[3].startsWith("null")){
                        binding.etZipcode.setText(address[3]);
                    }*/
                    latitude = Double.parseDouble(address[4]);
                    longitude = Double.parseDouble(address[5]);
                    addressChoose.onChooseAddress(binding.etAddress.getText().toString(),latitude,longitude);
                    dismiss();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            // auto_to_city.setText(address[0]);
            //   daddress.setText(dcity.getText().toString() + ", " + dstate.getText().toString() + ", " + dcountry.getText().toString());


        }
    }


    double latitude = 0, longitude = 0;
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
