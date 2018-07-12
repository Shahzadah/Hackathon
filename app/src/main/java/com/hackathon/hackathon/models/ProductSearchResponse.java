package com.hackathon.hackathon.models;

import com.google.gson.annotations.SerializedName;
import com.hackathon.hackathon.framework.model.Model;

public class ProductSearchResponse implements Model {
    @SerializedName("uk")
    private ProductListResponseRoot productListResponseRoot;

    public ProductListResponseRoot getProductListResponseRoot() {
        return productListResponseRoot;
    }
}