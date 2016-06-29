package com.arvin.gank.model;

import com.arvin.gank.bean.GankDaily;

import java.util.List;

import rx.Observable;

/**
 * Created by xiaoyi on 2016/6/29.
 */
public interface IDailyModel {
    /**
     * 查询每日数据
     *
     * @param year  year
     * @param month month
     * @param day   day
     * @return Observable<GankDaily>
     */
    Observable<GankDaily> getDaily(int year, int month, int day);
}
