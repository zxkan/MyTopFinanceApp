package com.zhouxinkai.topfinanceapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/17 0017.
 */
public class SharedPreferencesUtil {


    private static SharedPreferences sp;

    /**
     *
     * @param context  上下文
     * @param spName   SP的名字
     * @param dataName 数据的名字
     * @param dataString 字符串格式的数据
     */
    public static void saveData(Context context,String spName,String dataName,String dataString){

        if(sp == null){
            sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        }

        Editor edit = sp.edit();
        edit.clear().putString(dataName, dataString).commit();
    }

    /**
     * 获取数据调用的方法
     * @param context 上下文
     * @param spName  SP的名称
     * @param dataName 数据的名称
     * @return
     */
    public static String getData(Context context,String spName,String dataName,String defaultData){
        if(sp == null){
            sp = context.getSharedPreferences(spName,Context.MODE_PRIVATE);
        }
        String dataString = sp.getString(dataName, defaultData);
        return dataString;
    }
}
