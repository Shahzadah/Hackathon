package com.hackathon.hackathon.framework.data.remote;

import com.hackathon.hackathon.Constants;
import com.hackathon.hackathon.models.AuthTokenResponse;
import com.hackathon.hackathon.models.IdentityRequest;
import com.hackathon.hackathon.models.ProductLocationResponse;
import com.hackathon.hackathon.models.ProductSearchResponse;
import com.hackathon.hackathon.models.StoreDetailsApiModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PDAService {

    @Headers({
            Constants.ApiHeader.CONTENT_TYPE_JSON,
            Constants.ApiHeader.ACCEPT_CHARSET
    })
    @GET("./")
    Call<StoreDetailsApiModel> getStoreDetails(@Query("IP") String ip);

    @Headers({
            Constants.ApiHeader.CONTENT_TYPE_JSON,
            Constants.ApiHeader.ACCEPT_CHARSET
    })
    @GET("./")
    Call<ProductSearchResponse> getProductList(
            @Header("Authorization") String identityAccessToken,
            @Query("query") String query,
            @Query("geo") String geo,
            @Query("distChannel") String distChannel,
            @Query("fields") String fields,
            @Query("resType") String resType,
            @Query("config") String config,
            @Query("store") String store,
            @Query("offset") int offset,
            @Query("limit") int limit
    );


    @GET("./stores/{store_no}/mapped-locations")
    Call<List<ProductLocationResponse.ProductLocationResponseData>> getProductLocationInfo(
            @Path("store_no") String store_no,
            @Header("Authorization") String identityAccessToken,
            @Header("api_key") String apiKey,
            @Query("productId") String productId
    );


    @Headers({Constants.ApiHeader.CONTENT_TYPE_JSON})
    @POST("./")
    Call<AuthTokenResponse> getIdentity(@Body IdentityRequest request);
}