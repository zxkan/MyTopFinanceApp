package com.zhouxinkai.topfinanceapp.view.content;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhouxinkai.topfinanceapp.R;
import com.zhouxinkai.topfinanceapp.bean.MailingDataBean;
import com.zhouxinkai.topfinanceapp.utils.HttpInterface;
import com.zhouxinkai.topfinanceapp.view.adapter.MailingRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel X.K. Chow on 2016/2/28 0028.
 */
public class MailingActivity extends AppCompatActivity {

    /**
     * RecyclerView
     */
    RecyclerView mailing_recyclerView;


    private int[] mListIcons = new int[]{R.mipmap.g1, R.mipmap.g2, R.mipmap.g3, R.mipmap.g4,
            R.mipmap.g5, R.mipmap.g6, R.mipmap.g7, R.mipmap.g8, R.mipmap.g9, R.mipmap.g10, R
            .mipmap.g11, R.mipmap.g12, R.mipmap.g13, R.mipmap.g14, R.mipmap.g15, R.mipmap
            .g16, R.mipmap.g17, R.mipmap.g18, R.mipmap.g19, R.mipmap.g20, R.mipmap.g21, R
            .mipmap.g22, R.mipmap.g23, R.mipmap.g24, R.mipmap.g25, R.mipmap.g26, R.mipmap
            .g27, R.mipmap.g28, R.mipmap.g29};

    private int[] mStraggeredIcons = new int[]{R.mipmap.p1, R.mipmap.p2, R.mipmap.p3, R
            .mipmap.p4, R.mipmap.p5, R.mipmap.p6, R.mipmap.p7, R.mipmap.p8, R.mipmap.p9, R
            .mipmap.p10, R.mipmap.p11, R.mipmap.p12, R.mipmap.p13, R.mipmap.p14, R.mipmap
            .p15, R.mipmap.p16, R.mipmap.p17, R.mipmap.p18, R.mipmap.p19, R.mipmap.p20, R
            .mipmap.p21, R.mipmap.p22, R.mipmap.p23, R.mipmap.p24, R.mipmap.p25, R.mipmap
            .p26, R.mipmap.p27, R.mipmap.p28, R.mipmap.p29, R.mipmap.p30, R.mipmap.p31, R
            .mipmap.p32, R.mipmap.p33, R.mipmap.p34, R.mipmap.p35, R.mipmap.p36, R.mipmap
            .p37, R.mipmap.p38, R.mipmap.p39, R.mipmap.p40, R.mipmap.p41, R.mipmap.p42, R
            .mipmap.p43, R.mipmap.p44};


    /**
     * 1、初始化相应的控件
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailing);

        initWidget();
        initDataSource();
        initDataFromNet();
    }

    /**
     * 从网络上拉取数据
     */
    private void initDataFromNet() {
        
    }

    /**
     * 初始化RecyclerView:
     * 1、找到控件
     * 2、设置LayoutManager
     * 3、设置Adapter
     *    (1)让MailingRecyclerAdapter extends RecyclerView.Adapter<MailingRecyclerAdapter.MyHolder>
     *    (2)创建MyHolder
     *    (3)让MyHolder extends RecyclerView.ViewHolder
     *    (4)创建MyHolder的构造方法
     *    (5)写onCreateViewHolder()方法，返回new MyHolder(),并添加参数
     *    (6)在MyHolder()中讲参数分配到成员变量处
     *    (7)getItemCount()方法
     *    (8)onBindViewHolder()方法:
     *       讲参数的myHolder设置一个方法： setDataAndRefreshUI()
     *    (9)在MyHolder类中写setDataAndRefreshUI()方法,并使用成员变量setText()
     */
    private void initWidget() {
        mailing_recyclerView = (RecyclerView) findViewById(R.id.mailing_recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mailing_recyclerView.setLayoutManager(layoutManager);

        MailingRecyclerAdapter myAdapter = new MailingRecyclerAdapter(this, mailingDatas);
        mailing_recyclerView.setAdapter(myAdapter);
    }


    /**
     * TODO
     * 模拟的数据源之后要进行改变
     */
    private List<MailingDataBean> mailingDatas = new ArrayList<MailingDataBean>();

    /**
     * 初始化数据源:
     * 当前的数据源是我们本地自带的，之后改成从网络中请求数据
     */
    private void initDataSource() {

        MailingDataBean bean;
        for (int i = 0; i < mListIcons.length; i++) {
            bean = new MailingDataBean();
            bean.iconId = mListIcons[i];
            bean.text = "我是item : " + "[" + i + "]";
            mailingDatas.add(bean);
        }
    }
}
