package com.zhouxinkai.topfinanceapp.view.manager;


import android.widget.RadioGroup;

import com.zhouxinkai.topfinanceapp.MainActivity;
import com.zhouxinkai.topfinanceapp.R;
import com.zhouxinkai.topfinanceapp.global.ConstantValue;

import java.util.Observable;

/**
 * Created by Daniel X.K Chow on 2016/1/11 0011.
 *
 * The Manager of TabBar in MainActivity
 *
 * I regard BottomManager as an Observable,
 * once BottomManager change,the middle container and
 * title area follow it.
 */
public class BottomManager extends Observable{


    private final String TAG = "BottomManager";

    /**
     * 使用静态内部的单例模式
     */
    private BottomManager(){}

    private static class BottomManagerInnerClass{
        private static BottomManager getBottomManagerInstance = new BottomManager();
    }

    public static BottomManager getInstance(){
        return BottomManagerInnerClass.getBottomManagerInstance;
    }

    /**
     * MainActivity传递过来的上下文
     */
    private MainActivity mainActivity;

    /**
     * 底部的RadioGroup
     */
    private RadioGroup main_radio;


    /**
     * BottomManager初始化的方法
     */
    public void init(MainActivity context){
        this.mainActivity = context;

        main_radio = (RadioGroup) mainActivity.findViewById(R.id.main_radio);
        MyCheckedChangeListener myCheckedChangeListener = new MyCheckedChangeListener();
        main_radio.setOnCheckedChangeListener(myCheckedChangeListener);
    }

    /**
     * 设置默认选中的页面
     */
    public void setDefaultUI(){
        main_radio.check(R.id.rb_home); //默认选中的是首页
    }

    /**
     * RadioGroup的选择监听事件
     */
    private class MyCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        /**
         * 定义一个变量,用来记录当前RadioGroup选中的是哪个RadioButton
         */
        private int fragmentId;

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_home:
                    fragmentId = ConstantValue.HOME_FRAGMENT; //HOME
                    break;
                case R.id.rb_myChoose:
                    fragmentId = ConstantValue.MY_CHOOSE_FRAGMENT; //MyChoose
                    break;
                case R.id.rb_dynamic:
                    fragmentId = ConstantValue.DYNAMIC_FRAGMENT; //dynamic
                    break;
                case R.id.rb_group:
                    fragmentId = ConstantValue.CUDE_FRAGMENT; //cube
                    break;
                case R.id.rb_trading:
                    fragmentId = ConstantValue.TRADING_FRAGMENT; //trade
                    break;
            }

            /*
             * 选中某个RadioButton以后触发观察者,
             * 即显示某个标题栏
             */
            invokeObserver(fragmentId);
        }
    }

    /**
     *  触发观察者
     *  观察者是:MainActivity和TitleManager
     */
    public void invokeObserver(int fragmentId){
        super.setChanged();
        super.notifyObservers(fragmentId);
    }
}