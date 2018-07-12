package com.hackathon.hackathon.data;

import com.hackathon.hackathon.framework.ResponseHandler;
import com.hackathon.hackathon.framework.data.PDADataConnector;
import com.hackathon.hackathon.framework.data.PDADataConnectorFactory;
import com.hackathon.hackathon.models.ProductSearchResponse;
import com.hackathon.hackathon.models.SearchProductRequest;

/**
 * Price Data Manager
 */
public class ProductSearchDataManager {
    private PDADataConnector cloudConnector;

    public ProductSearchDataManager(PDADataConnectorFactory dataConnectorFactory) {
        cloudConnector = dataConnectorFactory.createCloudConnector("https://search.api.tesco.com/api/search/",PDADataConnectorFactory.REST_REQUEST);
    }

    /**
     * This method will get the Price detail of the project
     * @param request : Product ID
     * @param responseHandler : Response Handler
     */
    public void getProductList(SearchProductRequest request, final ResponseHandler<ProductSearchResponse> responseHandler) {
        cloudConnector.searchProduct(request, responseHandler);
    }
}