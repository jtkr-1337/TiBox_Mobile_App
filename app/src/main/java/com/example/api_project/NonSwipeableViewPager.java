package com.example.api_project;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

public class NonSwipeableViewPager extends ViewPager {
    private boolean swipeEnabled;
    public NonSwipeableViewPager(Context context) {
        super(context);
        this.swipeEnabled = true;
    }
    public NonSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.swipeEnabled = true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.swipeEnabled){

        }
        return this.swipeEnabled && super.onTouchEvent(event);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.swipeEnabled && super.onInterceptTouchEvent(event);
    }
    public void setSwipeEnabled(boolean enabled) {
        this.swipeEnabled = enabled;
    }
}