package com.zhouxinkai.topfinanceapp.view.content.mailing;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhouxinkai.topfinanceapp.R;
import com.zhouxinkai.topfinanceapp.bean.MailingDataBean;
import com.zhouxinkai.topfinanceapp.global.GlobalParams;
import com.zhouxinkai.topfinanceapp.utils.LogCat;

import java.util.List;

/**
 * Created by Daniel X.K. Chow on 2016/2/28 0028.
 */
public class MailingActivity extends AppCompatActivity {


    private static final String TAG = MailingActivity.class.getSimpleName();


    private static final int VIRTUAL_DATA = 0;

    /**
     * RecyclerView
     */
    RecyclerView mailing_recyclerView;


    /**
     * 定义数据是否已经准备好
     */
    private boolean isDataReady = false;

    /**
     * 1、初始化数据
     * 2、初始化控件
     * 注意：
     * 初始化数据的方法要写在初始化控件的前面,
     * 因为我们使用的控件是RecyclerView,
     * 要在构造方法处就将数据添加好
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailing);

        initWidget();
        initDataSource();
    }


    private MailingRecyclerAdapter myAdapter;

    /**
     * 初始化RecyclerView:
     * 1、找到控件
     * 2、设置LayoutManager
     * 3、设置Adapter
     * (1)让MailingRecyclerAdapter extends RecyclerView.Adapter<MailingRecyclerAdapter.MyHolder>
     * (2)创建MyHolder
     * (3)让MyHolder extends RecyclerView.ViewHolder
     * (4)创建MyHolder的构造方法
     * (5)写onCreateViewHolder()方法，返回new MyHolder(),并添加参数
     * (6)在MyHolder()中讲参数分配到成员变量处
     * (7)getItemCount()方法
     * (8)onBindViewHolder()方法:
     * 讲参数的myHolder设置一个方法： setDataAndRefreshUI()
     * (9)在MyHolder类中写setDataAndRefreshUI()方法,并使用成员变量setText()
     */
    private void initWidget() {
        mailing_recyclerView = (RecyclerView) findViewById(R.id.mailing_recyclerView);
    }


    /**
     * 初始化数据的来源
     */
    private void initDataSource() {
        boolean isNetRecorvery = GlobalParams.isOpenConnect;
        if (isNetRecorvery) {
            getDataFromNet();
        } else {
            getVirtualDataFromLocal();
        }
    }

    /**
     * 从本地获取虚拟的数据
     */
    public void getVirtualDataFromLocal() {
        LogCat.i(TAG, "------------使用虚拟数据--------------");

        MailingDataInterface.getMailData(new MailingDataInterface.getVirtualDataCallback() {

            @Override
            public void callback(List<MailingDataBean> mailList) { //子线程
                LogCat.i(TAG, "---获取到的数据是 : " + mailList);

                Message msg = new Message();
                msg.obj = mailList;
                msg.what = VIRTUAL_DATA;
                dataHandler.sendMessage(msg);
            }
        });
    }

    /**
     * 数据传递到主线程
     */
    private Handler dataHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case VIRTUAL_DATA:
                    LogCat.i(TAG, "----走到Handler里面的线程是 : " + Thread.currentThread());
                    List<MailingDataBean> dataList = (List<MailingDataBean>) msg.obj;
                    initDataEvent(dataList);
                    break;
                default:
                    break;
            }

        }
    };

    /**
     * 将数据赋值到成员变量处,千万别忘了
     *
     * @param mailingDatas
     */
    public void initDataEvent(List<MailingDataBean> mailingDatas) { //Main Thread

        //给RecyclerView添加Manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mailing_recyclerView.setLayoutManager(layoutManager);

        //给RecyclerView添加数据适配器
        myAdapter = new MailingRecyclerAdapter(this, mailingDatas);
        mailing_recyclerView.setAdapter(myAdapter);

        //给RecyclerView添加分割线
        mailing_recyclerView.addItemDecoration(new ItemDivider(this,R.drawable.shape_recyclerview_diver));
    }

    /**
     * 从网络中获取数据
     */
    public void getDataFromNet() {
        LogCat.i(TAG, "------开启网络连接-------");//TODO

    }
}
