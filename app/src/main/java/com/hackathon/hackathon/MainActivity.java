package com.hackathon.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.hackathon.hackathon.data.IdentityManager;
import com.hackathon.hackathon.data.ProductLocationDataManager;
import com.hackathon.hackathon.data.ProductSearchDataManager;
import com.hackathon.hackathon.framework.PreferencesHelper;
import com.hackathon.hackathon.framework.ResponseHandler;
import com.hackathon.hackathon.framework.data.PDADataConnectorFactory;
import com.hackathon.hackathon.models.ProductLocationResponse;
import com.hackathon.hackathon.models.ProductOverviewAPIRequest;
import com.hackathon.hackathon.models.ProductSearchResponse;
import com.hackathon.hackathon.models.SearchProductRequest;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String SERVER_ERROR = "Server Error. Try again please.";
    private String NETWORK_ERROR = "Internet not available. Try again please.";
    private String STORE_NUMBER = "2087";
    private TextToSpeech t1;
    private ProgressBar progressBar;

    private PDADataConnectorFactory pdaDataConnectorFactory;
    private PreferencesHelper preferencesHelper;
    private IdentityManager identityManager;
    private ProductSearchDataManager productSearchDataManager;
    private ProductLocationDataManager productLocationDataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar);
        findViewById(R.id.button_record).setOnClickListener(this);

        pdaDataConnectorFactory = new PDADataConnectorFactory();
        preferencesHelper = new PreferencesHelper(this);

        identityManager = new IdentityManager(pdaDataConnectorFactory, preferencesHelper);
        productSearchDataManager = new ProductSearchDataManager(pdaDataConnectorFactory);
        productLocationDataManager = new ProductLocationDataManager(pdaDataConnectorFactory);

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // if it’s speech recognition results
        // and process finished ok
        if (requestCode == SpeechRecognitionHelper.VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {

            // receiving a result in string array
            // there can be some strings because sometimes speech recognizing inaccurate
            // more relevant results in the beginning of the list
            ArrayList matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            // in “matches” array we holding a results... let’s show the most relevant
            if (matches.size() > 0) {
                String text = matches.get(0).toString();
                progressBar.setVisibility(View.VISIBLE);
                callIdentity(text);
//                t1.speak(Data.getInstance().getAnswer(text), TextToSpeech.QUEUE_FLUSH, null);


//                Toast.makeText(MainActivity.this, matches.get(0).toString(), Toast.LENGTH_LONG).show();
//                if(text.contains("how are you")) {
//                    t1.speak("I am good, How about you.", TextToSpeech.QUEUE_FLUSH, null);
//                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * Prepare search query to call api for Load more (will add offset while load more).
     */
    private SearchProductRequest prepareSearchQuery(String queryText) {
        return new SearchProductRequest(
                queryText,
                Constants.EnquiryConstants.PRODUCT_SEARCH_VALUE_GEO,
                Constants.EnquiryConstants.PRODUCT_SEARCH_VALUE_DIST_CHANNEL,
                Constants.EnquiryConstants.PRODUCT_SEARCH_VALUE_FIELDS,
                Constants.EnquiryConstants.PRODUCT_SEARCH_VALUE_RES_TYPE,
                Constants.EnquiryConstants.PRODUCT_SEARCH_VALUE_CONFIG,
                STORE_NUMBER,
                Constants.EnquiryConstants.PRODUCT_SEARCH_VALUE_OFFSET,
                Constants.EnquiryConstants.PRODUCT_SEARCH_VALUE_LIMIT);
    }


    @Override
    public void onClick(View view) {
        SpeechRecognitionHelper.run(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (t1 != null) {
            t1.stop();
            t1.shutdown();
        }
    }

    private void callIdentity(final String query) {
        identityManager.getIdentity(this, new IdentityManager.IdentityCallback() {
            @Override
            public void onSuccess(String id) {
                callSearchAPI(query);
            }

            @Override
            public void onError() {
                t1.speak(SERVER_ERROR, TextToSpeech.QUEUE_FLUSH, null);
            }

            @Override
            public void onNetworkError() {
                t1.speak(NETWORK_ERROR, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    private void callSearchAPI(final String query) {
        productSearchDataManager.getProductList(prepareSearchQuery(query), new ResponseHandler<ProductSearchResponse>() {
            @Override
            public void onRequestFailure() {
                t1.speak(SERVER_ERROR, TextToSpeech.QUEUE_FLUSH, null);
            }

            @Override
            public void onRequestSuccess(ProductSearchResponse model) {
                ProductOverviewAPIRequest request = new ProductOverviewAPIRequest();
                request.setLocationId(STORE_NUMBER);
                request.setIdentityAccessToken("Bearer " + preferencesHelper.getPreferences().getString(Constants.SignOnConstants.AUTH_ACCESS_TOKEN, ""));
                request.setApiKey(getString(R.string.api_key));
                String tpnb = model.getProductListResponseRoot().getProductListSubRoot().getProducts().getResults().get(0).getTBNB();
                request.setTpnb(tpnb.length() == 8 ? "0" + tpnb : tpnb);

                callLocationAPI(request, query);
            }
        });
    }

    private void callLocationAPI(ProductOverviewAPIRequest request, final String query) {
        productLocationDataManager.getProductLocationInfo(request, new ResponseHandler<ProductLocationResponse>() {
            @Override
            public void onRequestFailure() {
                t1.speak(SERVER_ERROR, TextToSpeech.QUEUE_FLUSH, null);
            }

            @Override
            public void onRequestSuccess(ProductLocationResponse model) {
                progressBar.setVisibility(View.GONE);
                String aisle = "You can find " + query + " in Aisle " + model.getLocationResponseList().get(0).getAisleCode();
                t1.speak(aisle, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
}