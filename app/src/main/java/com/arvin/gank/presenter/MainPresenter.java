package com.arvin.gank.presenter;


import com.arvin.gank.core.mvp.BasePresenter;
import com.arvin.gank.gank.GankApi;
import com.arvin.gank.gank.GankTypeDict;
import com.arvin.gank.presenter.iview.MainView;
import com.arvin.gank.utils.DateUtils;
import com.arvin.gank.utils.ReservoirUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



/**
 * Created by xiaoyi on 2016/6/28.
 */
public class MainPresenter extends BasePresenter<MainView> {

    private int page;

    private ReservoirUtils reservoirUtils;

    EasyDate currentDate;

    public class EasyDate implements Serializable {
        private Calendar calendar;


        public EasyDate(Calendar calendar) {
            this.calendar = calendar;
        }

        public int getYear() {
            return calendar.get(Calendar.YEAR);
        }

        public int getMoth() {
            return calendar.get(Calendar.MONTH) + 1;
        }

        public int getDay() {
            return calendar.get(Calendar.DAY_OF_MONTH);
        }

        public List<EasyDate> getPastTime() {
            List<EasyDate> easyDates = new ArrayList<>();
            for (int i = 0; i < GankApi.DEFAULT_DAILY_SIZE; i++) {
                long time = calendar.getTimeInMillis() - ((page - 1) * GankApi.DEFAULT_DAILY_SIZE * DateUtils.ONE_DAY) - i * DateUtils.ONE_DAY;
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(time);
                EasyDate easyDate = new EasyDate(c);
                easyDates.add(easyDate);
            }
            return easyDates;
        }
    }

    public MainPresenter() {
        reservoirUtils = ReservoirUtils.getInstance();
        long time = System.currentTimeMillis();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        currentDate = new EasyDate(c);
        page = 1;
    }

    /**
     * 设置查询第几页
     *
     * @param page page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * 获取当前页数量
     *
     * @return page
     */
    public int getPage() {
        return page;
    }

    /**
     * 查询每日数据
     *
     * @param refresh 是否是刷新
     * @param oldPage olaPage==GankTypeDict.DONT_SWITCH表示不是切换数据
     */
    public void getDaily(boolean refresh, int oldPage) {
        if (oldPage != GankTypeDict.DONT_SWITCH) {
            page = 1;
        }
        mCompositeSubscription.add(mDataManager.);

    }
}
