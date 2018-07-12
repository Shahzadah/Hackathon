package com.hackathon.hackathon.framework;

import com.hackathon.hackathon.framework.model.Model;

/**
 * This interface is a response handler contract between com.sapient.mobility.sncore.reference.model, datamanager and controller
 */
public interface ResponseHandler<M extends Model> {

    void onRequestFailure();

    void onRequestSuccess(M model);
}
