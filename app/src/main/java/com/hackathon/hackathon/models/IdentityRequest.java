package com.hackathon.hackathon.models;

import android.content.Context;

import com.hackathon.hackathon.R;

/**
 * Get Auth Token Request
 */
public class IdentityRequest {

    private String client_id;
    private String grant_type;
    private String scope;
    private String username;
    private String password;

    /**
     * Constructor
     */
    public IdentityRequest(Context context){
        this.client_id = context.getString(R.string.identity_client_prefix) + context.getString(R.string.identity_client) +":" + context.getString(R.string.identity_secret);
        this.grant_type = context.getString(R.string.identity_grant_type);
        this.username = context.getString(R.string.identity_user_name);
        this.password = context.getString(R.string.identity_password);
        this.scope = context.getString(R.string.identity_scope);
    }

    public IdentityRequest(){}

    public String getGrantType() {
        return grant_type;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getClientID() {
        return client_id;
    }

    public String getScope() {
        return scope;
    }

    public void setClientID(String client_id) {
        this.client_id = client_id;
    }

    public void setGrantType(String grant_type) {
        this.grant_type = grant_type;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
