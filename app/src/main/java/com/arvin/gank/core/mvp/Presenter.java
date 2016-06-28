package com.arvin.gank.core.mvp;

/**
 * Created by xiaoyi on 2016/6/28.
 */
public interface Presenter<V extends MvpView> {
    void attachView(V view);

    void detachView();

}
