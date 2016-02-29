package com.zhouxinkai.topfinanceapp.view.manager;

import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import com.zhouxinkai.topfinanceapp.MainActivity;
import com.zhouxinkai.topfinanceapp.R;
import com.zhouxinkai.topfinanceapp.global.ConstantValue;
import com.zhouxinkai.topfinanceapp.view.content.CubeFragment;
import com.zhouxinkai.topfinanceapp.view.content.DynamicFragment;
import com.zhouxinkai.topfinanceapp.view.content.HomeFragment;
import com.zhouxinkai.topfinanceapp.view.content.MyChooseFragment;
import com.zhouxinkai.topfinanceapp.view.content.TradingFragment;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Administrator on 2016/1/12 0012.
 */
public class MiddleContentManager implements Observer{

    /**
     * Static Inner Singleton Pattern
     */
    private MiddleContentManager(){}

    private static class MiddleContentManagerInnerClass{
        private static MiddleContentManager instance = new MiddleContentManager();
    }

    public static MiddleContentManager getInstance(){
        return MiddleContentManagerInnerClass.instance;
    }

    /**
     * context of MainActivity
     */
    private MainActivity mainActivity;

    /**
     * 占位区域的帧布局,用与Fragment进行替换
     */
    private FrameLayout middleContainer;

    /**
     * Fragment的管理类
     */
    private FragmentManager fragmentManager;



    private HomeFragment homeFragment;
    private MyChooseFragment myChooseFragment;
    private DynamicFragment dynamicFragment;
    private CubeFragment cubeFragment;
    private TradingFragment tradingFragment;

    /**
     * 初始化的方法
     */
    public void init(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        middleContainer = (FrameLayout) mainActivity.findViewById(R.id.container_activity);

        fragmentManager = mainActivity.getSupportFragmentManager();

        homeFragment = new HomeFragment();
        myChooseFragment = new MyChooseFragment();
        dynamicFragment = new DynamicFragment();
        cubeFragment = new CubeFragment();
        tradingFragment = new TradingFragment();
    }

    /**
     * BottomManager中RadioGroup(被观察者)的状态发生改变所触发的方法
     */
    @Override
    public void update(Observable observable, Object data) {
        if(data!=null){
            int fragmentId = Integer.parseInt(data.toString());

            switch (fragmentId){
                case ConstantValue.HOME_FRAGMENT: //首页
                    fragmentManager.beginTransaction().
                            replace(R.id.container_activity,homeFragment,"homeFragment").
                            commit();

                    break;
                case ConstantValue.MY_CHOOSE_FRAGMENT: //自选
                    fragmentManager.beginTransaction().
                            replace(R.id.container_activity,myChooseFragment,"myChooseFragment").
                            commit();
                    break;
                case ConstantValue.DYNAMIC_FRAGMENT: //动态
                    fragmentManager.beginTransaction().
                            replace(R.id.container_activity, dynamicFragment, "dynamicFragment").
                            commit();

                    break;
                case ConstantValue.CUDE_FRAGMENT: //组合
                    fragmentManager.beginTransaction().
                            replace(R.id.container_activity, cubeFragment, "cubeFragment").
                            commit();
                    break;
                case ConstantValue.TRADING_FRAGMENT: //交易
                    fragmentManager.beginTransaction().
                            replace(R.id.container_activity,tradingFragment,"tradingFragment").
                            commit();
                    break;
            }
        }
    }
}
