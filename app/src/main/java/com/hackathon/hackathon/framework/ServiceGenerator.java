package com.hackathon.hackathon.framework;

import com.hackathon.hackathon.BuildConfig;
import com.hackathon.hackathon.framework.data.remote.PDAService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceGenerator {
    private static Retrofit retrofit;
    private static Retrofit.Builder restBuilder;

    // No need to instantiate this class.
    private ServiceGenerator() {
    }

    public static PDAService getRestService(String newApiBaseUrl) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }

        restBuilder = new Retrofit.Builder()
                .baseUrl(newApiBaseUrl)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = restBuilder.build();
        return retrofit.create(PDAService.class);
    }

}