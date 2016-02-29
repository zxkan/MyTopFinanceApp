package com.zhouxinkai.topfinanceapp.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Daniel X.K. Chow on 2016/1/13 0013.
 */
public class HttpInterface {


    private static RequestQueue requestQueue;

    /**
     * init Volley Request Object
     */
    public static void initRequestQueue(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
    }

    /**
     * 释放指定标识的请求
     */
    public static void releaseRequest(String tag) {

        requestQueue.cancelAll(tag);
    }

    /**
     * 释放所有的请求
     */
    public static void releaseAllRequest() {

        requestQueue.cancelAll(new Object());
    }


    /**
     * 获取String类型的数据
     *
     * @param method            request method
     * @param url               接口地址
     * @param params            请求参数
     * @param listener          成功的回调
     * @param errorListener     失败的回调
     * @param tag               请求标记
     * @param timeOut           超时时长
     * @param maxRequestTimes   最大的重复的请求次数
     * @param backoffMultiplier 重复请求的间隔提升因子
     */
    public static void getStringRequest(int method, String url, final Map<String, String> params,
                                        Response.Listener<String> listener, Response.ErrorListener errorListener,
                                        String tag, int timeOut, int maxRequestTimes, float backoffMultiplier
    ) {

        StringRequest stringRequest = new StringRequest(method, url, listener, errorListener) {
        };
        if (params != null && params.size() > 0) {
            stringRequest = new StringRequest(method, url, listener, errorListener) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return params;
                }
            };
        }
        stringRequest.setTag(tag);
        stringRequest.setShouldCache(false);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(timeOut, maxRequestTimes, backoffMultiplier));
        requestQueue.add(stringRequest);
    }

    /**
     * 获取Json格式的数据
     *
     * @param method            请求的方法
     * @param url               请求的url地址
     * @param jsonRequest       请求的参数
     * @param listener          请求成功的方法回调
     * @param errorListener     请求失败的方法回调
     * @param tag               请求的标识
     * @param timeOut           超时的时长
     * @param maxNumRetries     最大的重复请求次数
     * @param backoffMultiplier 重复请求的间隔提升因子
     */
    public static void getJsonObjectRequest(int method, String url, JSONObject jsonRequest,
                                            Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, String tag, int timeOut, int maxNumRetries,
                                            float backoffMultiplier) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method, url, jsonRequest, listener, errorListener);
        jsonObjectRequest.setTag(tag);
        jsonObjectRequest.setShouldCache(false);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(timeOut, maxNumRetries, backoffMultiplier));
        requestQueue.add(jsonObjectRequest);
    }
}
