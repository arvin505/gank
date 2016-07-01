package com.arvin.easyrecyclerview.widget.decorator.adpater;

import android.support.v7.widget.RecyclerView;

import com.arvin.easyrecyclerview.widget.decorator.holder.EasyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by xiaoyi on 2016/7/1.
 */
public abstract class EasyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList mList;

    private EasyRecyclerViewHolder.OnItemClickListener onItemClickListener;
    private EasyRecyclerViewHolder.OnItemLongClickListener onItemLongClickListener;

    public EasyRecyclerViewAdapter() {
        this.mList = new ArrayList();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public int getListSize() {
        return mList.size();
    }

    public <T> T getItem(int position) {
        return (T) mList.get(position);
    }

    public <T> T getItemByPosition(int position) {
        return getItem(position);
    }

    public void setList(List list) {
        mList.clear();
        if (list == null) return;
        mList.addAll(list);
    }

    public void clear() {
        mList.clear();
    }

    public void remove(Object o) {
        mList.remove(o);
    }

    public List getList() {
        return mList;
    }

    public void addAll(Collection list) {
        mList.addAll(list);
    }

    public abstract int[] getItemLayouts();

    public abstract int getRecycleViewItemType(int position);

    @Override
    public int getItemViewType(int position) {
        return getRecycleViewItemType(position);
    }

    public abstract void onBindRecycleViewHolder(EasyRecyclerViewHolder viewHolder, int position);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EasyRecyclerViewHolder easyRecyclerViewHolder = (EasyRecyclerViewHolder) holder;
        onBindViewHolder(easyRecyclerViewHolder, position);
        if (onItemClickListener != null)
            easyRecyclerViewHolder.setOnItemClickListener(onItemClickListener, position);
        if (onItemLongClickListener != null)
            easyRecyclerViewHolder.setOnItemLongClickListener(onItemLongClickListener, position);
    }

    public void setOnItemClickListener(EasyRecyclerViewHolder.OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public void setOnItemLongClickListener(EasyRecyclerViewHolder.OnItemLongClickListener listener) {
        onItemLongClickListener = listener;
    }
}
