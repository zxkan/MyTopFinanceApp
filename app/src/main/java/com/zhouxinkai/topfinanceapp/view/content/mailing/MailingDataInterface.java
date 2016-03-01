package com.zhouxinkai.topfinanceapp.view.content.mailing;

import com.zhouxinkai.topfinanceapp.bean.MailingDataBean;

import java.util.List;

/**
 * Created by Daniel X.K. Chow on 2016/3/1 0001.
 */
public class MailingDataInterface {

    private static final String TAG = MailingDataInterface.class.getSimpleName();

    /**
     * 注意:
     *    已经在GlobalApplication中进行了
     *    VirtualData.getInstance().initVirtualClassInterface()操作
     *    这里我们直接获取数据即可
     */
    public static void getMailData(final getVirtualDataCallback callback){

        new Thread(){

            @Override
            public void run() {
                List<MailingDataBean> mailList = VirtualData.getInstance().getMailList();
                callback.callback(mailList);
            }
        }.start();
    }


    public interface getVirtualDataCallback{

        public void callback(List<MailingDataBean> mailList);
    }

}
