package com.zhouxinkai.topfinanceapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import com.zhouxinkai.topfinanceapp.global.GlobalParams;
import com.zhouxinkai.topfinanceapp.view.manager.BottomManager;
import com.zhouxinkai.topfinanceapp.view.manager.MiddleContentManager;
import com.zhouxinkai.topfinanceapp.view.manager.TitleManager;

/**
 *
 * 配置文件中原来的style是 android:theme="@style/AppTheme"
 *
 */
public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    /**
     * 1、给actiity设置layout布局之前的准备工作()
     * 2、设置layout布局
     * 3、初始化相关管理者
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initWindowParams();

        GlobalParams.initActivityContext(this);

        setContentView(R.layout.activity_main);

        initManager();
    }

    /**
     * init some window parameters
     */
    private void initWindowParams(){
        //the soft input_pan will top up the radiogroup in the bottom without following codes
        //beacuse the EditText will be focused once come into homeFragment
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }



    /**
     * 初始化的一些动作:
     * 1、初始化底部导航栏的Manager
     * 2、初始化中间内容区域的Manager
     * 3、将 【观察者(顶部导航栏--和--中间内容区域)】注册到【被观察者(底部导航栏)的身上】
     *   3-1、设置默认的页面
     */
    public void initManager() {
        BottomManager bottomManagerInstance = BottomManager.getInstance();
        MiddleContentManager middleContentManagerInstance = MiddleContentManager.getInstance();
        TitleManager titleManagerInstance = TitleManager.getInstance();


        bottomManagerInstance.init(this);
        middleContentManagerInstance.init(this);
        titleManagerInstance.init(this);

        bottomManagerInstance.addObserver(middleContentManagerInstance);
        bottomManagerInstance.addObserver(titleManagerInstance);

        //设置默认的页面
        bottomManagerInstance.setDefaultUI();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
