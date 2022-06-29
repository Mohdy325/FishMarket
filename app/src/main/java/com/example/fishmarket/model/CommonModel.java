package com.example.fishmarket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CommonModel implements Serializable {

    @SerializedName("status")
    @Expose
    public boolean status;
    public boolean isLogin;
    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("user")
    @Expose
    public LoginPOJO user;

    @SerializedName("category")
    @Expose
    public ArrayList<CategoryPOJO> categoryPOJOS;
    @SerializedName("slider")
    @Expose
    public ArrayList<CategoryPOJO> slider;
    @SerializedName("in_trending")
    @Expose
    public ArrayList<ProductPOJO> in_trending;
    @SerializedName("product")
    @Expose
    public ArrayList<ProductPOJO> productPOJOS;
    @SerializedName("favourite")
    @Expose
    public ArrayList<ProductPOJO> favourite;

    @SerializedName("product_details")
    @Expose
    public ProductPOJO product_details;


    @SerializedName("sub_category")
    @Expose
    public ArrayList<SubCategoryPOJO> sub_category;
}
