package com.example.fishmarket.model;

import com.example.fishmarket.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SubCategoryPOJO implements Serializable {
  /*  "sub_category": [
    {
        "id": 16,
            "category_id": 12,
            "sub_category_name": "FABRICATION MATERIAL",
            "slug": "fabrication-material",
            "image": null,
            "status": 1,
            "created_at": "2022-06-22T09:07:51.000000Z",
            "updated_at": "2022-06-22T09:07:51.000000Z"
    }*/
    @SerializedName("sub_category_name")
    @Expose
    public String name;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("image")
    @Expose
    public String image;

    public int placeholder= R.drawable.placeholder;
    @SerializedName("status")
    @Expose
    public int status;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("category_id")
    @Expose
    public int category_id;
    boolean isSelected;
    public boolean getSelected() {
    return isSelected;
  }

    public void setSelected(boolean selected) {
    isSelected = selected;
  }
  public Boolean isExpand;
  public ArrayList<SubCategoryPOJO> subCategoryPOJOS;

}
