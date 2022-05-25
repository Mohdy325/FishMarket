package com.example.fishmarket.model;

import java.io.Serializable;

public class DiscoverPOJO implements Serializable {
    public String name;
    public int image;

    public DiscoverPOJO(String name, int image) {
        this.name = name;
        this.image = image;
    }
}
