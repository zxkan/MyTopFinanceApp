package com.zhouxinkai.topfinanceapp.view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.zhouxinkai.topfinanceapp.R;
import com.zhouxinkai.topfinanceapp.base.BasePageChangeListener;
import com.zhouxinkai.topfinanceapp.global.GlobalParams;
import com.zhouxinkai.topfinanceapp.utils.GlobalUtils;
import com.zhouxinkai.topfinanceapp.utils.LogCat;

import java.util.List;

/**
 * Created by Daniel Zhou on 2016/1/14 0014.
 * 应该明确的是:
 * 1、整个轮播图的区域是由 ViewPager和轮播点组成的，
 * 不是说轮播图就只有ViewPager
 * 2、当前的类只能是控制轮播图中ViewPager和轮播点的转换,
 * 而且轮播点的创建是要放在和ViewPager同一个级别的
 */
public class RollViewPager extends ViewPager {

    private static final String TAG = "RollViewPager";

    /**
     * 传递进来的上下文
     */
    private Context context;

    /**
     * 标识ViewPager中当前页面的方法
     */
    private int currentPosition;

    /**
     * RollViewPager中的图片的url地址
     */
    private List<String> urlList;

    /**
     * 管理轮播点的集合
     */
    private List<View> dotViewList;

    /**
     * 标识是否开始轮播的字段
     */
    private boolean ifStart = false;

    /**
     * 轮播时自增当前页面的任务
     */
    private RunnableTask runnableTask;

    /**
     * 传递进来的默认图片的集合（本地爱的一些图片）
     */
    private List<Integer> defaultUrlList;

    /**
     * ViewPager的数据适配器
     */
    private RollViewPagerAdapter rollViewPagerAdapter;

    /**
     * RollViewPager的页面改变监听器
     */
    private RollViewPageChangeListener pageChangeListener;

    /**
     * 自定义的点击事件监听
     */
    private OnPageClickListener clickListener;


    public RollViewPager(Context context) {
        super(context);
        this.context = context;
    }

    public RollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    /**
     * 1、传递参数:
     *      上下文、ViewPager里面图片的数量
     * 2、初始化任务:
     *      任务是将ViewPager里的ImageView的当前角标自增
     * 3、初始化RollViewPager的监听
     * 4、将监听器设置到RollViewPage身上
     */
    public RollViewPager(Context context,OnPageClickListener clickListener){
        super(context);
        this.context = context;
        this.clickListener = clickListener;

        runnableTask = new RunnableTask();
        pageChangeListener = new RollViewPageChangeListener();

        //监听RollViewPager页面的改变
        this.setOnPageChangeListener(pageChangeListener);
     }

    /**
     * 传递图片的集合
     * @param urlList : 图片地址的集合
     */
    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    /**
     * 传递默认的图片的集合
     * @param defaultUrlList : 默认的图片的集合
     */
    public void setDefaultUrlList(List<Integer> defaultUrlList){
        this.defaultUrlList = defaultUrlList;
    }

    /**
     * 传递轮播点的数据
     * @param dotViewList 管理轮播点的集合
     */
    public void setDotParams(List<View> dotViewList) {
        this.dotViewList = dotViewList;
    }

