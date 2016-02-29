package com.zhouxinkai.topfinanceapp.view.detail;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2016/1/13 0013.
 */
public abstract class PageOfTradingFragment {


    public Context context;

    private View rootView;

    public PageOfTradingFragment(Context context){
        this.context = context;
        rootView = initView();
    }


    public View getRootView(){
        return rootView;
    }

    /**
     * 获取ViewPager每一页的布局
     */
    public abstract View initView();

    /**
     * 获取数据的方法
     */
    public abstract void initPageData();

    /**
     * 标识“实盘操作”还是“模拟操作”的id
     */
    public abstract int getPageId();

    /**
     * 离开本页面的时候做一些扫尾的工作
     */
    public abstract void onRelease();
}
