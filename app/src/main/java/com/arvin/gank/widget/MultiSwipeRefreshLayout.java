package com.arvin.gank.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.arvin.gank.R;

/**
 * Created by arvin on 2016/6/26.
 */
public class MultiSwipeRefreshLayout extends SwipeRefreshLayout {

    private CanChildScrollUpCallback mCanChildScrollUpCallback;

    private Drawable mForgroundDrawable;

    public MultiSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public MultiSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MultiSwipeRefreshLayout);
        mForgroundDrawable = array.getDrawable(R.styleable.MultiSwipeRefreshLayout_forground);
        if (mForgroundDrawable != null) {
            mForgroundDrawable.setCallback(this);
            setWillNotDraw(false);
        }
        array.recycle();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (mForgroundDrawable != null) {
            mForgroundDrawable.draw(canvas);
        }
    }

    public void setCanChildScrollUpCallback(CanChildScrollUpCallback mCanChildScrollUpCallback) {
        this.mCanChildScrollUpCallback = mCanChildScrollUpCallback;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mForgroundDrawable != null) {
            mForgroundDrawable.setBounds(0, 0, w, h);
        }
    }

    public interface CanChildScrollUpCallback {
        boolean canSwipeRefreshChildScrollUp();
    }

    @Override
    public boolean canChildScrollUp() {
        if (mCanChildScrollUpCallback != null) {
            return mCanChildScrollUpCallback.canSwipeRefreshChildScrollUp();
        }
        return super.canChildScrollUp();
    }
}
