package com.hackathon.hackathon.framework.base;

public interface Presenter<V extends MVPView> {
    void attachView(V mvpView);

    void detachView();
}
