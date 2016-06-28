package com.arvin.gank.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.arvin.gank.R;

import java.util.HashMap;
import java.util.Map;

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

        mMenuItems = new HashMap<>();
        int[] menuItemIds = getMenuItemIds();
        if (menuItemIds.length > 0) {
            for (int menuId : menuItemIds) {
                MenuItem menuItem = mNavigationView.getMenu().findItem(menuId);
                if (menuItem != null) {
                    mMenuItems.put(menuId, menuItem);
                }
            }
        }
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_menu, R.string.app_name);
    }


    protected abstract NavigationView.OnNavigationItemSelectedListener getNavigationItemSelectedListener();

    protected abstract int[] getMenuItemIds();

    protected abstract void onMenuItemOnclick(MenuItem now);

    protected boolean menuItemChecked(int itemId) {
        MenuItem old = null;
        MenuItem now = null;
        if (mMenuItems.containsKey(itemId)) {
            for (Map.Entry<Integer, MenuItem> menuEntry : mMenuItems.entrySet()) {
                MenuItem menuItem = menuEntry.getValue();
                int menuId = menuEntry.getKey();
                if (menuItem.isChecked()) {
                    old = menuItem;
                }
                if (old != null && menuId == old.getItemId()) {
                    break;
                }
                if (itemId == menuId) {
                    now = menuItem;
                    now.setChecked(true);
                    onMenuItemOnclick(now);
                } else {
                    menuItem.setChecked(false);
                }
            }
            mDrawerLayout.closeDrawer(mNavigationView);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mDrawerLayout.isDrawerOpen(mNavigationView)) {
            mDrawerLayout.closeDrawer(mNavigationView);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class EasyDrawerListener implements DrawerLayout.DrawerListener {

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            mDrawerToggle.onDrawerOpened(drawerView);
            if (mActionBarHelper != null) {
                mActionBarHelper.onDrawerOpened();
            }
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            mDrawerToggle.onDrawerClosed(drawerView);
            if (mActionBarHelper != null) {
                mActionBarHelper.onDrawerClosed();
            }
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            mDrawerToggle.onDrawerStateChanged(newState);
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
}
