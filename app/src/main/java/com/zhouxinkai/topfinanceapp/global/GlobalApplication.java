package com.zhouxinkai.topfinanceapp.global;

import android.app.Application;
import android.content.Context;

import com.zhouxinkai.topfinanceapp.utils.HttpInterface;
import com.zhouxinkai.topfinanceapp.view.content.mailing.VirtualData;

/**
 * Created by Daniel X.K. Chow on 2016/1/11 0011.
 */
public class GlobalApplication extends Application{

    /**
     * 1、get Global Context of the Application
     * 2、init HttpInterface
     * 3、init Global Context of the application
     * 4、init imageLoader
     * 5、init VirtualData
     */
    @Override
    public void onCreate() {
        super.onCreate();

        Context globalContext = getApplicationContext();
        GlobalParams.init(globalContext);
        HttpInterface.initRequestQueue(globalContext);
        GlobalParams.initImageLoader(globalContext);
        VirtualData.getInstance().initVirtualClassInterface();
    }
}
