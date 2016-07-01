package com.arvin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.arvin.easyrecyclerview.widget.decorator.adpater.EasyRecyclerViewAdapter;
import com.arvin.easyrecyclerview.widget.decorator.holder.EasyRecyclerViewHolder;
import com.arvin.gank.R;


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
        return 0;
    }

    @Override
    public void onBindRecycleViewHolder(EasyRecyclerViewHolder viewHolder, int position) {
        int layoutType = getRecycleViewItemType(position);
        switch (layoutType) {
            case LAYOUT_TYPE_DAILY:
                break;
            case LAYOUT_TYPE_TECHNOLOGY:
                break;
            case LAYOUT_TYPE_WELFARE:
                break;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public interface OnClickListener {
        void onClickPicture(String url, String title, View view);
    }
}
