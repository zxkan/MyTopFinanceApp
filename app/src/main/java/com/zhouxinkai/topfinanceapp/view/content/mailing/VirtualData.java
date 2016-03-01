package com.zhouxinkai.topfinanceapp.view.content.mailing;

import com.zhouxinkai.topfinanceapp.R;
import com.zhouxinkai.topfinanceapp.bean.MailingDataBean;
import com.zhouxinkai.topfinanceapp.utils.LogCat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel X.K. Chow on 2016/3/1 0001
 * <p/>
 * 此类用于模拟数据
 */
public class VirtualData {


    private static final String TAG = VirtualData.class.getSimpleName();

    /**
     * 静态内部单例模式创建对象
     */
    private VirtualData() {
    }

    private static class VirtualClassInterfaceHolder {
        private static VirtualData virtualClassInterface = new VirtualData();
    }

    public static VirtualData getInstance() {
        return VirtualClassInterfaceHolder.virtualClassInterface;
    }

    /**
     * 图片id
     */
    private int[] mListIcons = new int[]{R.mipmap.g1, R.mipmap.g2, R.mipmap.g3, R.mipmap.g4,
            R.mipmap.g5};
    /**
     * 标题
     */
    private String[] mailingTitles = new String[]{"玩转组合", "雪球组合", "小秘书", "个股公告", "交易小助手"};

    /**
     * 描述
     */
    private String[] mailingDescs = new String[]{"啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊", "轻轻去去去去去去去去去去去去去去去去去去去去去",
            "嗯嗯嗯嗯嗯嗯嗯嗯嗯嗯嗯嗯嗯嗯嗯嗯嗯", "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈", "咯咯咯咯咯咯咯咯咯咯咯咯咯咯咯"
    };

    /**
     * 时间
     */
    private String[] mailingTimes = new String[]{"20:31", "21:31", "22:31", "23:31", "24:31"};

    /**
     * 未读邮件的数量
     */
    private int[] mailingCounts = {5, 6, 7, 8, 21};


  /*  private int[] mStraggeredIcons = new int[]{R.mipmap.p1, R.mipmap.p2, R.mipmap.p3, R
            .mipmap.p4, R.mipmap.p5, R.mipmap.p6, R.mipmap.p7, R.mipmap.p8, R.mipmap.p9, R
            .mipmap.p10, R.mipmap.p11, R.mipmap.p12, R.mipmap.p13, R.mipmap.p14, R.mipmap
            .p15, R.mipmap.p16, R.mipmap.p17, R.mipmap.p18, R.mipmap.p19, R.mipmap.p20, R
            .mipmap.p21, R.mipmap.p22, R.mipmap.p23, R.mipmap.p24, R.mipmap.p25, R.mipmap
            .p26, R.mipmap.p27, R.mipmap.p28, R.mipmap.p29, R.mipmap.p30, R.mipmap.p31, R
            .mipmap.p32, R.mipmap.p33, R.mipmap.p34, R.mipmap.p35, R.mipmap.p36, R.mipmap
            .p37, R.mipmap.p38, R.mipmap.p39, R.mipmap.p40, R.mipmap.p41, R.mipmap.p42, R
            .mipmap.p43, R.mipmap.p44};*/

    //=============邮箱中的模拟数据=========================================

    /**
     * 邮件部分模拟的数据集合，这部分需要暴漏出去
     */
    private List<MailingDataBean> mailList = new ArrayList<MailingDataBean>();
    ;

    /**
     * 暴漏数据的方法
     *
     * @return
     */
    public List<MailingDataBean> getMailList() {
        if (mailList != null && mailList.size() > 0) {
            return mailList;
        }
        return null;
    }

    /**
     * 初始化模拟数据接口
     */
    public void initVirtualClassInterface() {
        initMailingData();
    }

    /**
     * 初始化模拟邮件数据
     */
    private void initMailingData() {
        MailingDataBean mailingBean;
        int len = mListIcons.length;
        LogCat.i(TAG,"len : " + len);
        for (int i = 0; i < len; i++) {
            mailingBean = new MailingDataBean();
            mailingBean.iconId = mListIcons[i];
            mailingBean.mailingTitle = mailingTitles[i];
            mailingBean.mailingDesc = mailingDescs[i];
            mailingBean.mailingTime = mailingTimes[i];
            mailingBean.mailingCount = mailingCounts[i];
            mailList.add(mailingBean);
        }
    }
}
