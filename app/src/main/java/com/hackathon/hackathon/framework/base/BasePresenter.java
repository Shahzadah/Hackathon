package com.hackathon.hackathon.framework.base;

public class BasePresenter<T extends MVPView> implements Presenter<T> {

    protected T view;


    @Override
    public void attachView(T mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
    }


}
