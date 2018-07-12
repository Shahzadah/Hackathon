package com.hackathon.hackathon.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.hackathon.hackathon.Constants;
import com.hackathon.hackathon.framework.NetworkUtil;
import com.hackathon.hackathon.framework.PreferencesHelper;
import com.hackathon.hackathon.framework.ResponseHandler;
import com.hackathon.hackathon.framework.data.PDADataConnector;
import com.hackathon.hackathon.framework.data.PDADataConnectorFactory;
import com.hackathon.hackathon.models.AuthTokenResponse;
import com.hackathon.hackathon.models.IdentityRequest;

/**
 * Manager class for Identity service
 */
public class IdentityManager {
    private PDADataConnector cloudConnector;
    private PreferencesHelper mPreferencesHelper;
    private Context mContext;
    private IdentityCallback mIdentityCallback;


    /**
     * Injecting Data manager and Preference helper
     *
     * @param dataConnectorFactory : Connection
     * @param preferenceHelper     : Preference helper
     */
    public IdentityManager(PDADataConnectorFactory dataConnectorFactory, PreferencesHelper preferenceHelper) {
        cloudConnector = dataConnectorFactory.createCloudConnector("https://identity.api.tesco.com/v3/api/auth/oauth/v2/token/", PDADataConnectorFactory.REST_REQUEST);
        mPreferencesHelper = preferenceHelper;
    }

    /**
     * method to get Get Identity.
     *
     * @param context  : Context
     * @param callback : Callback
     */
    public void getIdentity(Context context, IdentityCallback callback) {
        mContext = context;
        mIdentityCallback = callback;

        //Get Client ID
        String accessToken = getAuthToken();
        //Validating if has valid token and saved client ID is valid
        if (hasValidToken() && accessToken != null && !accessToken.equalsIgnoreCase("")) {
            mIdentityCallback.onSuccess(accessToken);

        } else {
            IdentityRequest identityRequest = new IdentityRequest(mContext);
            fetchIdentity(identityRequest);
        }
    }

    /**
     * Method is to get the fresh Identity client ID.
     *
     * @param request : Identity request
     */
    private void fetchIdentity(IdentityRequest request) {
        if (NetworkUtil.isNetworkConnected()) {
            cloudConnector.getIdentity(request, new ResponseHandler<AuthTokenResponse>() {

                @Override
                public void onRequestFailure() {
                    mIdentityCallback.onError();
                }

                @Override
                public void onRequestSuccess(AuthTokenResponse response) {
                    save(response.getAccessToken(), response.getRefreshToken(), response.getExpiresIn());
                    mIdentityCallback.onSuccess(response.getAccessToken());
                }
            });

        } else {
            mIdentityCallback.onNetworkError();
        }
    }


    /**
     * Save Auth token to the shared preference
     *
     * @param token        : Auth token
     * @param refreshToken : Refresh Token
     * @param expiry       : Expiry value
     */
    private void save(String token, String refreshToken, int expiry) {
        SharedPreferences prefs = mPreferencesHelper.getPreferences();
        prefs.edit().putString(Constants.SignOnConstants.AUTH_ACCESS_TOKEN, token).apply();
        prefs.edit().putString(Constants.SignOnConstants.AUTH_REFRESH_TOKEN, refreshToken).apply();
        prefs.edit().putLong(Constants.SignOnConstants.AUTH_EXPIRY_VALUE, System.currentTimeMillis() + expiry * 1000).apply();
    }


    /**
     * Method to get the Auth token
     *
     * @return : Saved AuthToken
     */
    private String getAuthToken() {
        SharedPreferences prefs = mPreferencesHelper.getPreferences();
        return prefs.getString(Constants.SignOnConstants.AUTH_ACCESS_TOKEN, "");
    }

    /**
     * Method to check the validity of the Existing Auth token
     *
     * @return : Validity status
     */
    private boolean hasValidToken() {
        SharedPreferences prefs = mPreferencesHelper.getPreferences();
        long expiryTime = prefs.getLong(Constants.SignOnConstants.AUTH_EXPIRY_VALUE, 0L);
        return expiryTime > System.currentTimeMillis();
    }

    /**
     * Callback interface for Identity
     */
    public interface IdentityCallback {
        void onSuccess(String id);

        void onError();

        void onNetworkError();
    }
}
