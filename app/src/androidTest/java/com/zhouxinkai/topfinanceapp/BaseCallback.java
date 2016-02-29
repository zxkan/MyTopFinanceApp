package com.zhouxinkai.topfinanceapp;

/**
 * Created by Administrator on 2016/2/29 0029.
 */
public abstract class BaseCallback {

    private String callbackName;

    public void setCallbackName(String callbackName) {
        this.callbackName = callbackName;
    }

    public String getCallbackName() {
        return callbackName;
    }

    public abstract void callback(String callbackName);
}
