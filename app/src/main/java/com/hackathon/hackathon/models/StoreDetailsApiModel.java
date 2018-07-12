package com.hackathon.hackathon.models;

import com.google.gson.annotations.SerializedName;
import com.hackathon.hackathon.framework.model.Model;

public class StoreDetailsApiModel implements Model {

    @SerializedName("storeName")
    private String storeName ;

    @SerializedName("storeNumber")
    private  int storeNumber ;

    @SerializedName("pfsStoreNumber")
    private  int pfsStoreNumber ;

    @SerializedName("pfsStoreName")
    private String pfsStoreName ;

    @SerializedName("errorCode")
    private int errorCode ;

    @SerializedName("errorMessage")
    private String errorMessage ;

    @SerializedName("countryID")
    private String countryId;

    @SerializedName("codeLevel")
    private String codeLevel;

    @SerializedName("stream")
    private String stream;

    @SerializedName("crDNSEnvironment")
    private String crDNSEnvironment;

    @SerializedName("countryCode")
    private String countryCode;

    @SerializedName("currency")
    private String currency;

    public String getStoreName() {
        return storeName;
    }

    public int getStoreNumber() {
        return storeNumber;
    }

    public int getPfsStoreNumber() {
        return pfsStoreNumber;
    }

    public String getPfsStoreName() {
        return pfsStoreName;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getCodeLevel() {
        return codeLevel;
    }

    public String getStream() {
        return stream;
    }

    public String getCrDNSEnvironment() {
        return crDNSEnvironment;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCurrency() {
        return currency;
    }
}