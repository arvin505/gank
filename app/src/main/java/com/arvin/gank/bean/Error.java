package com.arvin.gank.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xiaoyi on 2016/6/28.
 */
public class Error {
    // 每个请求都有error数据
    @SerializedName("error") public Boolean error;

    @SerializedName("msg") public String msg;

}
