package com.zhouxinkai.topfinanceapp.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/1/11 0011.
 */
public class LogCat {

    public static boolean isOpen = true;

    public static void i(String TAG,String msg){
        if(isOpen){
            Log.i(TAG,msg);
        }
    }
}
