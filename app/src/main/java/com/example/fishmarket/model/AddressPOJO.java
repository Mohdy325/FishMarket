package com.example.fishmarket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddressPOJO implements Serializable {
/*    {"id":2,"title":"Mr.","name":"Yaseen Official","mobile":"8126044585","address1":"586","address2":"Rampur Bazpur Road","locality":"Doondawala Mustakam","pin_code":"244924",
            "address_as":"Mr.","latitude":"Mr.","longitude":"Mr."}]}
            */


    @SerializedName("id")
    @Expose
    public int addressID;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("name")
    @Expose
    public String name;
     @SerializedName("mobile")
    @Expose
    public String mobileNo;
    @SerializedName("address1")
    @Expose
    public String houseNo;
    @SerializedName("address2")
    @Expose
    public String streetNo;
    @SerializedName("locality")
    @Expose
    public String address;
    @SerializedName("address_as")
    @Expose
    public String addressType;
    @SerializedName("aD_User_ID")
    @Expose
    public int aDUserID;
    @SerializedName("pin_code")
    @Expose
    public String pinCode;
    @SerializedName("Street_No")
    @Expose
    public String phone;
    @SerializedName("latitude")
    @Expose
    public String latitude;
    @SerializedName("longitude")
    @Expose
    public String longitude;


    @SerializedName("full_Address")
    @Expose
    public String full_Address;
    @SerializedName("isActive")
    @Expose
    public boolean isActive;
}
