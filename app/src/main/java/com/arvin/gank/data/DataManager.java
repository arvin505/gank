package com.arvin.gank.data;

/**
 * Created by xiaoyi on 2016/6/28.
 */
public class DataManager {
    private static volatile DataManager mInstance;

    private DataManager(){

    }

    public static DataManager getInstance(){
        if (mInstance == null){
            synchronized (DataManager.class){
                if (mInstance == null){
                    mInstance = new DataManager();
                }
            }
        }
        return mInstance;
    }
}
