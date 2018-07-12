package com.hackathon.hackathon;

public class Constants {
    public static final class SignOnConstants {
        public static final String AUTH_ACCESS_TOKEN = "oauth.accesstoken";
        public static final String AUTH_REFRESH_TOKEN = "oauth.refreshtoken";
        public static final String AUTH_EXPIRY_VALUE = "oauth.expiry";
        public static final String AUTH_USER_NAME = "oauth.username";
        //Parser Constants
        public static final String ACCESS_TOKEN_KEY = "access_token";
        public static final String TOKEN_TYPE_KEY = "token_type";
        public static final String EXPIRE_IN_KEY = "expires_in";
        public static final String REFRESH_TOKEN_KEY = "refresh_token";
    }


    public static class ApiHeader {
        public static final String ACCEPT_CHARSET = "Accept-Charset: utf-8";
        public static final String CONTENT_TYPE_JSON = "Content-Type: application/json";
    }

    public static class ApiUrl {
        public static final String BASE = "http://uk09744iss02.s9744.tsl/";
    }


    public static final class EnquiryConstants {
        //Product Search values
        public static final String PRODUCT_SEARCH_VALUE_GEO = "uk";
        public static final String PRODUCT_SEARCH_VALUE_DIST_CHANNEL = "ghs";
        public static final String PRODUCT_SEARCH_VALUE_FIELDS = "tpnb";
        public static final String PRODUCT_SEARCH_VALUE_RES_TYPE = "products";
        public static final String PRODUCT_SEARCH_VALUE_CONFIG = "default";
        public static final int PRODUCT_SEARCH_VALUE_OFFSET = 0;
        public static final int PRODUCT_SEARCH_VALUE_LIMIT = 10;
    }
}
