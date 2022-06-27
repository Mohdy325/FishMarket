package com.example.fishmarket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryPOJO implements Serializable {

   /* {
        "id": 15,
            "category_name": "FISH FEED",
            "slug": "fish-feed",
            "image": "uploads/category_image/1655908409.jpg",
            "status": 1,
            "created_at": "2022-06-22T09:03:29.000000Z",
            "updated_at": "2022-06-22T09:03:29.000000Z"
    }*/
    @SerializedName("category_name")
    @Expose
    public String name;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("status")
    @Expose
    public int status;
    @SerializedName("id")
    @Expose
    public int id;
    boolean isSelected;

    public CategoryPOJO(String name) {
        this.name = name;
    }
    public CategoryPOJO(String name,int image) {
        this.name = name;
        this.id=image;
    }
    public Boolean isExpand;
    public ArrayList<SubCategoryPOJO> subCategoryPOJOS;

    public CategoryPOJO(Boolean isExpand, ArrayList<SubCategoryPOJO> subCategoryPOJOS) {
        this.isExpand = isExpand;
        this.subCategoryPOJOS = subCategoryPOJOS;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
