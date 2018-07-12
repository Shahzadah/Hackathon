package com.hackathon.hackathon;

import java.util.HashMap;
import java.util.Map;

public class Data {

    private static Data _data;

    public static final String NO_ANSWER = "Sorry, Can you come again?";
    private final HashMap<String, String> mData;

    public static Data getInstance() {
        if (_data == null) {
            _data = new Data();
        }
        return _data;
    }

    private Data() {
        mData = new HashMap<>();
        mData.put("how are you", "I am doing good, How about you.");
        mData.put("where are the biscuits", "Go to Aisle 1 and 1st shelf");
        mData.put("biscuits", "We have lot of biscuits available. Tell me the brands?");
        mData.put("good day", "Please check Aisle 1. Number of biscuits available.");
        mData.put("is there any offer on cosmetics", "Yes, buy one get one offer currently going");
        mData.put("ok", "");
        mData.put("", "");
        mData.put("", "");
        mData.put("", "");
        mData.put("", "");
        mData.put("", "");
        mData.put("", "");
        mData.put("", "");
        mData.put("", "");
    }

    public String getAnswer(String question) {
        /*if (mData.containsKey(question)) {
            return mData.get(question);
        }*/
        for (Map.Entry<String, String> entry : mData.entrySet()) {
            String key = entry.getKey();

        }
        return NO_ANSWER;
    }
}
