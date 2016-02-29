package com.zhouxinkai.topfinanceapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhouxinkai.topfinanceapp.utils.LogCat;

/**
 * Created by Administrator on 2016/1/11 0011.
 *
 *  面板中的向下的箭头的按钮的作用是:
 *    修改了编译文件脚本的时候，点一下，重新编译
 */
public abstract class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";
    public View view;

    public Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogCat.i(TAG,"-------BaseFragment------onCreate---------");
        context = getActivity();
    }

    /**
     * 初始化View的方法
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = initView(inflater);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
    }

    /**
     * 初始化View的方法
     */
    public abstract View initView(LayoutInflater inflater);

    /**
     * 初始化数据的方法
     */
    public abstract void initData(Bundle savedInstanceState);


    /**
     * 当前Frgamnet的唯一标识
     */
    public abstract int getFragmentId();
}
