package com.example.fishmarket.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryPOJO implements Serializable {
    public String name;
    public int image;
    boolean isSelected;

    public CategoryPOJO(String name) {
        this.name = name;
    }
    public CategoryPOJO(String name,int image) {
        this.name = name;
        this.image=image;
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
