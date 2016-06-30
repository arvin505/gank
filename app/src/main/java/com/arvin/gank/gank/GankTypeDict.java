package com.arvin.gank.gank;

import android.os.Handler;
import android.util.SparseArray;

import com.arvin.gank.R;

import java.util.HashMap;

/**
 * Created by xiaoyi on 2016/6/29.
 */
public class GankTypeDict {
    public static final int DONT_SWITCH = -1;

    public static int[] menuIds = new int[]{
            R.id.navigation_daily, R.id.navigation_welfare,
            R.id.navigation_android, R.id.navigation_ios, R.id.navigation_js, R.id.navigation_video,
            R.id.navigation_resources, R.id.navigation_app
    };

    public static final SparseArray<Integer> menuIdTypeDict = new SparseArray<>();
    public static HashMap<Integer, String> type2UrlTypeDict = new HashMap<>();

    public static final HashMap<String, Integer> urlType2TypeDict = new HashMap<>();

    public static final HashMap<String, Integer> urlType2ColorDict = new HashMap<>();

}
