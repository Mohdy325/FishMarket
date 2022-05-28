package com.example.fishmarket.view;

import static android.app.Activity.RESULT_OK;

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
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.fishmarket.R;
import com.example.fishmarket.adapter.AddedImageAdapter;
import com.example.fishmarket.databinding.FragSellBinding;
import com.example.fishmarket.model.ImagesPOJO;
import com.example.fishmarket.utils.BaseFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SellFragment extends BaseFragment {
FragSellBinding binding;
String[] typeOfSupplier={"Select Supply Type","Locally","All India"};
String[] typeOfFish={"Select Type of Fish","Papda","Red Tilapia"};
String[] sizeOfFish={"Select Size of Fish","Spawn","Zero","1 Inches","2 Inches","3 Inches"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragSellBinding.inflate(inflater,container,false);
        binding.spTypeOfFish.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,typeOfFish));
        binding.spSizeOfFish.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,sizeOfFish));
        binding.flAddImage.setOnClickListener(view -> {
            if (hasPermission()){
                SelectImage();
            }else {
                checkPermission();
            }
        });

        return binding.getRoot();
    }
    ArrayList<ImagesPOJO> imagesPOJOS;
    AddedImageAdapter imageAdapter;
    ArrayList<ImagesPOJO> videoList;
    private void setUpImageList(){
        if (imageAdapter==null){
            imageAdapter=new AddedImageAdapter(context,imagesPOJOS, position -> {
                imagesPOJOS.remove(position);
                imageAdapter.notifyDataSetChanged();
            });
            binding.rvImageList.setAdapter(imageAdapter);
        }else {
            imageAdapter.notifyDataSetChanged();
        }
    }
    private void SelectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        // final CharSequence[] options = {"Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
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

                    if (FileType.equals(VIDEOS)){
                        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        startActivityForResult(intent, 3);
                    }else {
                        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 1);
                    }
                } if (options[which].equals("Choose from Gallery")) {
                    if (FileType.equals(VIDEOS)){
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 4);
                    }else {
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 2);
                    }
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

                    ImagesPOJO imagesPOJO=new ImagesPOJO();
                    imagesPOJO.bitmap=bitmap;
                    imagesPOJO.file=file1;
                    imagesPOJO.uri=filePath;
                    if (imagesPOJOS==null || imagesPOJOS.size()==0){
                        imagesPOJOS=new ArrayList<>();
                    }
                    imagesPOJOS.add(imagesPOJO);
                    setUpImageList();


                Log.e("1258963", bitmap + "");

            } else if (requestCode == 2) {
                filePath = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), filePath);

                        ImagesPOJO imagesPOJO=new ImagesPOJO();
                        imagesPOJO.bitmap=bitmap;
                        imagesPOJO.uri=filePath;
                        if (imagesPOJOS==null || imagesPOJOS.size()==0){
                            imagesPOJOS=new ArrayList<>();
                        }
                        imagesPOJOS.add(imagesPOJO);
                        setUpImageList();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else
            if (requestCode == 3) {
                //bitmap = (Bitmap) data.getExtras().get("data");
                // File file1 = bitmapToFile(bitmap, getString(R.string.app_name) + "_" + System.currentTimeMillis() + ".jpg");
                filePath = data.getData();//Uri.fromFile(file1);
                ImagesPOJO imagesPOJO=new ImagesPOJO();
                imagesPOJO.bitmap=bitmap;
                //  imagesPOJO.file=file1;
                imagesPOJO.uri=filePath;
                if (videoList==null || videoList.size()==0){
                    videoList=new ArrayList<>();
                }
                videoList.add(imagesPOJO);
              //  setUpVideoList();
                Log.e("1258963", bitmap + "");

            } else if (requestCode == 4) {
                filePath = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), filePath);
                    ImagesPOJO imagesPOJO=new ImagesPOJO();
                    imagesPOJO.bitmap=bitmap;
                    imagesPOJO.uri=filePath;
                    if (videoList==null || videoList.size()==0){
                        videoList=new ArrayList<>();
                    }
                    videoList.add(imagesPOJO);
                    //setUpVideoList();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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

            /*else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
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
                        iv_UploadImage.setImageBitmap(photo1);
                        // ProfileFragment.iv_profile.setImageBitmap(photo1);
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

                    //updateImage();

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                    error.printStackTrace();

                }
            }
*/
        }

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
}

