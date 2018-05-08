package com.hoooopa.hoopa.hoopa.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

/**
 * Created by Pray on 2018/5/4.
 */

public class TranslucentNestedScrollView extends NestedScrollView{

    private OnScrollChangedListener mOnScrollChangedListener;


    public TranslucentNestedScrollView(@NonNull Context context) {
        super(context);
    }

    public TranslucentNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TranslucentNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        mOnScrollChangedListener = listener;
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(NestedScrollView who, int l, int t, int oldl, int oldt);
    }

}
