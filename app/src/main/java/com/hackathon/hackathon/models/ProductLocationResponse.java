package com.hackathon.hackathon.models;

import com.google.gson.annotations.SerializedName;
import com.hackathon.hackathon.framework.model.Model;

import java.util.List;

public class ProductLocationResponse implements Model {

    private int code;
    private List<ProductLocationResponseData> locationResponseList;

    public List<ProductLocationResponseData> getLocationResponseList() {
        return locationResponseList;
    }

    public void setLocationResponseList(List<ProductLocationResponseData> locationResponseList) {
        this.locationResponseList = locationResponseList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public class ProductLocationResponseData {

        @SerializedName("productId")
        private String productId;

        @SerializedName("aisleCode")
        private String aisleCode;

        @SerializedName("orientationCode")
        private String orientationCode;

        @SerializedName("modSequence")
        private String modSequence;

        @SerializedName("shelfCode")
        private String shelfCode;

        @SerializedName("productPosition")
        private int productPosition;

        @SerializedName("facing")
        private int facing;

        @SerializedName("isTrayPack")
        private int isTrayPack;

        public String getProductId() {
            return productId;
        }

        public String getAisleCode() {
            return aisleCode;
        }

        public String getOrientationCode() {
            return orientationCode;
        }

        public String getModSequence() {
            return modSequence;
        }

        public String getShelfCode() {
            return shelfCode;
        }

        public int getProductPosition() {
            return productPosition;
        }

        public int getFacing() {
            return facing;
        }

        public int getIsTrayPack() {
            return isTrayPack;
        }
    }
}
