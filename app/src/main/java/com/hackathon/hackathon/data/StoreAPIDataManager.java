package com.hackathon.hackathon.data;

import com.hackathon.hackathon.framework.ResponseHandler;
import com.hackathon.hackathon.framework.data.PDADataConnector;
import com.hackathon.hackathon.framework.data.PDADataConnectorFactory;
import com.hackathon.hackathon.models.StoreDetailsApiModel;

/**
 * APi data manager class which calls the cloud connector
 */
public class StoreAPIDataManager {
    private PDADataConnector cloudConnector;

    public StoreAPIDataManager(PDADataConnectorFactory dataConnectorFactory) {
        cloudConnector = dataConnectorFactory.createCloudConnector("http://172.21.156.247/StoreAPI/api/StoreDetails/GetStoreDetails/", PDADataConnectorFactory.REST_REQUEST);
    }

    /**
     * Actual api call for geting the store details
     *
     * @param responseHandler
     */
    public void getStoreDetails(final ResponseHandler<StoreDetailsApiModel> responseHandler) {
        cloudConnector.getStoreDetails("", responseHandler);
    }
}