package com.arvin.gank.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by arvin on 2016/6/26.
 */
public class EasyRecyclerView extends RecyclerView {
    private LinearLayoutManager mLinearLayoutManager;

    public EasyRecyclerView(Context context) {
        super(context);
        initRecycleView(context);
    }

    public EasyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initRecycleView(context);
    }

    public EasyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initRecycleView(context);
    }

    private void initRecycleView(Context context) {
        mLinearLayoutManager = new LinearLayoutManager(context);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        setLayoutManager(mLinearLayoutManager);
        setItemAnimator(new DefaultItemAnimator());
        setHasFixedSize(true);
    }

    public LinearLayoutManager getLinearLayoutManager() {
        return mLinearLayoutManager;
    }

    public void setLinearLayoutManager(LinearLayoutManager manager) {
        mLinearLayoutManager = manager;
    }
}
