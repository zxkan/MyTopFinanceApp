package com.zhouxinkai.topfinanceapp.bean;

/**
 * Created by Daniel Zhou on 2016/1/13 0013.
 */
public class TradingCategoryInTitle {


    private TradingCategoryInTitle(){}

    private static class  TradingCategoryInTitleClass{
        private static TradingCategoryInTitle instance = new TradingCategoryInTitle();
    }

    public static TradingCategoryInTitle getInstance(){
        return TradingCategoryInTitleClass.instance;
    }


    public boolean isRealTrading = true;
}
