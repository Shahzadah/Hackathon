package com.hackathon.hackathon.framework;

public class RequestFailureError {

    private int errorCode=0;
    private int errorMessageResId ;
    private int errorTitleResId;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorMessageResId() {
        return errorMessageResId;
    }

    public void setErrorMessageResId(int errorMessageResId) {
        this.errorMessageResId = errorMessageResId;
    }

    public int getErrorTitleResId() {
        return errorTitleResId;
    }

    public void setErrorTitleResId(int errorTitleResId) {
        this.errorTitleResId = errorTitleResId;
    }
}