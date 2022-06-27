package com.example.fishmarket.model;

import android.graphics.Bitmap;
import android.net.Uri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.Serializable;

public class ImagesPOJO implements Serializable {
    public File file;
    public Uri uri;
    public Bitmap bitmap;
   /* {
        "id": 6,
            "category_id": 12,
            "title": "test slider",
            "image": "uploads/slider_image/1656319622.jpg",
            "status": 1,
            "created_at": "2022-06-27T08:47:02.000000Z",
            "updated_at": "2022-06-27T08:47:02.000000Z"
    }*/

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("category_id")
    @Expose
    public int category_id;
    @SerializedName("status")
    @Expose
    public int status;
    @SerializedName("id")
    @Expose
    public int id;
}
