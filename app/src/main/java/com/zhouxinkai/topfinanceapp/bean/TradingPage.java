package com.zhouxinkai.topfinanceapp.bean;

/**
 * Created by Administrator on 2016/1/13 0013.
 */
public class TradingPage {

    private TradingPage(){}

    private static class TradingPageClass{
        private static TradingPage instance = new TradingPage();
    }

    public static TradingPage getInstance(){
        return TradingPageClass.instance;
    }

    /**
     * 默认的情况下显示的是左侧的"虚拟交易"页面
     */
    public boolean isRealTrade = true;
}
