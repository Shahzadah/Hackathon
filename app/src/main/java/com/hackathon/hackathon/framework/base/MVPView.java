package com.hackathon.hackathon.framework.base;

import com.hackathon.hackathon.framework.model.Model;

public interface MVPView {
    void onSuccess(Model response);

    void onError(int errorTitleId, int errorMessageId);
}