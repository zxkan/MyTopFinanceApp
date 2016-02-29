package com.zhouxinkai.topfinanceapp.view.detail;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zhouxinkai.topfinanceapp.global.ConstantValue;
import com.zhouxinkai.topfinanceapp.utils.LogCat;

/**
 * Created by Daniel X.K. Chow on 2016/1/13 0013.
 */
public class RealTradingPage extends PageOfTradingFragment {

    private static String TAG = "RealTradingPage";

    public RealTradingPage(Context context) {
        super(context);
    }

    /**
     * 初始化布局
     */
    @Override
    public View initView() {
        LogCat.i(TAG,"------RealTradingPage-------initView");
        TextView textView = new TextView(context);
        textView.setText("***********实盘操作**************");
        return textView;
    }

    /**
     * 初始化页面的数据
     */
    @Override
    public void initPageData() {
        LogCat.i(TAG,"-----RealTradingPage--------initPageData");
    }

    /**
     * 重写扫尾的工作
     */
    @Override
    public void onRelease() {
        LogCat.i(TAG,"-----RealTradingPage------onRelease");
    }

    /**
     * 标识当前页面的唯一id
     * @return
     */
    @Override
    public int getPageId() {
        return ConstantValue.REAL_TRADE_PAGE_IN_TRADING_FRAGMENT;//41
    }
}
