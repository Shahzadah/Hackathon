package com.hackathon.hackathon.models;

import com.google.gson.annotations.SerializedName;

public class ProductListSubRoot {
    @SerializedName("products")
    private ProductList products;

    public ProductList getProducts (){
        return products;
    }


}
