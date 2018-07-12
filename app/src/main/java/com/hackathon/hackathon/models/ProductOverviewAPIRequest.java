package com.hackathon.hackathon.models;

public class ProductOverviewAPIRequest {

    private String tpnb;
    private String locationId;
    private String crStream;
    private String codeLevel;
    private String pricingDate;
    private String identityAccessToken;
    private String crDNSEnvironment;
    private String apiKey;
    private String identityAccessTokenParam;
    private String ean;

    public String getTpnb() {
        return tpnb;
    }

    public void setTpnb(String tpnb) {
        this.tpnb = tpnb;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getCrStream() {
        return crStream;
    }

    public void setCrStream(String crStream) {
        this.crStream = crStream;
    }

    public String getCodeLevel() {
        return codeLevel;
    }

    public void setCodeLevel(String codeLevel) {
        this.codeLevel = codeLevel;
    }

    public String getPricingDate() {
        return pricingDate;
    }

    public void setPricingDate(String pricingDate) {
        this.pricingDate = pricingDate;
    }

    public String getIdentityAccessToken() {
        return identityAccessToken;
    }

    public void setIdentityAccessToken(String identityAccessToken) {
        this.identityAccessToken = identityAccessToken;
    }

    public String getCrDNSEnvironment() {
        return crDNSEnvironment;
    }

    public void setCrDNSEnvironment(String crDNSEnvironment) {
        this.crDNSEnvironment = crDNSEnvironment;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getIdentityAccessTokenParam() {
        return identityAccessTokenParam;
    }

    public void setIdentityAccessTokenParam(String identityAccessTokenParam) {
        this.identityAccessTokenParam = identityAccessTokenParam;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }
}
