package com.arvin.gank.presenter;


import com.anupcowkur.reservoir.ReservoirGetCallback;
import com.arvin.gank.bean.BaseGankData;
import com.arvin.gank.bean.GankDaily;
import com.arvin.gank.bean.GankData;
import com.arvin.gank.core.mvp.BasePresenter;
import com.arvin.gank.gank.GankApi;
import com.arvin.gank.gank.GankType;
import com.arvin.gank.gank.GankTypeDict;
import com.arvin.gank.presenter.iview.MainView;
import com.arvin.gank.utils.DateUtils;
import com.arvin.gank.utils.ReservoirUtils;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.security.Key;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rx.Subscriber;


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
    public void getDaily(final boolean refresh, final int oldPage) {
        if (oldPage != GankTypeDict.DONT_SWITCH) {
            page = 1;
        }
        mCompositeSubscription.add(mDataManager.getDailyDataByNetWork(currentDate)
                .subscribe(new Subscriber<List<GankDaily>>() {
                    @Override
                    public void onCompleted() {
                        if (mCompositeSubscription != null) {
                            mCompositeSubscription.remove(this);
                        }
                    }

                    @Override
                    public void onError(final Throwable e) {
                        Logger.e(e.getMessage());
                        if (refresh) {
                            Type resultType = new TypeToken<List<GankDaily>>() {
                            }.getType();
                            reservoirUtils.get(GankType.daily + "", resultType, new ReservoirGetCallback<List<GankDaily>>() {
                                @Override
                                public void onSuccess(List<GankDaily> gankDailies) {
                                    if (oldPage != GankTypeDict.DONT_SWITCH) {
                                        if (getMvpView() != null) {
                                            getMvpView().onSwitchSuccess(GankType.daily);
                                        }
                                    }
                                    if (getMvpView() != null) {
                                        getMvpView().onGetDailySuccess(gankDailies, refresh);
                                        getMvpView().onFailure(e);
                                    }
                                }

                                @Override
                                public void onFailure(Exception e) {
                                    switchFailure(oldPage, e);
                                }
                            });
                        } else {
                            getMvpView().onFailure(e);
                        }
                    }

                    @Override
                    public void onNext(List<GankDaily> gankDailies) {
                        /*
                         * 如果是切换数据源
                         * page=1加载成功了
                         * 即刚才的loadPage
                         */
                        if (oldPage != GankTypeDict.DONT_SWITCH) {
                            if (getMvpView() != null) {
                                getMvpView().onSwitchSuccess(GankType.daily);
                            }
                        }
                        if (refresh) {
                            reservoirUtils.refresh(GankType.daily + "", gankDailies);
                        }
                        if (getMvpView() != null) {
                            getMvpView().onGetDailySuccess(gankDailies, refresh);
                        }
                    }
                }));

    }


    /**
     * * 查询 ( Android、iOS、前端、拓展资源、福利、休息视频 )
     *
     * @param type    GankType
     * @param refresh 是否是刷新
     * @param oldPage olaPage==GankTypeDict.DONT_SWITCH表示不是切换数据
     */
    public void getData(final int type, final boolean refresh, final int oldPage) {
        if (oldPage != GankTypeDict.DONT_SWITCH) {
            this.page = 1;
        }
        String gankType = GankTypeDict.type2UrlTypeDict.get(type);
        if (gankType == null) return;
        mCompositeSubscription.add(mDataManager.getDataByNetWork(gankType, GankApi.DEFAULT_DATA_SIZE, page)
                .subscribe(new Subscriber<ArrayList<BaseGankData>>() {
                    @Override
                    public void onCompleted() {
                        if (mCompositeSubscription != null) {
                            mCompositeSubscription.remove(this);
                        }
                    }

                    @Override
                    public void onError(final Throwable e) {
                        try {
                            Logger.d(e.getMessage());
                        } catch (Throwable e1) {
                            e1.getMessage();
                        } finally {
                            if (refresh) {
                                Type resultType = new TypeToken<ArrayList<BaseGankData>>() {
                                }.getType();
                                reservoirUtils.get(type + "", resultType, new ReservoirGetCallback<ArrayList<BaseGankData>>() {
                                    @Override
                                    public void onSuccess(ArrayList<BaseGankData> gankDatas) {
                                        if (oldPage != GankTypeDict.DONT_SWITCH) {
                                            if (getMvpView() != null) {
                                                getMvpView().onSwitchSuccess(type);
                                                getMvpView().onGetDataSuccess(gankDatas, refresh);
                                                getMvpView().onFailure(e);
                                            }

                                        }
                                    }

                                    @Override
                                    public void onFailure(Exception e) {
                                        if (getMvpView() != null) {
                                            switchFailure(oldPage, e);
                                        }
                                    }
                                });
                            } else {
                                if (getMvpView() != null) {
                                    getMvpView().onFailure(e);
                                }
                            }
                        }
                    }

                    @Override
                    public void onNext(ArrayList<BaseGankData> gankDatas) {

                        if (oldPage != GankTypeDict.DONT_SWITCH) {
                            if (getMvpView() != null) {
                                getMvpView().onSwitchSuccess(type);
                                getMvpView().onGetDataSuccess(gankDatas, refresh);
                            }
                        }
                        if (refresh) {
                            reservoirUtils.refresh(type + "", gankDatas);
                        }
                    }
                }));
    }

    /**
     * 切换分类失败
     *
     * @param oldPage oldPage
     */
    private void switchFailure(int oldPage, Throwable e) {
                /*
         * 如果是切换数据源
         * 刚才尝试的page＝1失败了的请求
         * 加载失败
         * 会影响到原来页面的page
         * 在这里执行复原page操作
         */
        if (oldPage != GankTypeDict.DONT_SWITCH) {
            page = oldPage;
        }
        if (getMvpView() != null) {
            getMvpView().onFailure(e);
        }
    }

}
