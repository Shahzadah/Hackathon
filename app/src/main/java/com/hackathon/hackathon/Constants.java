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
        public static final String EXPIRES_IN_KEY = "expires_in";
        private static final String AUTHORITY = "com.tesco.pdasignon.provider";
    }


    public static class ApiHeader {
        public static final String ACCEPT_CHARSET = "Accept-Charset: utf-8";
        public static final String CONTENT_TYPE_JSON = "Content-Type: application/json";
    }

    public static class ApiUrl {
        public static final String BASE = "http://uk09744iss02.s9744.tsl/";
    }
}
