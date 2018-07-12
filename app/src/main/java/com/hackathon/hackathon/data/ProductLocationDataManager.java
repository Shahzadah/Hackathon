package com.hackathon.hackathon.data;


import com.hackathon.hackathon.framework.ResponseHandler;
import com.hackathon.hackathon.framework.data.PDADataConnector;
import com.hackathon.hackathon.framework.data.PDADataConnectorFactory;
import com.hackathon.hackathon.models.ProductLocationResponse;
import com.hackathon.hackathon.models.ProductOverviewAPIRequest;

public class ProductLocationDataManager {

    private PDADataConnector cloudConnector;

    public ProductLocationDataManager(PDADataConnectorFactory dataConnectorFactory) {
        cloudConnector = dataConnectorFactory.createCloudConnector("http://productlocation.global.tesco.org/v4/", PDADataConnectorFactory.REST_REQUEST);
    }

    /**
     * This method will get the location information for product.
     *
     * @param request         : Product ID
     * @param responseHandler : Response Handler
     */
    public void getProductLocationInfo(ProductOverviewAPIRequest request, final ResponseHandler<ProductLocationResponse> responseHandler) {
//        request.setLocationId("2049");
        cloudConnector.getProductLocationInfo(request, responseHandler);
    }
}