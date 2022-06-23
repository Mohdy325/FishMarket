package com.example.fishmarket.model;

import java.io.Serializable;

public class CategoryPOJO implements Serializable {
    public String name;
    public int image;
    Boolean isSelected;

    public CategoryPOJO(String name) {
        this.name = name;
    }
    public CategoryPOJO(String name,int image) {
        this.name = name;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
