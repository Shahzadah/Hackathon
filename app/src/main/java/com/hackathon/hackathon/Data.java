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
        mData.put("thank you", "welcome");
        mData.put("hi", "hello");
        mData.put("good day", "thank you, you too");
        mData.put("is there any offer on cosmetics", "we have good offers on loreal products, would you like to know more?");
        mData.put("yes please", "we have buy 1 get 1 free on all ranges of loreal matte lipstics ");
        mData.put("thanks", "welcome");
        mData.put("hello", "hi");
        mData.put("can you help me", "yes, eager to help, tell me");
        mData.put("is there any discount going on", "we have good offers on clothing");
        mData.put("do you accept cards", "yes we do accept all visa master and amex cards");
        mData.put("where is female clothing section", "It is in Aisle 12 behind pharmacy");
        mData.put("bye", "bye bye");
        mData.put("go to hell", "that is so rude");
        mData.put("where the hell are you", "I am here to help you");

    }

    public String getAnswer(String question) {
        if (mData.containsKey(question)) {
            return mData.get(question);
        }
        for (Map.Entry<String, String> entry : mData.entrySet()) {
            String key = entry.getKey();

        }
        return NO_ANSWER;
    }
}
