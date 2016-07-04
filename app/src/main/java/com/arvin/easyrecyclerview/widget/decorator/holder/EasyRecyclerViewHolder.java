package com.arvin.easyrecyclerview.widget.decorator.holder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.arvin.gank.R;

/**
 * Created by xiaoyi on 2016/7/1.
 */
public class EasyRecyclerViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;
    private View convertView;

    public EasyRecyclerViewHolder(View convertView) {
        super(convertView);
        views = new SparseArray<>();
        this.convertView = convertView;
    }

    public void setOnItemClickListener(final OnItemClickListener listener, final int position) {
        if (listener == null) {
            itemView.setOnClickListener(null);
        } else {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, position);
                }
            });
        }
    }

    public void setOnItemLongClickListener(final OnItemLongClickListener listener, final int position) {
        if (listener == null) {
            itemView.setOnLongClickListener(null);
        } else {
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return listener.onItemLongClick(v, position);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View convertView, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View convertView, int position);
    }

    public  <T extends View> T findViewById(int id) {
        View view = views.get(id);
        if (view == null) {
            view = convertView.findViewById(id);
            views.put(id, view);
        }
        return (T) view;
    }
}
