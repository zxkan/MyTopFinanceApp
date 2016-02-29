package com.zhouxinkai.topfinanceapp;

import android.test.AndroidTestCase;

import com.zhouxinkai.topfinanceapp.utils.LogCat;

/**
 * Created by Administrator on 2016/2/29 0029.
 */
public class TestCallback extends AndroidTestCase{


    private static final String TAG = TestCallback.class.getSimpleName();

    private static final String CALLBACK_NAME = "hahacallback";

    public void testCallback(){

        CallbackManager.registCallback("hahacallback", new BaseCallback() {

            @Override
            public void callback(String callbackName) {

                LogCat.i(TAG,"callbackName : " + callbackName);
            }
        });


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * 触发指定的回调
         */
        CallbackManager.invokeAppointedCallback(CALLBACK_NAME);
    }
}
