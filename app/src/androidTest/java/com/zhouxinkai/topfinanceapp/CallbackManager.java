package com.zhouxinkai.topfinanceapp;

import com.zhouxinkai.topfinanceapp.utils.LogCat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/29 0029.
 */
public class CallbackManager {

    private static final String TAG = CallbackManager.class.getSimpleName();

    /**
     * 存储回调的集合
     */
    private static List<BaseCallback> callbackList = new ArrayList<BaseCallback>();

    /**
     * 注册回调
     * @param callbackName
     * @param callback
     */
    public static void registCallback(String callbackName,BaseCallback callback){

        for(int i = 0 ; i < callbackList.size();i++){
            if(callbackList.get(i).equals(callbackName)){
                callback.setCallbackName(callbackName);
                callbackList.set(i,callback);
                return ;
            }
        }

        callback.setCallbackName(callbackName);
        callbackList.add(callback);
    }

    /**
     * 触发所有的回调
     */
    public static void invokeAllCallback(){
        if(callbackList!=null && callbackList.size()>0){
            for(BaseCallback callback : callbackList){
                callback.callback(callback.getCallbackName());
            }
        }
    }

    /**
     * 触发指定名称的回调
     * @param callbackName
     */
    public static void invokeAppointedCallback(String callbackName){
        if(callbackList!=null && callbackList.size()>0){
            for(int i = 0 ; i < callbackList.size();i++){
                if(callbackName.equals(callbackList.get(i).getCallbackName())){
                    callbackList.get(i).callback(callbackName);
                    return ;
                }
            }
        }
        LogCat.i(TAG,"----------不符合callbackList!=null和callbackList.size()>0----------");
    }
}
