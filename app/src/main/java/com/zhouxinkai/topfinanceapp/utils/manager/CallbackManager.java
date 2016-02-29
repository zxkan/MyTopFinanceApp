package com.zhouxinkai.topfinanceapp.utils.manager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/5 0005.
 */
public class CallbackManager {

    /**
     * 存储回调的集合
     */
    private static List<BaseCallback> callbacklist = new ArrayList<BaseCallback>();

    /**
     * 注册回调函数
     * @param callbackName
     * @param callback
     */
    public static void registCallback(String callbackName,BaseCallback callback){
        for(int i = 0 ; i < callbacklist.size();i++){
            if(callbacklist.get(i).getCallbackName().equals(callbackName)){
                callback.setCallbackName(callbackName);
                callbacklist.set(i,callback);
                return ;
            }
        }

        callback.setCallbackName(callbackName);
        callbacklist.add(callback);
    }


    /**
     * 触发所有的回调
     */
    public static void invokeAllCallback(){
        if(callbacklist!=null && callbacklist.size()>0){
            for(BaseCallback callback : callbacklist){
                if(callback.isUsed()){
                    callback.callback(callback.getCallbackName());
                }
            }
        }
    }

    /**
     * 触发指定的回调
     */
    public static void invokeAppointedCallback(String callbackName){
        if(callbackName==null){
            throw new RuntimeException("-------回调的名称不能为null-------");
        }
        if(callbacklist!=null && callbacklist.size()>0){
            for(BaseCallback callback : callbacklist){
                if(callback.getCallbackName().equals(callbackName)){
                    if(callback.isUsed()){
                        callback.callback(callback.getCallbackName());
                        return ;
                    }
                }
            }
            throw new RuntimeException("---------没有注册名称的回调--------");
        }
    }
}
