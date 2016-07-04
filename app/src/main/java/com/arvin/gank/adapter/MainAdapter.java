package com.arvin.gank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arvin.easyrecyclerview.widget.decorator.adpater.EasyRecyclerViewAdapter;
import com.arvin.easyrecyclerview.widget.decorator.holder.EasyRecyclerViewHolder;
import com.arvin.gank.R;
import com.arvin.gank.bean.BaseGankData;
import com.arvin.gank.bean.GankDaily;
import com.arvin.gank.constant.Constant;
import com.arvin.gank.gank.GankApi;
import com.arvin.gank.gank.GankType;
import com.arvin.gank.utils.DateUtils;
import com.arvin.gank.utils.GlideUtils;


/**
 * Created by xiaoyi on 2016/7/1.
 */
public class MainAdapter extends EasyRecyclerViewAdapter {

    public static final int LAYOUT_TYPE_DAILY = 0;
    public static final int LAYOUT_TYPE_TECHNOLOGY = 1;
    public static final int LAYOUT_TYPE_WELFARE = 2;

    private Context context;

    private int type;

    private OnClickListener listener;

    public MainAdapter(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    @Override
    public int[] getItemLayouts() {
        return new int[]{R.layout.item_daily, R.layout.item_data, R.layout.item_welfare};
    }

    @Override
    public int getRecycleViewItemType(int position) {
        switch (type) {
            case GankType.daily:
                return LAYOUT_TYPE_DAILY;
            case GankType.android:
            case GankType.ios:
            case GankType.js:
            case GankType.resources:
            case GankType.video:
            case GankType.app:
                return LAYOUT_TYPE_TECHNOLOGY;
            case GankType.welfare:
                return LAYOUT_TYPE_WELFARE;
            default:
                return LAYOUT_TYPE_DAILY;
        }
    }

    @Override
    public void onBindRecycleViewHolder(EasyRecyclerViewHolder viewHolder, int position) {
        int layoutType = getRecycleViewItemType(position);
        switch (layoutType) {
            case LAYOUT_TYPE_DAILY:
                loadingDaily(viewHolder, position);
                break;
            case LAYOUT_TYPE_TECHNOLOGY:
                loadingDaily(viewHolder, position);
                break;
            case LAYOUT_TYPE_WELFARE:
                loadingDaily(viewHolder, position);
                break;
        }
    }

    private void loadingDaily(EasyRecyclerViewHolder viewHolder, int position) {
        GankDaily dailyData = getItem(position);
        if (null == dailyData) return;
        ImageView dailyIv = viewHolder.findViewById(R.id.daily_iv);
        TextView dailyDateTV = viewHolder.findViewById(R.id.daily_date_tv);
        TextView dailyTitleTV = viewHolder.findViewById(R.id.daily_title_tv);

        TextView androidTagTV = viewHolder.findViewById(R.id.daily_android_tag_tv);
        TextView iOSTagTV = viewHolder.findViewById(R.id.daily_ios_tag_tv);
        TextView jsTagTV = viewHolder.findViewById(R.id.daily_js_tag_tv);

        if (dailyData.results.videoData != null && dailyData.results.videoData.size() > 0) {
            BaseGankData video = dailyData.results.videoData.get(0);
            dailyTitleTV.setText(video.desc.trim());
            dailyDateTV.setText(
                    DateUtils.date2String(video.publishedAt.getTime(), Constant.DAILY_DATE_FORMAT));
        } else if (dailyData.results.welfareData != null &&
                dailyData.results.welfareData.size() > 0) {
            BaseGankData welfare = dailyData.results.welfareData.get(0);
            dailyTitleTV.setText(welfare.desc.trim());
            dailyDateTV.setText(DateUtils.date2String(welfare.publishedAt.getTime(),
                    Constant.DAILY_DATE_FORMAT));
        } else {
            dailyTitleTV.setText("这期没福利了，安心学习吧！");
            dailyDateTV.setText("");
        }

        // 图片
        if (dailyData.results.welfareData != null && dailyData.results.welfareData.size() > 0) {
            final BaseGankData welfare = dailyData.results.welfareData.get(0);
            GlideUtils.display(dailyIv, welfare.url);
            dailyIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (MainAdapter.this.listener != null) {
                        MainAdapter.this.listener.onClickPicture(welfare.url, welfare.desc, v);
                    }
                }
            });
        } else {
            GlideUtils.displayNative(dailyIv, R.mipmap.img_default_gray);
        }

         /*
         * 标签 ListView 和 RecyclerView 都要写else 因为复用问题
         * 忧伤
         */
        if (dailyData.category == null) {
            androidTagTV.setVisibility(View.GONE);
            iOSTagTV.setVisibility(View.GONE);
            jsTagTV.setVisibility(View.GONE);
        } else {
            if (dailyData.category.contains(GankApi.DATA_TYPE_ANDROID)) {
                androidTagTV.setVisibility(View.VISIBLE);
            } else {
                androidTagTV.setVisibility(View.GONE);
            }
            if (dailyData.category.contains(GankApi.DATA_TYPE_IOS)) {
                iOSTagTV.setVisibility(View.VISIBLE);
            } else {
                iOSTagTV.setVisibility(View.GONE);
            }
            if (dailyData.category.contains(GankApi.DATA_TYPE_JS)) {
                jsTagTV.setVisibility(View.VISIBLE);
            } else {
                jsTagTV.setVisibility(View.GONE);
            }
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void onClickPicture(String url, String title, View view);
    }
}
