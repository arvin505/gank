package com.arvin.gank.model.impl;

import com.arvin.gank.bean.GankDaily;
import com.arvin.gank.gank.EasyGank;
import com.arvin.gank.model.IDailyModel;

import rx.Observable;

/**
 * Created by xiaoyi on 2016/6/29.
 */
public class DailyModel implements IDailyModel {

    private static final DailyModel ourInstance = new DailyModel();

    @Override
    public Observable<GankDaily> getDaily(int year, int month, int day) {
        return EasyGank.getInstance().getGankService().getDaily(year, month, day);
    }

    private DailyModel() {
    }

    public static DailyModel getInstance() {
        return ourInstance;
    }

}
