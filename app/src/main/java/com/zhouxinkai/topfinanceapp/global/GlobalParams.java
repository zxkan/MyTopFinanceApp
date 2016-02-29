package com.zhouxinkai.topfinanceapp.global;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.zhouxinkai.topfinanceapp.MainActivity;

/**
 * Created by Daniel X.K. Chow on 2016/1/11 0011.
 */
public class GlobalParams {

    /**
     * 全局的上下文对象
     */
    public static Context globalContext;

    /**
     * 程序入口Activity管理的上下文对象
     */
    public static MainActivity mainActivityContext;


    /**
     * 初始化全局的上下文对象
     * @param context : 整个应用全局的上下文对象
     */
    public static void init(Context context) {

        globalContext = context;
    }

    /**
     * 初始化入口Activity的上下文对象
     * @param mainActivity  程序入口Activity的上下文对象
     */
    public static void initActivityContext(MainActivity mainActivity) {
        mainActivityContext = mainActivity;
    }

    /**
     * 获取全局的上下文对象
     */
    public static Context getApplicationContext() {

        return globalContext;
    }

    /**
     * 获取程序入口的
     */
    public static MainActivity getMainActivityContext(){

        return mainActivityContext;
    }


    //-------------------------ImageLoader配置---------------------------------------------

    /**
     * 初始化Universe ImageLoader
     *
     * @param globalContext 全局的上下文对象
     */
    public static void initImageLoader(Context globalContext) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(globalContext)
                //缓存图片的最大尺寸
                .memoryCacheExtraOptions(480, 320)
                        //线程池内加载的数量
                .threadPoolSize(1)
                        //降低线程的优先级保证UI线程不受太大的影响
                .threadPriority(Thread.NORM_PRIORITY - 2)
                        //强制内存中不能存储内容相同但大小不同的图片
                .denyCacheImageMultipleSizesInMemory()
                        //建议内存设在5-10M，可以有比较好的表现
                .memoryCache(new LruMemoryCache(10 * 1024 * 1024))
                        //磁盘缓存的最大占用空间
                .diskCacheSize(50 * 1024 * 1024)
                        //设置图片下载和显示的工作队列排序
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                        //设置超时时间
                .imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 3 * 1000))
                        //生成
                .build();
        ImageLoader.getInstance().init(config);
    }

    //-----------------------图片的配置------------------------------------------------
    private static DisplayImageOptions myImageOptions = null;

    public final static DisplayImageOptions getPicOptions() {
        if (myImageOptions == null) {
            myImageOptions = new DisplayImageOptions.Builder()
                    //内存缓存
                    .cacheInMemory(true)
                    //磁盘缓存
                    .cacheOnDisk(true)
                    //设置为RGB565比默认的ARGB_8888要节省大量的内存
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    //载入图片的时候稍作延时可以提高整体滑动的流畅度
                    .delayBeforeLoading(10)
                    //生成
                    .build();
        }
        return myImageOptions;
    }

    //----------------------------------------------------------------------
}
