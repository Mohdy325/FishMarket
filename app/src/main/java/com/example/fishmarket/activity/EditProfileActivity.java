package com.example.fishmarket.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;


import com.example.fishmarket.R;
import com.example.fishmarket.databinding.ActivityEditProfileBinding;
import com.example.fishmarket.search_autocomplete.GeocodingLocation;
import com.example.fishmarket.search_autocomplete.PlacesAutoCompleteAdapter;
import com.example.fishmarket.search_autocomplete.Prediction;
import com.example.fishmarket.utils.BaseActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends BaseActivity {
    ActivityEditProfileBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.tvTitle.setText("Edit Profile");
        binding.tvChange.setOnClickListener(view -> {
            if (hasPermission()){
                SelectImage();
            }else {
                checkPermission();
            }
        });

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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("afterchanged", "before");
                            GeocodingLocation locationAddress = new GeocodingLocation();
                            locationAddress.getAddressFromLocation(binding.etAddress.getText().toString(),
                                    EditProfileActivity.this, new GeocoderHandlerto());
                        }
                    });
                    if (s.toString().length()>0){
                        binding.ivLocation.setVisibility(View.GONE);
                    }else {
                        binding.ivLocation.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        });

    }

    private void SelectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        // final CharSequence[] options = {"Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                )
                {
                    ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},200);
                    return;
                }

                if (options[which].equals("Take Photo")) {


                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 1);

                } if (options[which].equals("Choose from Gallery")) {

                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 2);
                  //  }
                } else if (options[which].equals("Cancel")) {
                    dialog.dismiss();
                }

            }
        });
        builder.show();
    }

    private static final String[] permissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    public boolean hasPermission() {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    public boolean checkPermission(){
        // ActivityCompat.requestPermissions(activity,permissions,PIC_CROP_REQUEST);
        if (hasPermission()){
            return true;
        }else {
            checkPermission();
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PIC_CROP_REQUEST :
                if (hasPermission()){
                    SelectImage();
                }else {
                    checkPermission();
                }
                break;
        }
    }

    private static final int PIC_CROP_REQUEST = 10;
    Bitmap bitmap;
    Uri filePath;
    Bitmap coverBitmap;
    Uri coverFilePath;

    String FileType="";
    String COVER_IMAGE="CoverImage";
    String IMAGES="Images";
    String VIDEOS="Videos";


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {
                bitmap = (Bitmap) data.getExtras().get("data");
                File file1 = bitmapToFile(bitmap, getString(R.string.app_name) + "_" + System.currentTimeMillis() + ".jpg");
                filePath = Uri.fromFile(file1);



                    binding.imageView.setImageBitmap(bitmap);
                    binding.tvRemove.setOnClickListener(view -> {
                        filePath=null;
                        bitmap=null;
                        binding.imageView.setImageResource(R.drawable.ic_user_placeholder);
                    });
              //  }

                Log.e("1258963", bitmap + "");

            } else if (requestCode == 2) {
                filePath = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), filePath);


                    binding.imageView.setImageBitmap(bitmap);
                    binding.tvRemove.setOnClickListener(view -> {
                        filePath=null;
                        bitmap=null;
                        binding.imageView.setImageResource(R.drawable.ic_user_placeholder);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else
            if (requestCode == PIC_CROP_REQUEST && data != null) {

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    Bundle extras = data.getExtras();
                    bitmap = extras.getParcelable("data");
                } else {

                    Uri uri = data.getData();

                    if (uri != null) {

                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
                            filePath = uri;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {

                        Bundle extras = data.getExtras();
                        bitmap = extras.getParcelable("data");
                    }
                }

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                byte[] byteArrayImage = bytes.toByteArray();

                //  encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
                //Log.e("encodedImage ", "encodedImage " + encodedImage);


            }

        }







       /* if (resultCode == RESULT_OK) {

            if (requestCode == 1) {
                bitmap = (Bitmap) data.getExtras().get("data");


                Log.e("1258963", bitmap + "");
                File file1 = bitmapToFile(bitmap, getString(R.string.app_name) + "_" + System.currentTimeMillis() + ".jpg");
                filePath = Uri.fromFile(file1);
                performCrop(filePath);
                if (CLICK_ON.equals(FIRST_IMAGE)) {
                    bitmap1 = bitmap;
                    iv_UploadImage.setImageBitmap(bitmap);
                } else if (CLICK_ON.equals(SECOND_IMAGE)) {
                    bitmap2 = bitmap;
                    iv_image2.setImageBitmap(bitmap);
                } else if (CLICK_ON.equals(THIRDE_IMAGE)) {
                    bitmap3 = bitmap;
                    iv_image3.setImageBitmap(bitmap);
                } else if (CLICK_ON.equals(FOURTH_IMAGE)) {
                    bitmap4 = bitmap;
                    iv_image4.setImageBitmap(bitmap);
                }

            } else if (requestCode == 2) {
                filePath = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(MeterialAddActivity.this.getContentResolver(), filePath);

                    //iv_UploadImage.setImageBitmap(bitmap);


                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (CLICK_ON.equals(FIRST_IMAGE)) {
                    bitmap1 = bitmap;
                    filePath1 = filePath;
                    iv_UploadImage.setImageBitmap(bitmap);
                } else if (CLICK_ON.equals(SECOND_IMAGE)) {
                    bitmap2 = bitmap;
                    filePath2 = filePath;
                    iv_image2.setImageBitmap(bitmap);
                } else if (CLICK_ON.equals(THIRDE_IMAGE)) {
                    bitmap3 = bitmap;
                    filePath3 = filePath;
                    iv_image3.setImageBitmap(bitmap);
                } else if (CLICK_ON.equals(FOURTH_IMAGE)) {
                    bitmap4 = bitmap;
                    filePath4 = filePath;
                    iv_image4.setImageBitmap(bitmap);
                }
                performCrop(filePath);
            } else if (requestCode == PIC_CROP_REQUEST && data != null) {

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    Bundle extras = data.getExtras();
                    bitmap = extras.getParcelable("data");
                } else {

                    Uri uri = data.getData();

                    if (uri != null) {

                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
                            filePath = uri;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {

                        Bundle extras = data.getExtras();
                        bitmap = extras.getParcelable("data");
                    }
                }

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                byte[] byteArrayImage = bytes.toByteArray();

                if (CLICK_ON.equals(FIRST_IMAGE)) {
                    bitmap1 = bitmap;
                    filePath1 = filePath;
                    iv_UploadImage.setImageBitmap(bitmap);
                } else if (CLICK_ON.equals(SECOND_IMAGE)) {
                    bitmap2 = bitmap;
                    filePath2 = filePath;
                    iv_image2.setImageBitmap(bitmap);
                } else if (CLICK_ON.equals(THIRDE_IMAGE)) {
                    bitmap3 = bitmap;
                    filePath3 = filePath;
                    iv_image3.setImageBitmap(bitmap);
                } else if (CLICK_ON.equals(FOURTH_IMAGE)) {
                    bitmap4 = bitmap;
                    filePath4 = filePath;
                    iv_image4.setImageBitmap(bitmap);
                }
                //  encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
                //Log.e("encodedImage ", "encodedImage " + encodedImage);
                // iv_UploadImage.setImageBitmap(bitmap);


            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    Bitmap photo1;
                    try {
                        photo1 = MediaStore.Images.Media.getBitmap(context.getContentResolver(), resultUri);

                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        photo1.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                        byte[] byteArrayImage = bytes.toByteArray();
                        //encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
                        filePath = resultUri;
                        bitmap = photo1;
                        //iv_UploadImage.setImageBitmap(photo1);
                        // ProfileFragment.iv_profile.setImageBitmap(photo1);

                        ///GEt PAth for Crop image

                        String imageurl = MediaStore.Images.Media.insertImage(context.getContentResolver(), photo1, getString(R.string.app_name) + "_" + System.currentTimeMillis(), null);
                        //Log.e("captured uri", imagepath);

                        String[] projection = {MediaStore.Images.Media.DATA};
                        Cursor cursor = context.getContentResolver().query(Uri.parse(imageurl), projection, null, null, null);
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        cursor.moveToFirst();
                        imageurl = cursor.getString(column_index);//cursor.getString(column_index);
                        Log.e("imagepath ", "imagepath " + imageurl);
                        Log.e("captured uri", imageurl);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (CLICK_ON.equals(FIRST_IMAGE)) {
                        bitmap1 = bitmap;
                        filePath1 = filePath;
                        iv_UploadImage.setImageBitmap(bitmap);
                    } else if (CLICK_ON.equals(SECOND_IMAGE)) {
                        bitmap2 = bitmap;
                        filePath2 = filePath;
                        iv_image2.setImageBitmap(bitmap);
                    } else if (CLICK_ON.equals(THIRDE_IMAGE)) {
                        bitmap3 = bitmap;
                        filePath3 = filePath;
                        iv_image3.setImageBitmap(bitmap);
                    } else if (CLICK_ON.equals(FOURTH_IMAGE)) {
                        bitmap4 = bitmap;
                        filePath4 = filePath;
                        iv_image4.setImageBitmap(bitmap);
                    }
                    //updateImage();

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                    error.printStackTrace();

                }
            }
        }*/


    }

    public File bitmapToFile(Bitmap bitmap, String fileNameToSave) { // File name like "image.png"
        //create a file to write bitmap data
        File file = null;
        try {
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + fileNameToSave);
            file.createNewFile();

//Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos); // YOU can also save it in JPEG
            byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return file; // it will return null
        }
    }



    private void loadDataToCity() {
        List<Prediction> predictions = new ArrayList<>();
        PlacesAutoCompleteAdapter placesAutoCompleteAdapter = new PlacesAutoCompleteAdapter(getApplicationContext(), predictions);
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
                    if (address[0]!=null && !address[0].startsWith("null")){
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
}
