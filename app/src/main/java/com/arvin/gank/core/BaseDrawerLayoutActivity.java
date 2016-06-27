package com.arvin.gank.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;

import com.arvin.gank.R;

import java.util.HashMap;

import butterknife.BindView;

/**
 * Created by xiaoyi on 2016/6/27.
 */
public abstract class BaseDrawerLayoutActivity extends BaseSwipeRefreshLayoutActivity {
    @BindView(R.id.root_view)
    protected DrawerLayout mDrawerLayout;

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    private ActionBarDrawerToggle mDrawerToggle;

    protected HashMap<Integer, MenuItem> mMenuItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getNavigationItemSelectedListener() != null) {
            mNavigationView.setNavigationItemSelectedListener(getNavigationItemSelectedListener());
        }
        mDrawerLayout.addDrawerListener(new EasyDrawerListener());
    }

    protected abstract NavigationView.OnNavigationItemSelectedListener getNavigationItemSelectedListener();

    protected abstract int[] getMenuItemIds();

    protected abstract void onMenuItemOnclick(MenuItem now);


    private class EasyDrawerListener implements DrawerLayout.DrawerListener {

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(View drawerView) {
            mDrawerToggle.onDrawerOpened(drawerView);
        }

        @Override
        public void onDrawerClosed(View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    }

}
