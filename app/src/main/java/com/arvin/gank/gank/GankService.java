package com.arvin.gank.gank;

import com.arvin.gank.bean.GankDaily;
import com.arvin.gank.bean.GankData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by xiaoyi on 2016/6/29.
 */
public interface GankService {


    /**
     * @param year
     * @param month
     * @param day
     * @return
     */
    @GET("day/{year}/{month}/{day}")
    Observable<GankDaily> getDaily(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    /**
     * @param type
     * @param size
     * @param page
     * @return
     */
    @GET("data/{type}/{size}/{page}")
    Observable<GankData> getData(@Path("type") String type, @Path("size") int size, @Path("page") int page);
}
