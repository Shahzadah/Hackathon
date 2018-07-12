package com.hackathon.hackathon.framework.data;


import com.hackathon.hackathon.Constants;
import com.hackathon.hackathon.framework.ServiceGenerator;
import com.hackathon.hackathon.framework.data.remote.PDACloudDataConnector;
import com.hackathon.hackathon.framework.data.remote.PDAService;

public class PDADataConnectorFactory {
    public final static int REST_REQUEST = 2;
    private PDAService service;

    public PDADataConnectorFactory() {
        this.service = ServiceGenerator.getRestService(Constants.ApiUrl.BASE);
    }


    public PDADataConnector createCloudConnector(String baseUrl, int requestType) {
        this.service = ServiceGenerator.getRestService(baseUrl);
        return new PDACloudDataConnector(service);
    }
}
