package com.arvin.gank.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arvin.gank.R;
import com.arvin.gank.annotations.LayoutId;
import com.arvin.gank.bean.BaseGankData;
import com.arvin.gank.core.BaseToolbarActivity;

import java.util.ArrayList;

/**
 * Created by xiaoyi on 2016/7/14.
 */

@LayoutId(R.layout.activity_daily_detail)
public class DailyDetailActivity extends BaseToolbarActivity {

    private static final String EXTRA_DETAIL = "com.camnter.easygank.EXTRA_DETAIL";
    private static final String EXTRA_TITLE = "com.camnter.easygank.EXTRA_TITLE";

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }



    public static void startActivity(Context context, String title, ArrayList<ArrayList<BaseGankData>> detail) {
        Intent intent = new Intent(context, DailyDetailActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_DETAIL, detail);
        context.startActivity(intent);
    }
}
