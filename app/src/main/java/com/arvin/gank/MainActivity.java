package com.arvin.gank;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.arvin.easyrecyclerview.widget.decorator.EasyBorderDividerItemDecoration;
import com.arvin.gank.annotations.LayoutId;
import com.arvin.gank.core.BaseAppCompatActivity;
import com.arvin.gank.core.BaseDrawerLayoutActivity;
import com.arvin.gank.core.mvp.BasePresenter;
import com.arvin.gank.presenter.MainPresenter;
import com.arvin.gank.widget.EasyRecyclerView;

import butterknife.BindView;

@LayoutId(R.layout.activity_main)
public class MainActivity extends BaseDrawerLayoutActivity {
    @BindView(R.id.main_rv)
    EasyRecyclerView mainRv;

    private EasyBorderDividerItemDecoration dataDecoration;
    private EasyBorderDividerItemDecoration welfareDecoration;
    private LinearLayoutManager mLinearLayoutManager;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    private MainPresenter presenter;


    

    @Override
    protected NavigationView.OnNavigationItemSelectedListener getNavigationItemSelectedListener() {
        return null;
    }

    @Override
    protected int[] getMenuItemIds() {
        return new int[0];
    }

    @Override
    protected void onMenuItemOnclick(MenuItem now) {

    }

    @Override
    protected void onSwipeRefresh() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }
}
