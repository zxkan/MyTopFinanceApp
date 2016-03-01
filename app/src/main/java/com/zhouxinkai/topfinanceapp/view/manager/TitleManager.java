package com.zhouxinkai.topfinanceapp.view.manager;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhouxinkai.topfinanceapp.MainActivity;
import com.zhouxinkai.topfinanceapp.R;
import com.zhouxinkai.topfinanceapp.bean.TradingCategoryInTitle;
import com.zhouxinkai.topfinanceapp.bean.TradingPage;
import com.zhouxinkai.topfinanceapp.global.ConstantValue;
import com.zhouxinkai.topfinanceapp.view.content.mailing.MailingActivity;

import java.util.Observable;
import java.util.Observer;

import de.greenrobot.event.EventBus;

/**
 * Created by Daniel Zhou on 2016/1/12 0012.
 */
public class TitleManager implements Observer,View.OnClickListener{

    /**
     * 静态内部的单例模式
     */
    private TitleManager(){}

    private static class TitleManagerClass{
        private static TitleManager titleManagerInstance = new TitleManager();
    }

    public static TitleManager getInstance(){
        return TitleManagerClass.titleManagerInstance;
    }

    //=========================================

    /**
     * MainActivity传递过来的上下文
     */
    private MainActivity mainActivity;

    /**
     * 带有搜索框的标题栏
     */
    private LinearLayout title_bar_with_search;

    /**
     * "栏自选"标题
     */
    private RelativeLayout title_bar_myChoose;

    /**
     * "动态"标题栏
     */
    private RelativeLayout title_bar_dynamic;

    /**
     * "组合"标题栏
     */
    private RelativeLayout title_bar_cube;

    /**
     * "交易"标题栏
     */
    private RelativeLayout title_bar_trading;

    /**
     * 首页中左上角的“邮件”图标
     */
    private ImageButton mailInHome;

    /**
     * 实盘操作
     */
    private TextView real_trade;

    /**
     * 虚拟交易
     */
    private TextView virtual_trade;

    /**
     * 浅灰色
     */
    private int normal_color_in_trade;

    /**
     * 白色
     */
    private int main_white;

    /**
     * 初始化标题区域
     *  1、实例化5个页面对应的标题布局
     *  2、找到一些控件:
     *     (1)实例化“交易”页面的顶部两个可以点击的TextView并设置监听
     *     (2)实例化首页（带有搜索框的）左上角的邮件图标
     *  3、初始化颜色值
     *  4、给“交易”页面的点击事件注册EventBus事件
     */
    public void init(MainActivity mainActivity){
        this.mainActivity = mainActivity;

       title_bar_with_search = (LinearLayout) mainActivity.findViewById(R.id.title_bar_with_search);
       title_bar_myChoose = (RelativeLayout) mainActivity.findViewById(R.id.title_bar_myChoose);
       title_bar_dynamic = (RelativeLayout) mainActivity.findViewById(R.id.title_bar_dynamic);
       title_bar_cube = (RelativeLayout) mainActivity.findViewById(R.id.title_bar_cube);
       title_bar_trading = (RelativeLayout) mainActivity.findViewById(R.id.title_bar_trading);

        real_trade = (TextView) mainActivity.findViewById(R.id.real_trade); //真实交易
        virtual_trade = (TextView) mainActivity.findViewById(R.id.virtual_trade);//虚拟交易
        mailInHome = (ImageButton) mainActivity.findViewById(R.id.ib_message_in_search_title);//左上角的邮件图片
        real_trade.setOnClickListener(this);
        virtual_trade.setOnClickListener(this);
        mailInHome.setOnClickListener(this);

        normal_color_in_trade = mainActivity.getResources().getColor(R.color.normal_color_in_trade);
        main_white =  mainActivity.getResources().getColor(R.color.main_white);


        EventBus.getDefault().register(this,"onEventByTradingFragment");
    }

    /**
     * 初始化标题栏,所有的标题栏全部设置为GONE
     */
    public void initTitle(){
        title_bar_with_search.setVisibility(View.GONE);
        title_bar_myChoose.setVisibility(View.GONE);
        title_bar_dynamic.setVisibility(View.GONE);
        title_bar_cube.setVisibility(View.GONE);
        title_bar_trading.setVisibility(View.GONE);
    }