    /**
     * 切换当前ViewPager显示的页面，调用startScroll()可以串起来
     */
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            LogCat.i(TAG,"-------handleMessgae");
            RollViewPager.this.setCurrentItem(currentPosition);
            startScroll();
        }
    };

    /**
     * 开始轮播:
     *   开始轮播的时候一定要更改ifStart字段
     */
    public void startScroll() {

        ifStart = true;

        if(rollViewPagerAdapter == null){
            rollViewPagerAdapter = new RollViewPagerAdapter();
            RollViewPager.this.setAdapter(rollViewPagerAdapter);
        }else{
            rollViewPagerAdapter.notifyDataSetChanged();
        }

        handler.postDelayed(runnableTask,3000);
    }

    /**
     * 自增RollViewPage里面页面角标的任务
     */
    private class RunnableTask implements  Runnable{

        @Override
        public void run() {
            if(ifStart){
                currentPosition = (currentPosition+1)%defaultUrlList.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }

    /**
     * 轮播图的数据适配器
     */
    private class RollViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            if(urlList == null || urlList.size()==0){
                return defaultUrlList.size();
//                  return Integer.MAX_VALUE;
            }else{
                return urlList.size();
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = View.inflate(context,R.layout.layout_roll_view_pager,null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

            if(urlList.size()!=0){
                setImageSrc(imageView,urlList.get(position)); //给当前的imageView设置图片
            }else{
                imageView.setImageResource(defaultUrlList.get(position));
            }

            ((RollViewPager)container).addView(view);

            return view;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((RollViewPager)container).removeView((View) object);
        }
    }

    /**
     * 给imageView设置图片 :
     *   使用ImageLoader
     */
    private void setImageSrc(ImageView imageView,String imageUrl){
        ImageSize imageSize;

        if(imageView.getWidth()!=0 && imageView.getHeight()!=0){
            imageSize = new ImageSize(imageView.getWidth(),imageView.getHeight());
        }else{
            imageSize = new ImageSize(GlobalUtils.getWindowWidth(),GlobalUtils.getWindowHeight());
        }

        ImageLoader.getInstance().loadImage(imageUrl, imageSize, GlobalParams.getPicOptions(),new MyImageLoaderListener(imageView));
    }

    /**
     * 给imageView设置数据的回调监听
     */
    private class MyImageLoaderListener implements ImageLoadingListener{

        private ImageView imageView;

        public MyImageLoaderListener(ImageView imageView){

            this.imageView = imageView;
        }

        @Override
        public void onLoadingComplete(String s, View view, final Bitmap bitmap) {
            new Handler().postDelayed(
                    new Runnable() {

                        @Override
                        public void run() {
                            bitmap.recycle();
                        }
                    }
                    ,1000);

            imageView.setImageBitmap(bitmap);
        }

        @Override
        public void onLoadingStarted(String s, View view) {

        }

        @Override
        public void onLoadingFailed(String s, View view, FailReason failReason) {

        }

        @Override
        public void onLoadingCancelled(String s, View view) {

        }
    }

    private int actionDownX;
    /**
     * RollViewPager的Touch事件:
     *
     *  1、由于手指按下的时候,我们要拿到事件进行判断，因此返回true
     *  2、当手指点击下去的时候,要终止handler维护的任务(不让轮播点接着走动了)
     *  3、当手指抬起的时候,接着让轮播点走动(startScroll())
     *  4、目前的情况是:当我的手指横着滑动的时候不响应Up事件
     */
    private class MyOnTouchListener implements OnTouchListener{

        private int position;

        private long actionDownTime;

        private int actionDownY;

        public MyOnTouchListener(int position){
            this.position = position;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
//                    handler.removeCallbacksAndMessages(null);
                    actionDownTime = System.currentTimeMillis();
                    actionDownX = (int)event.getX();
                    actionDownY = (int)event.getY();
//                    LogCat.i(TAG,"--onTouch--actionDownX : " + actionDownX);
                    break;
                case MotionEvent.ACTION_MOVE:
                    int moveX = (int) event.getX();

//                    LogCat.i(TAG,"--onTouch--moveX : " + moveX);
                    break;
                case MotionEvent.ACTION_UP:
//                    LogCat.i(TAG,"--onTouch---actionUpX : " + event.getX());
                    startScroll();
                    if((System.currentTimeMillis() - actionDownTime) < 500 && event.getX() == actionDownX){
                        //点击事件
                        clickListener.onClick(position);
                    }
                    break;
            }
            return true;
        }
    }

    /**
     * ViewPager的页面改变事件
     */
    private class RollViewPageChangeListener extends BasePageChangeListener{

        @Override
        public void onPageSelected(int position) {

//            position = position%defaultUrlList.size();

            for(int i = 0 ; i <dotViewList.size();i++ ){
                if(i == position){
                    dotViewList.get(i).setBackgroundResource(R.drawable.bg_dot_selected);
                }else{
                    dotViewList.get(i).setBackgroundResource(R.drawable.bg_dot_normal);
                }
            }
        }
    }

    /**
     * 移除RollViewPager的时候调用的方法
     */
    @Override
    protected void onDetachedFromWindow() {
        //清空handler中所有的消息和任务
        handler.removeCallbacksAndMessages(null);
        super.onDetachedFromWindow();
    }

    /**
     * RollViewPager的点击事件监听
     */
    public interface OnPageClickListener{

        /**
         * 触发点击事件的回调方法
         */
        void onClick(int position);
    }

    /**
     * 停止轮播调用的方法
     */
    public void stopTask() {
        LogCat.i(TAG,"----stopTask---------");

        ifStart = false;
        onDetachedFromWindow();
        runnableTask = null;
        pageChangeListener = null;
        rollViewPagerAdapter = null;
    }
}