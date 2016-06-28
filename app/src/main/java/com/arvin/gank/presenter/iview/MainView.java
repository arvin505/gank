package com.arvin.gank.presenter.iview;

import com.arvin.gank.bean.BaseGankData;
import com.arvin.gank.bean.GankDaily;
import com.arvin.gank.core.mvp.MvpView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyi on 2016/6/28.
 */
public interface MainView extends MvpView {
    /**
     * 查询 每日干货 成功
     *
     * @param dailyData dailyData
     * @param refresh   是否刷新
     */
    void onGetDailySuccess(List<GankDaily> dailyData, boolean refresh);

    /**
     * 查询 ( Android、iOS、前端、拓展资源、福利、休息视频 ) 成功
     *
     * @param data    data
     * @param refresh 是否刷新
     */
    void onGetDataSuccess(List<BaseGankData> data, boolean refresh);

    /**
     * 切换数据源成功
     *
     * @param type type
     */
    void onSwitchSuccess(int type);

    /**
     * 获取每日详情数据
     *
     * @param title  title
     * @param detail detail
     */
    void getDailyDetail(String title, ArrayList<ArrayList<BaseGankData>> detail);
}
