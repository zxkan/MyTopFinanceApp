package com.zhouxinkai.topfinanceapp.view.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhouxinkai.topfinanceapp.R;
import com.zhouxinkai.topfinanceapp.base.BaseFragment;
import com.zhouxinkai.topfinanceapp.global.ConstantValue;
import com.zhouxinkai.topfinanceapp.utils.CommonUtil;
import com.zhouxinkai.topfinanceapp.utils.LogCat;
import com.zhouxinkai.topfinanceapp.view.widget.RollViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Danial Zhou on 2016/1/11 0011.
 */
public class HomeFragment extends BaseFragment{

    private final String TAG = "HomeFragment";

    /**
     * 存储轮播点的集合
     */
    private List<View> dotViewList;

    /**
     * 默认的图片集合
     */
    private List<Integer> defaultUrlList;

    /**
     * RollViewPager的实例
     */
    private RollViewPager rollViewPager;

    /**
     * 存储RollViewPager中轮播图的url地址
     */
    private List<String> urlList;

    /**
     * HomeFragment的初始化方法
     *  1、创建存储轮播点的集合
     *  2、创建存储RollViewPager中显示图片的集合
     *  3、创建默认的存储的图片的集合并添加数据
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogCat.i(TAG, "------HomeFragment---onCreate--------------");

        dotViewList = new ArrayList<View>();

        urlList = new ArrayList<String>();

        defaultUrlList = new ArrayList<Integer>();
        defaultUrlList.add(R.drawable.ic_launcher);
        defaultUrlList.add(R.drawable.icon_regist);
        defaultUrlList.add(R.drawable.tabbar_icon_trade);
        defaultUrlList.add(R.drawable.icon_regist);
        defaultUrlList.add(R.drawable.my_icon_logo);


        //------------------给pullToRefresh模拟的添加头的操作------------
        mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(mStrings));
        arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,mListItems);
    }


    /**
     * pullToRefresh对应的Layout
     */
    private View rootView;

    /**
     * PullToRefresh对应的id
     */
    private PullToRefreshListView pull_refresh_list;

    /**
     * 轮播图对应的Layout
     */
    private View turnPlayPictureView;
    /**
     * 一、实例化布局:
     *    1、加载HomeFragment的布局: rootView
     *    2、通过rootView 找到【轮播图区域】的占位View : ll_turn_play_area
     *    3、通过rootView 找到【内容区域】的占位View : ll_contentArea
     *    4、【轮播图区域】的占位View 做移除操作
     *    5、初始化【轮播图】的view ( 轮播图 = RollViewPager + 轮播点 )
     *    6、将【轮播图】的view添加到【轮播图区域】占位View身上
     *
     */
    @Override
    public View initView(LayoutInflater inflater) {
        //整体PullToRefresh对应的Layout
        rootView = inflater.inflate(R.layout.layout_home_fragment, null);
        pull_refresh_list = (PullToRefreshListView) rootView.findViewById(R.id.pull_refresh_list);

        //轮播图对应的Layout
        turnPlayPictureView = initTurnPlayPicture(inflater); //是一个LinearLayout

        pull_refresh_list.getRefreshableView().addHeaderView(turnPlayPictureView);
        pull_refresh_list.setAdapter(arrayAdapter);
        return rootView;
    }

    /**
     * 添加数据的方法
     */
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    /**
     * 标识当前页面的唯一ID
     */
    public int getFragmentId() {

        return ConstantValue.HOME_FRAGMENT; //0
    }

    /**
     * HomeFragment销毁的时候调用的方法
     */
    @Override
    public void onPause() {
        super.onPause();
        rollViewPager.stopTask();
    }

    /**
     * 初始化轮播图 :
     *    注意：【轮播图】包含两个内容:
     *         RollViewPager和轮播点
     * 步骤:
     *    一、找到布局及控件:
     *       1、【轮播图】的布局
     *       2、【轮播图】的RollViewPager区域
     *       3、【轮播图】的轮播点区域
     *    二、初始化轮播点
     *    三、初始化RollViewPager
     *    四、返回【轮播图】的布局
     */
    private View initTurnPlayPicture(LayoutInflater inflater){
        //==============================================================================
        View turnPlayPicture = inflater.inflate(R.layout.layout_turn_play_picture_tool, null);
        LinearLayout ll_rollViewPagerArea = (LinearLayout) turnPlayPicture.findViewById(R.id.ll_rollViewPagerArea);
        LinearLayout ll_dotArea = (LinearLayout) turnPlayPicture.findViewById(R.id.ll_dotArea);

        //==============================================================================
        ll_dotArea.removeAllViews();
        dotViewList.clear();
        View view;
        int dotCount = urlList.size() == 0 ? defaultUrlList.size() :urlList.size();
        for(int i = 0 ; i < dotCount ; i++){
            view = new View(context);
            if(i == 0){
                view.setBackgroundResource(R.drawable.bg_dot_selected);
            }else{
                view.setBackgroundResource(R.drawable.bg_dot_normal);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    CommonUtil.dip2px(context,6), CommonUtil.dip2px(context, 6)
            );
            params.setMargins(5,0,5,0);
            ll_dotArea.addView(view,params); //显示view
            dotViewList.add(view); //管理轮播点的集合
        }

        //==============================================================================
        ll_rollViewPagerArea.removeAllViews();
        rollViewPager = new RollViewPager(context, new RollViewPager.OnPageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(context,"position = " + position, Toast.LENGTH_SHORT).show();
            }
        });
        rollViewPager.setUrlList(urlList);
        rollViewPager.setDefaultUrlList(defaultUrlList);
        rollViewPager.setDotParams(dotViewList);
        ll_rollViewPagerArea.addView(rollViewPager);
        rollViewPager.startScroll();

        //==============================
        return turnPlayPicture;
    }

    /**
     *
     View rootView = inflater.inflate(R.layout.layout_home_fragment, null);
     LinearLayout ll_turn_play_area = (LinearLayout) rootView.findViewById(R.id.ll_turn_play_area);
     RelativeLayout rl_contentArea = (RelativeLayout) rootView.findViewById(R.id.rl_contentArea);
     ll_turn_play_area.removeAllViews();

     View turnPlayPictureView = initTurnPlayPicture(inflater);
     ll_turn_play_area.addView(turnPlayPictureView);
     *
     */
    private String[] mStrings = { "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler" };
    private LinkedList<String> mListItems;

    private ArrayAdapter<String> arrayAdapter;
}