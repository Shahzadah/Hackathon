package com.hackathon.hackathon.framework.data.remote;

import android.os.Build;

import com.hackathon.hackathon.framework.NetworkUtil;
import com.hackathon.hackathon.framework.ResponseHandler;
import com.hackathon.hackathon.framework.data.PDADataConnector;
import com.hackathon.hackathon.models.AuthTokenResponse;
import com.hackathon.hackathon.models.IdentityRequest;
import com.hackathon.hackathon.models.ProductLocationResponse;
import com.hackathon.hackathon.models.ProductOverviewAPIRequest;
import com.hackathon.hackathon.models.ProductSearchResponse;
import com.hackathon.hackathon.models.SearchProductRequest;
import com.hackathon.hackathon.models.StoreDetailsApiModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PDACloudDataConnector implements PDADataConnector {

    private static final String TAG = PDACloudDataConnector.class.getName();
    private PDAService mServiceEndpoint;

    public PDACloudDataConnector(PDAService service) {
        this.mServiceEndpoint = service;
    }

    /**
     * Method calling Retrofit api for getting the response for Store details settings
     *
     * @param ipAddress       : Device IP
     * @param responseHandler : Response Handler
     */
    @Override
    public void getStoreDetails(String ipAddress, final ResponseHandler<StoreDetailsApiModel> responseHandler) {
        String ip = NetworkUtil.getLocalIpAddress();
        if (Build.FINGERPRINT.contains("generic")) {
            //INFO for emulator setting ip as below
            ip = "172.23";
        }
        mServiceEndpoint.getStoreDetails(ip).enqueue(new Callback<StoreDetailsApiModel>() {
            @Override
            public void onResponse(Call<StoreDetailsApiModel> call, Response<StoreDetailsApiModel> response) {
                if (response != null && response.isSuccessful()) {
                    responseHandler.onRequestSuccess(response.body());
                } else {
                    responseHandler.onRequestFailure();
                }
            }

            @Override
            public void onFailure(Call<StoreDetailsApiModel> call, Throwable t) {
                responseHandler.onRequestFailure();
            }
        });
    }

    @Override
    public void searchProduct(SearchProductRequest request, final ResponseHandler<ProductSearchResponse> responseHandler) {
        mServiceEndpoint.getProductList(request.getIdentityAccessToken(), request.getQuery(), request.getGeo(), request.getDistChannel(),
                request.getFields(), request.getResType(), request.getConfig(), request.getStore(),
                request.getOffset(), request.getLimit()).enqueue(new Callback<ProductSearchResponse>() {
            @Override
            public void onResponse(Call<ProductSearchResponse> call, Response<ProductSearchResponse> response) {
                if (response != null && response.isSuccessful()) {
                    responseHandler.onRequestSuccess(response.body());
                } else {
                    responseHandler.onRequestFailure();
                }
            }

            @Override
            public void onFailure(Call<ProductSearchResponse> call, Throwable t) {
                responseHandler.onRequestFailure();
            }
        });
    }

    @Override
    public void getProductLocationInfo(ProductOverviewAPIRequest request, final ResponseHandler<ProductLocationResponse> responseHandler) {
        mServiceEndpoint.getProductLocationInfo(request.getLocationId(), request.getIdentityAccessToken(), request.getApiKey(), request.getTpnb()).enqueue(new Callback<List<ProductLocationResponse.ProductLocationResponseData>>() {
            @Override
            public void onResponse(Call<List<ProductLocationResponse.ProductLocationResponseData>> call, Response<List<ProductLocationResponse.ProductLocationResponseData>> response) {
                if (response != null) {
                    ProductLocationResponse locationResponse = new ProductLocationResponse();
                    locationResponse.setLocationResponseList(response.body());
                    locationResponse.setCode(response.code());
                    responseHandler.onRequestSuccess(locationResponse);
                } else {
                    responseHandler.onRequestFailure();
                }
            }

            @Override
            public void onFailure(Call<List<ProductLocationResponse.ProductLocationResponseData>> call, Throwable t) {
                responseHandler.onRequestFailure();
            }
        });
    }

    @Override
    public void getIdentity(IdentityRequest request, final ResponseHandler<AuthTokenResponse> responseHandler) {
        mServiceEndpoint.getIdentity(request).enqueue(new Callback<AuthTokenResponse>() {
            @Override
            public void onResponse(Call<AuthTokenResponse> call, Response<AuthTokenResponse> response) {
                if (response != null && response.isSuccessful()) {
                    responseHandler.onRequestSuccess(response.body());
                } else {
                    responseHandler.onRequestFailure();
                }
            }

            @Override
            public void onFailure(Call<AuthTokenResponse> call, Throwable t) {
                responseHandler.onRequestFailure();
            }
        });
    }
}