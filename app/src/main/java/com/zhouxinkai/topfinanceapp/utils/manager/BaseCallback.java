package com.zhouxinkai.topfinanceapp.utils.manager;

/**
 * Created by Daniel X.K. Chow on 2016/2/5 0005.
 */
public abstract class BaseCallback {

    private String callbackName;

    public boolean isUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    private boolean isUsed;

    public void setCallbackName(String callbackName) {
        this.callbackName = callbackName;
    }

    public String getCallbackName() {
        return callbackName;
    }


    public abstract void callback(String callbackName);
}
