package com.example.fishmarket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductPOJO implements Serializable {
    public String name;
    public String dummy="Genetically Imroved Katla";
    public ProductPOJO(){

    }

    public ProductPOJO(String name) {
        this.name = name;
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("used_for")
    @Expose
    private String usedFor;
    @SerializedName("specifications")
    @Expose
    private String specifications;
    @SerializedName("market_price")
    @Expose
    private Integer marketPrice;
    @SerializedName("sale_price")
    @Expose
    private Integer salePrice;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("rating")
    @Expose
    private Integer rating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUsedFor() {
        return usedFor;
    }

    public void setUsedFor(String usedFor) {
        this.usedFor = usedFor;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
    /*{
        "id":5,
            "product_name":"ELECTRICAL PUMPS",
            "slug":"test-product-2",
            "company":"test product",
            "used_for":"test product",
            "specifications":"test product",
            "market_price":123,
            "sale_price":122,
            "size":"sdf",
            "weight":"sdf",
            "city":"Lucknow",
            "state":"Uttar Pradesh",
            "country":"India",
            "pincode":"226001",
            "latitude":"26.8466937",
            "longitude":"80.946166",
            "image":"uploads/product_image/1655986443.jpg",
            "rating":3,

    }*/
    }
