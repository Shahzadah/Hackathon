package com.hackathon.hackathon.framework.data;

import com.hackathon.hackathon.framework.ResponseHandler;
import com.hackathon.hackathon.models.AuthTokenResponse;
import com.hackathon.hackathon.models.IdentityRequest;
import com.hackathon.hackathon.models.ProductLocationResponse;
import com.hackathon.hackathon.models.ProductOverviewAPIRequest;
import com.hackathon.hackathon.models.ProductSearchResponse;
import com.hackathon.hackathon.models.SearchProductRequest;
import com.hackathon.hackathon.models.StoreDetailsApiModel;

public interface PDADataConnector {
    void getStoreDetails(String ipAddress, ResponseHandler<StoreDetailsApiModel> responseHandler);

    void searchProduct(SearchProductRequest request, ResponseHandler<ProductSearchResponse> responseHandler);

    void getProductLocationInfo(ProductOverviewAPIRequest request, ResponseHandler<ProductLocationResponse> responseHandler);

    void getIdentity(IdentityRequest request, ResponseHandler<AuthTokenResponse> responseHandler);
}