    /**
     * 显示HOME页标题栏
     */
    public void showHomeTitle(){
        initTitle();
        title_bar_with_search.setVisibility(View.VISIBLE);
    }

    /**
     * 显示"自选"页的标题栏
     */
    public void showChooseTitle(){
        initTitle();
        title_bar_myChoose.setVisibility(View.VISIBLE);
    }

    /**
     * 显示"动态"页的标题
     */
    public void showDynamicTitle(){
        initTitle();
        title_bar_dynamic.setVisibility(View.VISIBLE);
    }

    /**
     * 显示“组合”页的标题
     */
    public void showCubeTitle(){
        initTitle();
        title_bar_cube.setVisibility(View.VISIBLE);
    }

    /**
     * 显示"交易"页的标题
     */
    public void showTradingTitle(){
        initTitle();
        title_bar_trading.setVisibility(View.VISIBLE);
    }


    /**
     * 观察者模式调用的方法
     */
    @Override
    public void update(Observable observable, Object data) {
        if(data!=null){
            int fragmentId = Integer.parseInt(data.toString());
            switch (fragmentId){
                case ConstantValue.HOME_FRAGMENT: //首页
                    showHomeTitle();
                    break;
                case ConstantValue.MY_CHOOSE_FRAGMENT: //自选
                    showChooseTitle();
                    break;
                case ConstantValue.DYNAMIC_FRAGMENT: //动态
                    showDynamicTitle();
                    break;
                case ConstantValue.CUDE_FRAGMENT: //组合
                    showCubeTitle();
                    break;
                case ConstantValue.TRADING_FRAGMENT: //交易
                    showTradingTitle();
                    break;
            }
        }
    }


    /**
     * 交易页面的两个TextView的点击方法
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.real_trade:
                realTradeClick(); //实盘交易
                break;
            case R.id.virtual_trade:
                virtualTradeClick(); //虚拟交易
                break;
            case R.id.ib_message_in_search_title:
                jump2MailingActivity(); //跳转到邮件箱
                break;
        }
    }


    /**
     * “实盘交易”点击的时候调用的方法
     * 点击后发送的事件传递到TradingFragment的onEventMainThread()方法
     */
    public void realTradeClick(){
        real_trade.setBackgroundResource(R.drawable.bg_real_trade_when_click);
        virtual_trade.setBackgroundResource(R.drawable.bg_virtual_trade_when_normal);
        real_trade.setTextColor(main_white);
        virtual_trade.setTextColor(normal_color_in_trade);

        //将是否是实盘交易的状态改为true
        TradingPage.getInstance().isRealTrade=true;
        EventBus.getDefault().post(TradingPage.getInstance());
    }

    /**
     * “虚盘交易”点击的时候调用的方法
     * 点击后发送的事件传递到TradingFragment的onEventMainThread()方法
     */
    public void virtualTradeClick(){
        real_trade.setBackgroundResource(R.drawable.bg_real_trade_when_normal);
        virtual_trade.setBackgroundResource(R.drawable.bg_virtual_trade_when_click);
        real_trade.setTextColor(normal_color_in_trade);
        virtual_trade.setTextColor(main_white);

        //将是否是实盘交易的状态改为false
        TradingPage.getInstance().isRealTrade=false;
        EventBus.getDefault().post(TradingPage.getInstance());
    }

    /**
     * 跳转到“邮件”Activity
     */
    private void jump2MailingActivity() {
        Intent intent = new Intent(mainActivity,MailingActivity.class);
        mainActivity.startActivity(intent);
    }

    /**
     * 监听TradingFragment的ViewPage的页面改变事件，用来改变两个TextView相应的颜色
     * 在本类中进行注册
     *
     * 发送的方法在TradingFragment的MyPageChangeListener类中,
     * 当滑动TradingFragment中ViewPager的时候,改变交易页面标题处的TextView的颜色
     */
    public void onEventByTradingFragment(TradingCategoryInTitle tradingCategoryInTitle){
        if(tradingCategoryInTitle!=null){
            if(tradingCategoryInTitle.isRealTrading){
                realTradeClick();
            }else{
                virtualTradeClick();
            }
        }
    }
}
