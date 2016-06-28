package com.arvin.gank.core.mvp;

import com.arvin.gank.data.DataManager;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by xiaoyi on 2016/6/28.
 */
public class BasePresenter<V extends MvpView> implements Presenter<V> {
    private V mMvpView;
    public CompositeSubscription mCompositeSubscription;
    public DataManager mDataManager;

    @Override
    public void attachView(V view) {
        this.mMvpView = view;
        mCompositeSubscription = new CompositeSubscription();
        mDataManager = DataManager.getInstance();
    }

    @Override
    public void detachView() {
        mMvpView = null;
        mCompositeSubscription.unsubscribe();
        mCompositeSubscription = null;
        mDataManager = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMVpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (isViewAttached()) {
            throw new MvpViewNotAttachException();
        }
    }

    public static class MvpViewNotAttachException extends RuntimeException {
        public MvpViewNotAttachException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
