package com.zhouxinkai.topfinanceapp.view.detail;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zhouxinkai.topfinanceapp.utils.LogCat;

/**
 * Created by Administrator on 2016/1/13 0013.
 */
public class VirtualTradingPage extends PageOfTradingFragment{

    private static final String TAG = "VirtualTradingPage";

    public VirtualTradingPage(Context context) {
        super(context);
    }

    /**
     * 初始化布局
     */
    @Override
    public View initView() {
        LogCat.i(TAG,"------VirtualTradingPage---------initView");
        TextView textView = new TextView(context);
        textView.setText("************虚拟交易************");
        return textView;
    }

    /**
     * 初始化页面的方法
     */
    @Override
    public void initPageData() {
        LogCat.i(TAG,"------VirtualTradingPage---------initPageData");
    }

    /**
     * 重写扫尾的工作
     */
    @Override
    public void onRelease() {
        LogCat.i(TAG,"------VirtualTradingPage--------onRelease");
    }

    /**
     * 标识页面的id
     */
    @Override
    public int getPageId() {
        return 0;
    }
}
