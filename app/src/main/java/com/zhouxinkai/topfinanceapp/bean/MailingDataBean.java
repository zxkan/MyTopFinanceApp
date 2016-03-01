package com.zhouxinkai.topfinanceapp.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/2/28 0028.
 */
public class MailingDataBean implements Serializable{

    private static final long serialVersionUID = 47884423649028491L;

    /**
     * 图片ID
     */
    public int iconId;

    /**
     * 邮件标题
     */
    public String mailingTitle;

    /**
     * 内容描述
     */
    public String mailingDesc;

    /**
     * 邮件到达的时间
     */
    public String mailingTime;

    /**
     * 当前类型邮件的数量
     */
    public String mailingCount;
}
