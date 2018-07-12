package com.hackathon.hackathon.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ProductList {
    @SerializedName("results")
    private ArrayList<Product> productList;

    public ArrayList<Product> getResults (){
        return productList;
    }

    @SerializedName("totals")
    private Total total;

    public Total getTotal(){
        return total;
    }

    public class Total{
        @SerializedName("all")
        private int total;

        public int getTotalRecord(){
            return total;
        }
    }
}
