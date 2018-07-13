package com.hackathon.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.hackathon.hackathon.models.StoreDetailsApiModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String SERVER_ERROR = "Server Error. Try again please.";
    private String NETWORK_ERROR = "Internet not available. Try again please.";
    private String STORE_NUMBER = "2087";
    private String GREETING_MSG = "Welcome to %s store, How can I help you today?";
    private TextToSpeech tts;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ChatAppMsgAdapter mAdapter;
    private StoreDetailsApiModel mStoreDetails;
    private List<ChatAppMsgDTO> list;

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
        recyclerView = findViewById(R.id.chat_recycler_view);
        findViewById(R.id.button_record).setOnClickListener(this);

        PDADataConnectorFactory pdaDataConnectorFactory = new PDADataConnectorFactory();
        preferencesHelper = new PreferencesHelper(this);

        identityManager = new IdentityManager(pdaDataConnectorFactory, preferencesHelper);
        productSearchDataManager = new ProductSearchDataManager(pdaDataConnectorFactory);
        productLocationDataManager = new ProductLocationDataManager(pdaDataConnectorFactory);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.UK);
                }
                if (mStoreDetails != null) {
                    String welcomeText = String.format(GREETING_MSG, mStoreDetails.getStoreName());
                    tts.speak(welcomeText, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        if (getIntent() != null && getIntent().hasExtra(Constants.STORE_DETAILS)) {
            mStoreDetails = (StoreDetailsApiModel) getIntent().getSerializableExtra(Constants.STORE_DETAILS);
        }

        list = new ArrayList<>();
        mAdapter = new ChatAppMsgAdapter(list);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
//        mLayoutManager.setStackFromEnd(true);
//        mLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SpeechRecognitionHelper.VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches.size() > 0) {
                String question = matches.get(0).toString();
                addQuestionInList(question);
                if (question.contains("where can I find")) {
                    progressBar.setVisibility(View.VISIBLE);
                    String newText = question.replace("where can I find", "");
                    callIdentity(newText);
                } else if (question.contains("where is")) {
                    progressBar.setVisibility(View.VISIBLE);
                    String newText = question.replace("where is", "");
                    callIdentity(newText);
                } else if (question.contains("where can I get")) {
                    progressBar.setVisibility(View.VISIBLE);
                    String newText = question.replace("where can I get", "");
                    callIdentity(newText);
                } else {
                    String answer = Data.getInstance().getAnswer(question);
                    addMessageInList(answer);
                }
//                Toast.makeText(MainActivity.this, question, Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

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
        if (tts != null) {
            tts.stop();
            tts.shutdown();
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
                tts.speak(SERVER_ERROR, TextToSpeech.QUEUE_FLUSH, null);
            }

            @Override
            public void onNetworkError() {
                tts.speak(NETWORK_ERROR, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    private void callSearchAPI(final String query) {
        productSearchDataManager.getProductList(prepareSearchQuery(query), new ResponseHandler<ProductSearchResponse>() {
            @Override
            public void onRequestFailure() {
                tts.speak(SERVER_ERROR, TextToSpeech.QUEUE_FLUSH, null);
            }

            @Override
            public void onRequestSuccess(ProductSearchResponse model) {
                if (model == null
                        || model.getProductListResponseRoot() == null
                        || model.getProductListResponseRoot().getProductListSubRoot() == null
                        || model.getProductListResponseRoot().getProductListSubRoot().getProducts() == null
                        || model.getProductListResponseRoot().getProductListSubRoot().getProducts().getResults() == null
                        || model.getProductListResponseRoot().getProductListSubRoot().getProducts().getResults().size() == 0) {
                    progressBar.setVisibility(View.GONE);
                    addMessageInList(Data.NO_ANSWER);
                    return;
                }
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
                tts.speak(SERVER_ERROR, TextToSpeech.QUEUE_FLUSH, null);
            }

            @Override
            public void onRequestSuccess(ProductLocationResponse model) {
                progressBar.setVisibility(View.GONE);
                if (model == null
                        || model.getLocationResponseList() == null
                        || model.getLocationResponseList().size() == 0) {
                    addMessageInList(Data.NO_ANSWER);
                    return;
                }
                String aisle = "You can find " + query + " in Aisle " + model.getLocationResponseList().get(0).getAisleCode();
                addMessageInList(aisle);
            }
        });
    }

    private void addQuestionInList(String text) {
        ChatAppMsgDTO msg = new ChatAppMsgDTO(ChatAppMsgDTO.MSG_TYPE_SENT, text);
        list.add(msg);
        mAdapter.notifyDataSetChanged();
    }

    private void addMessageInList(String text) {
        ChatAppMsgDTO msg = new ChatAppMsgDTO(ChatAppMsgDTO.MSG_TYPE_RECEIVED, text);
        list.add(msg);
        mAdapter.notifyDataSetChanged();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}