package com.zhouxinkai.topfinanceapp.view.content;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhouxinkai.topfinanceapp.R;
import com.zhouxinkai.topfinanceapp.base.BaseFragment;
import com.zhouxinkai.topfinanceapp.bean.TradingCategoryInTitle;
import com.zhouxinkai.topfinanceapp.bean.TradingPage;
import com.zhouxinkai.topfinanceapp.global.ConstantValue;
import com.zhouxinkai.topfinanceapp.utils.LogCat;
import com.zhouxinkai.topfinanceapp.view.detail.PageOfTradingFragment;
import com.zhouxinkai.topfinanceapp.view.detail.RealTradingPage;
import com.zhouxinkai.topfinanceapp.view.detail.VirtualTradingPage;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Daniel X.K. Chow on 2016/1/11 0011.
 */
public class TradingFragment extends BaseFragment{

    /**
     * 交易页面的标记
     */
    private final String TAG = "TradingFragment";

    /**
     * 填充内容区域的ViewPager
     */
    private ViewPager container_in_tradingFragment;

    /**
     * 用于填充ViewPager的页面集合
     */
    private List<PageOfTradingFragment> pageList = new ArrayList<PageOfTradingFragment>();

    /**
     * ViewPager的数据适配器
     */
    private  TradingFragmentAdapter tradingFragmentAdapter;

    /**
     * ViewPager页面改变的方法
     */
    private MyPageChangeListener pageChangeListener;


    /**
     * “交易”页面的初始化的方法
     *
     * super.onCreate()这句代码极其重要,
     * 由于有了他,我们既实现了自己的onCreate,也走了父类的onCreate,
     * 更重要的是,走父类的onCreate()会拿到上下文对象context
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogCat.i(TAG, "------TradingFragment---onCreate--------------context : " + context);
        EventBus.getDefault().register(this, "onEventMainThread");

        //初始化的时候向集合中添加数据,
        pageList.add(new RealTradingPage(context));
        pageList.add(new VirtualTradingPage(context));
    }

    /**
     *  1、拿到布局View
     *  2、拿到布局View对应的id
     *  3、设置数据适配器
     *  4、监听页面改变的方法
     */
    @Override
    public View initView(LayoutInflater inflater) {
        View containerViewPager = inflater.inflate(R.layout.trading_fragment_view, null);
        container_in_tradingFragment = (ViewPager) containerViewPager.findViewById(R.id.container_in_tradingFragment);

        tradingFragmentAdapter = new TradingFragmentAdapter();
        container_in_tradingFragment.setAdapter(tradingFragmentAdapter);
        pageChangeListener = new MyPageChangeListener();
        container_in_tradingFragment.setOnPageChangeListener(pageChangeListener);

        return containerViewPager;
    }

    /**
     * 说明:
     *     加载数据的方法
     *     默认的情况下加载"实盘交易"的页面
     * 注意:
     *     这个方法会调用非这个页面的onRelease()方法
     *     因此在具体的页面onRelease()的时候应该进行数据的非空判断
     *     否则要报空指针异常
     */
    @Override
    public void initData(Bundle savedInstanceState) {
        pageList.get(0).initPageData();
    }

    /**
     * 说明:
     *    此类是监听ViewPager页面改变的实现类,
     *
     * 要做的事情:
     *    1、改变标题处两个TextView的颜色:
     *        使用EventBus发送JavaBean的方式通知标题区域改变相应TextView的背景及字体颜色
     *        接收事件的位置在TitleManager中的onEventByTradingFragment()方法
     *
     *    2、重新加载两个页面(RealTradingPage和VirtualTradingPage)的数据
     *    3、释放非当前页面的数据
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            if(position==0){
                TradingCategoryInTitle.getInstance().isRealTrading = true;
                EventBus.getDefault().post(TradingCategoryInTitle.getInstance());

                pageList.get(position).initPageData();
                pageList.get(1).onRelease();
            }else{
                TradingCategoryInTitle.getInstance().isRealTrading = false;
                EventBus.getDefault().post(TradingCategoryInTitle.getInstance());

                pageList.get(position).initPageData();
                pageList.get(0).onRelease();
            }

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

    /**
     * ViewPager的数据适配器,
     * 实际上就是给ViewPager添加两个页面而已
     */
    private class TradingFragmentAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return pageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(pageList.get(position).getRootView());
            return pageList.get(position).getRootView();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView((View) object);
        }
    }

    /**
     * 说明:
     *     在TitleManager中,触发realTradeClick()或者virtualTradeClick()方法后
     *     EventBus会将事件传递到这里
     *
     * 方法在本类中的onCreate()中注册,
     * 事件发送的位置在TitleManager
     */
    public void onEventMainThread(TradingPage tradingPage){
        if(tradingPage!=null){
            if(tradingPage.isRealTrade){
                container_in_tradingFragment.setCurrentItem(0);
            }else{
                container_in_tradingFragment.setCurrentItem(1);
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 标识“交易”页面的唯一Id
     */
    @Override
    public int getFragmentId() {

        return ConstantValue.TRADING_FRAGMENT;//40
    }
}
