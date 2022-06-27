package com.example.fishmarket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartPOJO implements Serializable {
    /*public int qty;
    public String productName;

    public CartPOJO(int qty, String productName) {
        this.qty = qty;
        this.productName = productName;
    }
    */
    @SerializedName("product_id")
    @Expose
    public String productId;
    @SerializedName("product_name")
    @Expose
    public String productName;
    @SerializedName("product_image")
    @Expose
    public String productImage;
    @SerializedName("variant_id")
    @Expose
    public String variantId;
    public String potencyId;
   /* @SerializedName("variantItem")
    @Expose
    public VariantPOJO variantItem;
    public VariantPOJO potencyItem;*/

    @SerializedName("product_quantity")
    @Expose
    public String productQuantity;


    @SerializedName("status")
    @Expose
    public String status;
}
