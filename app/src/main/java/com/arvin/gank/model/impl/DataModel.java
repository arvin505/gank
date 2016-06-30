package com.arvin.gank.model.impl;

import com.arvin.gank.bean.GankData;
import com.arvin.gank.gank.EasyGank;
import com.arvin.gank.model.IDataModel;

import rx.Observable;

/**
 * Created by xiaoyi on 2016/6/30.
 */
public class DataModel implements IDataModel {
    private DataModel() {
    }

    private static DataModel instance;

    public static DataModel getInstance() {
        if (instance == null) instance = new DataModel();
        return instance;
    }

    @Override
    public Observable<GankData> getData(String type, int size, int page) {
        return EasyGank.getInstance().getGankService().getData(type, size, page);
    }
}
