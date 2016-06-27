package com.arvin.gank.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;

import com.arvin.gank.R;
import com.arvin.gank.widget.MultiSwipeRefreshLayout;

import butterknife.BindView;

/**
 * Created by xiaoyi on 2016/6/26.
 */
public abstract class BaseSwipeRefreshLayoutActivity extends BaseToolbarActivity {
    @BindView(R.id.multi_swipe_refresh_layout)
    protected MultiSwipeRefreshLayout mMultiSwipeRefreshLayout;
    private boolean refreshStatus = false;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initMultiSwipeRefreshLayout();
    }

    private void initMultiSwipeRefreshLayout() {
        if (mMultiSwipeRefreshLayout != null) {
            mMultiSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
            mMultiSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    onSwipeRefresh();
                }
            });
        }

    }

    protected abstract void onSwipeRefresh();

    public void setRefreshStatus(boolean refreshStatus) {
        this.refreshStatus = refreshStatus;
    }

    public boolean isRefreshStatus() {
        return refreshStatus;
    }

    public void refresh(final boolean refresh) {
        if (mMultiSwipeRefreshLayout == null) {
            return;
        }
        if (!refresh && refreshStatus) {
            mMultiSwipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mMultiSwipeRefreshLayout.setRefreshing(false);
                    refreshStatus = false;
                }
            }, 1666);
        } else if (!refreshStatus) {
            mMultiSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mMultiSwipeRefreshLayout.setRefreshing(true);
                    refreshStatus = true;
                }
            });

        }
    }
}
