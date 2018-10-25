package com.bwie.liu1025.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class MyFluidView extends ViewGroup {

    private int mLeftMargin = 20;
    private int mTopMargin = 20;

    public MyFluidView(Context context) {
        this(context, null);
    }

    public MyFluidView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyFluidView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int leftMargin = mLeftMargin;
        int topMargin = mTopMargin;

        int viewHeight = 0;
        int viewWidth = 0;

        //父控件传进来的宽度和高度以及对应的测量模式
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        switch (modeHeight) {
            case MeasureSpec.AT_MOST:
                int measuredHeight = 0;
                for (int j = 0; j < getChildCount(); j++) {
                    int measuredWidth = getChildAt(j).getMeasuredWidth();
                    measuredHeight = getChildAt(j).getMeasuredHeight();
                    if (leftMargin + measuredWidth + mLeftMargin > getWidth()) {
                        leftMargin = mLeftMargin;
                        topMargin += measuredHeight + mTopMargin;
                    }
                    leftMargin += measuredWidth + mLeftMargin;
                }
                topMargin += measuredHeight + mTopMargin;
                break;
        }
        setMeasuredDimension(sizeWidth, topMargin);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int leftMargin = mLeftMargin;
        int topMargin = mTopMargin;

        for (int j = 0; j < getChildCount(); j++) {
            int measuredWidth = getChildAt(j).getMeasuredWidth();
            int measuredHeight = getChildAt(j).getMeasuredHeight();
            if (leftMargin + measuredWidth + mLeftMargin > getWidth()) {
                leftMargin = mLeftMargin;
                topMargin += measuredHeight + mTopMargin;
                getChildAt(j).layout(leftMargin, topMargin, leftMargin + measuredWidth, topMargin + measuredHeight);
            } else {
                getChildAt(j).layout(leftMargin, topMargin, leftMargin + measuredWidth, measuredHeight + topMargin);
            }
            leftMargin += measuredWidth + mLeftMargin;
        }
    }
}
