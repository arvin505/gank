package com.arvin.gank.core;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.arvin.gank.R;

import butterknife.BindView;

/**
 * Created by xiaoyi on 2016/6/24.
 */
public abstract class BaseToolbarActivity extends BaseAppCompatActivity {
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    protected ActionBarHelper mActionBarHelper;

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        this.initToolbarHelper();
    }

    private void initToolbarHelper() {
        if (toolbar == null || mAppBarLayout == null) return;
        setSupportActionBar(toolbar);
        mActionBarHelper = createActionBarHelper();
        mActionBarHelper.init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mAppBarLayout.setElevation(6.6f);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showBack() {
        if (mActionBarHelper != null) {
            mActionBarHelper.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void setAppBarLayoutAlpha(float alpha) {
        mAppBarLayout.setAlpha(alpha);
    }

    public void setAppBarLayoutVisibility(boolean visible) {
        if (mAppBarLayout != null) {
            mAppBarLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    private ActionBarHelper createActionBarHelper() {
        return new ActionBarHelper();
    }

    public class ActionBarHelper {
        private final ActionBar mActionBar;
        public CharSequence mDrawerTitle;
        public CharSequence mTitle;

        public ActionBarHelper() {
            this.mActionBar = getSupportActionBar();
        }

        public void init() {
            if (mActionBar == null) {
                return;
            }
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setDisplayShowHomeEnabled(false);
            mTitle = mDrawerTitle = getTitle();
        }

        public void onDrawerClosed() {
            if (mActionBar == null) return;
            mActionBar.setTitle(mTitle);
        }

        public void onDrawerOpened() {
            if (mActionBar == null) return;
            mActionBar.setTitle(mDrawerTitle);
        }

        public void setTitle(String title) {
            this.mTitle = title;
        }

        public void setDrawerTitle(String title) {
            this.mDrawerTitle = title;
        }

        public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
            if (mActionBar == null) return;
            mActionBar.setDisplayHomeAsUpEnabled(showHomeAsUp);
        }
    }
}
