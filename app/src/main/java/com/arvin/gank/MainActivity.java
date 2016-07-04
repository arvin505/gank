package com.arvin.gank;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.arvin.easyrecyclerview.widget.decorator.EasyBorderDividerItemDecoration;
import com.arvin.gank.adapter.MainAdapter;
import com.arvin.gank.annotations.LayoutId;
import com.arvin.gank.bean.BaseGankData;
import com.arvin.gank.bean.GankDaily;
import com.arvin.gank.core.BaseDrawerLayoutActivity;
import com.arvin.gank.gank.GankType;
import com.arvin.gank.gank.GankTypeDict;
import com.arvin.gank.presenter.MainPresenter;
import com.arvin.gank.presenter.iview.MainView;
import com.arvin.gank.widget.EasyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


@LayoutId(R.layout.activity_main)
public class MainActivity extends BaseDrawerLayoutActivity implements MainView, MainAdapter.OnClickListener {
    @BindView(R.id.main_rv)
    EasyRecyclerView mainRv;

    private MainAdapter mainAdapter;

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
        dataDecoration = new EasyBorderDividerItemDecoration(
                getResources().getDimensionPixelOffset(R.dimen.data_border_divider_height),
                getResources().getDimensionPixelOffset(R.dimen.data_border_padding_infra_spans));
        welfareDecoration = new EasyBorderDividerItemDecoration(
                getResources().getDimensionPixelOffset(R.dimen.welfare_border_divider_height),
                getResources().getDimensionPixelOffset(R.dimen.welfare_border_padding_infra_spans));
        // mainRv.addItemDecoration(dataDecoration);
        //  mLinearLayoutManager = mainRv.getLinearLayoutManager();
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mActionBarHelper.setDrawerTitle("GANK MENU");

    }

    @Override
    protected void initData() {
        presenter = new MainPresenter();
        presenter.attachView(this);
        gankType = GankType.daily;
        mainAdapter = new MainAdapter(this, this.gankType);
        mainAdapter.setListener(this);
        mainRv.setAdapter(this.mainAdapter);
        refreshData(gankType);
    }

    private void refreshData(final int gankType) {
        presenter.setPage(1);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                refresh(true);
                switch (gankType) {
                    case GankType.daily:
                        presenter.getDaily(true, GankTypeDict.DONT_SWITCH);
                        break;
                    case GankType.android:
                    case GankType.ios:
                    case GankType.js:
                    case GankType.resources:
                    case GankType.welfare:
                    case GankType.video:
                    case GankType.app:
                        presenter.getData(gankType, true, GankTypeDict.DONT_SWITCH);
                        break;
                }
            }
        });
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
        refreshData(gankType);
    }

    @Override
    protected void initListeners() {

    }


    @Override
    public void onGetDailySuccess(List<GankDaily> dailyData, boolean refresh) {
        if (refresh) {
            this.emptyCount = 0;
            this.mainAdapter.clear();
            this.mainAdapter.setList(dailyData);
        } else {
            this.mainAdapter.addAll(dailyData);
        }
        this.mainAdapter.notifyDataSetChanged();
        this.refresh(false);
        if (dailyData.size() == 0) this.emptyCount++;
    }

    @Override
    public void onGetDataSuccess(List<BaseGankData> data, boolean refresh) {

    }

    @Override
    public void onSwitchSuccess(int type) {

    }

    @Override
    public void getDailyDetail(String title, ArrayList<ArrayList<BaseGankData>> detail) {

    }

    @Override
    public void onFailure(Throwable e) {
        this.refresh(false);
        this.setRefreshStatus(true);
        Snackbar.make(this.mainRv, R.string.main_load_error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onClickPicture(String url, String title, View view) {

    }
}
