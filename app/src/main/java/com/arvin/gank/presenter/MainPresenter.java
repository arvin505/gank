package com.arvin.gank.presenter;

import com.arvin.gank.core.mvp.BasePresenter;
import com.arvin.gank.core.mvp.MvpView;
import com.arvin.gank.gank.GankApi;
import com.arvin.gank.presenter.iview.MainView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by xiaoyi on 2016/6/28.
 */
public class MainPresenter extends BasePresenter<MainView> {

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

            }
            return easyDates;
        }
    }
}
