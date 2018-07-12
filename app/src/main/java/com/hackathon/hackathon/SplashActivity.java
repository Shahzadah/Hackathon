package com.hackathon.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;

import com.hackathon.hackathon.data.StoreAPIDataManager;
import com.hackathon.hackathon.framework.ResponseHandler;
import com.hackathon.hackathon.framework.data.PDADataConnectorFactory;
import com.hackathon.hackathon.models.StoreDetailsApiModel;

public class SplashActivity extends Activity {

    private StoreAPIDataManager storeAPIDataManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = findViewById(R.id.progress_bar);
        storeAPIDataManager = new StoreAPIDataManager(new PDADataConnectorFactory());

        callStoreAPI();
    }

    private void callStoreAPI() {
        progressBar.setVisibility(View.VISIBLE);
        storeAPIDataManager.getStoreDetails(new ResponseHandler<StoreDetailsApiModel>() {
            @Override
            public void onRequestFailure() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onRequestSuccess(StoreDetailsApiModel model) {
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra(Constants.STORE_DETAILS, model);
                startActivity(intent);
                finish();
            }
        });
    }
}