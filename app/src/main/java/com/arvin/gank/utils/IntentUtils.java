package com.arvin.gank.utils;

import android.content.Intent;

/**
 * Created by xiaoyi on 2016/7/7.
 */
public class IntentUtils {

    public static boolean hasIntent(Intent intent) {
        return intent != null;
    }


    public static boolean hasExtra(Intent intent, String key) {
        return intent.hasExtra(key);
    }

    public static String getStringExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getStringExtra(name);
    }
}
