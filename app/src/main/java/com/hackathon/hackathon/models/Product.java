package com.hackathon.hackathon.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Product implements Serializable {
    @SerializedName("name")
    private String productName;

    @SerializedName("image")
    private String ProductImage;

    @SerializedName("tpnb")
    private String TBNB;

    @SerializedName("price")
    private String price;

    public String getProductName() {
        return productName;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public String getTBNB() {
        return TBNB;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public void setTBNB(String TBNB) {
        this.TBNB = TBNB;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
