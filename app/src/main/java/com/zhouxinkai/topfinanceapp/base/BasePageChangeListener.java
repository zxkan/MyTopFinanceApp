package com.zhouxinkai.topfinanceapp.base;

import android.support.v4.view.ViewPager;

/**
 * Created by Daniel Zhou on 2016/1/16 0016.
 */
public abstract class BasePageChangeListener implements ViewPager.OnPageChangeListener{

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public abstract void onPageSelected(int position);

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
