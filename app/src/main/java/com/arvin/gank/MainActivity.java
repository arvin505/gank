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

    private int emptyCount = 0;
    private static final int EMPTY_LIMIT = 5;

    private int gankType;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        dataDecoration = new EasyBorderDividerItemDecoration(getResources().getDimensionPixelOffset(R.dimen.data_border_divider_height),
                getResources().getDimensionPixelOffset(R.dimen.data_border_padding_infra_spans));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_about:
                showToast("关于");
                break;
            case R.id.menu_main_home_page:
                showToast("去主页");
                break;
            case R.id.menu_main_top_github:
                showToast("热门git");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

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
    protected void initData() {

    }
}
