package com.hackathon.hackathon.models;

public class SearchProductRequest {
    private String query;
    private String geo;
    private String distChannel;
    private String fields;
    private String resType;
    private String config;
    private String store;
    private int offset;
    private int limit;
    private String identityAccessToken;

    public SearchProductRequest(String query, String geo, String distChannel, String fields, String resType, String config, String store, int offset, int limit){
        this.query = query;
        this.geo = geo;
        this.distChannel = distChannel;
        this.fields = fields;
        this.resType = resType ;
        this.config = config ;
        this.store = store ;
        this.offset = offset ;
        this.limit = limit ;
    }

    public String getQuery() {
        return query;
    }

    public String getGeo() {
        return geo;
    }

    public String getDistChannel() {
        return distChannel;
    }

    public String getFields() {
        return fields;
    }

    public String getResType() {
        return resType;
    }

    public String getConfig() {
        return config;
    }

    public String getStore() {
        return store;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setOffset(int offset){
        this.offset = offset;
    }

    public String getIdentityAccessToken() {
        return identityAccessToken;
    }

    public void setIdentityAccessToken(String identityAccessToken) {
        this.identityAccessToken = identityAccessToken;
    }
}
