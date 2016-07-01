package com.arvin.gank.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by xiaoyi on 2016/6/28.
 */
public class GankDaily {
    @SerializedName("results")
    public DailyResults results;

    @SerializedName("category")
    public ArrayList<String> category;

    public class DailyResults {

        @SerializedName("福利")
        public ArrayList<BaseGankData> welfareData;

        @SerializedName("Android")
        public ArrayList<BaseGankData> androidData;

        @SerializedName("iOS")
        public ArrayList<BaseGankData> iosData;

        @SerializedName("前端")
        public ArrayList<BaseGankData> jsData;

        @SerializedName("休息视频")
        public ArrayList<BaseGankData> videoData;

        @SerializedName("拓展资源")
        public ArrayList<BaseGankData> resourcesData;

        @SerializedName("App")
        public ArrayList<BaseGankData> appData;

        @SerializedName("瞎推荐")
        public ArrayList<BaseGankData> recommendData;

        @Override
        public String toString() {
            return "DailyResults{" +
                    "welfareData=" + welfareData +
                    ", androidData=" + androidData +
                    ", iosData=" + iosData +
                    ", jsData=" + jsData +
                    ", videoData=" + videoData +
                    ", resourcesData=" + resourcesData +
                    ", appData=" + appData +
                    ", recommendData=" + recommendData +
                    '}';
        }

    }

    @Override
    public String toString() {
        return "GankDaily{" +
                "results=" + results +
                ", category=" + category +
                '}';
    }
}
