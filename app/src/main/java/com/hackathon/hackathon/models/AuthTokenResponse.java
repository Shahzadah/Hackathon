package com.hackathon.hackathon.models;


import com.google.gson.annotations.SerializedName;
import com.hackathon.hackathon.Constants;
import com.hackathon.hackathon.framework.model.Model;

/**
 * Auth Token Response Class
 */
public class AuthTokenResponse implements Model {

    @SerializedName(Constants.SignOnConstants.ACCESS_TOKEN_KEY)
    private String accessToken;

    @SerializedName(Constants.SignOnConstants.TOKEN_TYPE_KEY)
    private String tokenType;

    @SerializedName(Constants.SignOnConstants.EXPIRE_IN_KEY)
    private int expiresIn;

    @SerializedName(Constants.SignOnConstants.REFRESH_TOKEN_KEY)
    private String refreshToken;

    private String mUserName;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }
}
