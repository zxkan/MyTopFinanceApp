package com.zhouxinkai.topfinanceapp.utils;

import android.content.Context;
import android.view.WindowManager;

import com.zhouxinkai.topfinanceapp.global.GlobalParams;

/**
 * Created by Daniel Zhou on 2016/1/15 0015.
 */
public class GlobalUtils {

    /**
     * 获取屏幕的宽度
     */
    public static int getWindowWidth(){
        WindowManager wm = (WindowManager) GlobalParams.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        int windowWidth = wm.getDefaultDisplay().getWidth();
        return windowWidth;
    }

    /**
     * 获取屏幕的高度
     * @return
     */
    public static int getWindowHeight(){
       WindowManager wm = (WindowManager) GlobalParams.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        int windowHeight = wm.getDefaultDisplay().getHeight();
        return windowHeight;
    }
}
