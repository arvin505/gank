package com.arvin.easyrecyclerview.widget.decorator;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xiaoyi on 2016/6/28.
 */
public class EasyBorderDividerItemDecoration extends RecyclerView.ItemDecoration {
    private final int verticalItemSpacingInPx;
    private final int horizontalItemSpacingInPx;

    public EasyBorderDividerItemDecoration(int verticalItemSpacingInPx, int horizontalItemSpacingInPx) {
        this.verticalItemSpacingInPx = verticalItemSpacingInPx;
        this.horizontalItemSpacingInPx = horizontalItemSpacingInPx;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int itemPosition = layoutParams.getViewLayoutPosition();
        int childCount = parent.getAdapter().getItemCount();
        int left = horizontalItemSpacingInPx;
        int right = horizontalItemSpacingInPx;

        int top = getItemTopSpace(itemPosition);
        int bottom = getItemBottomSpace(itemPosition, childCount);
        outRect.set(left, top, right, bottom);
    }

    private int getItemBottomSpace(int itemPosition, int childCount) {
        if (isLastItem(itemPosition, childCount)) {
            return verticalItemSpacingInPx;
        }
        return verticalItemSpacingInPx / 2;
    }

    private int getItemTopSpace(int itemPosition) {
        if (isFirstItem(itemPosition)) {
            return verticalItemSpacingInPx;
        }
        return verticalItemSpacingInPx / 2;
    }

    private boolean isFirstItem(int itemPosition) {
        return itemPosition == 0;
    }

    private boolean isLastItem(int itemPosition, int childCount) {
        return itemPosition == childCount - 1;
    }
}
