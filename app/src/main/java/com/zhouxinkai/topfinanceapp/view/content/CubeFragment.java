package com.zhouxinkai.topfinanceapp.view.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhouxinkai.topfinanceapp.base.BaseFragment;
import com.zhouxinkai.topfinanceapp.global.ConstantValue;
import com.zhouxinkai.topfinanceapp.utils.LogCat;

/**
 * Created by Administrator on 2016/1/11 0011.
 */
public class CubeFragment extends BaseFragment{

    private final String TAG = "CubeFragment";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogCat.i(TAG, "------CubeFragment---onCreate--------------");
    }

    @Override
    public View initView(LayoutInflater inflater) {
        TextView textView = new TextView(context);
        textView.setText("组合");
        return textView;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getFragmentId() {
        return ConstantValue.CUDE_FRAGMENT;
    }
}
