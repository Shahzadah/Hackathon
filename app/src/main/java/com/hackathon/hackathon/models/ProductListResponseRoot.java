package com.hackathon.hackathon.models;

import com.google.gson.annotations.SerializedName;

public class ProductListResponseRoot {
    @SerializedName("ghs")
    private ProductListSubRoot productListSubRoot;

    public ProductListSubRoot getProductListSubRoot (){
        return productListSubRoot;
    }


}

