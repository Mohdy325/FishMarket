package com.example.fishmarket.model;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;
import java.io.Serializable;

public class ImagesPOJO implements Serializable {
    public File file;
    public Uri uri;
    public Bitmap bitmap;

}
