package com.example.fishmarket.model;

import java.io.Serializable;

public class ProductPOJO implements Serializable {
    public String name;
    public String dummy="Genetically Imroved Katla";
    public ProductPOJO(){

    }

    public ProductPOJO(String name) {
        this.name = name;
    }
}
