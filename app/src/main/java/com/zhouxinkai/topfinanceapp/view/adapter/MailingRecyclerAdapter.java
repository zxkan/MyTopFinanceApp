package com.zhouxinkai.topfinanceapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhouxinkai.topfinanceapp.R;
import com.zhouxinkai.topfinanceapp.bean.MailingDataBean;
import com.zhouxinkai.topfinanceapp.utils.LogCat;

import java.util.List;

/**
 * Created by Daniel X.K. Chow on 2016/2/28 0028.
 *
 * illustration:
 *    The Holder you define must belong to the class you defined
 */
public class MailingRecyclerAdapter extends RecyclerView.Adapter<MailingRecyclerAdapter.MyHolder> {

    private static final String TAG = "MailingRecyclerAdapter";

    /**
     * 模拟的数据源
     */
    private List<MailingDataBean> mailingsList;

    private Context context;

    public MailingRecyclerAdapter(Context context,List<MailingDataBean> mailingsList){
        this.context = context;
        this.mailingsList = mailingsList;
    }


    /**
     * 每个条目的layout
     * @param viewGroup
     * @param i
     * @return
     */
    @Override
    public MyHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogCat.i(TAG, "----onCreateViewHolder----");
        View view = View.inflate(context, R.layout.layout_mailing_item, null);
        return new MyHolder(view);
    }

    /**
     * 绑定RecyclerView的数据
     * @param myHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyHolder myHolder, int position) {
        LogCat.i(TAG,"----onBindViewHolder----");
        myHolder.setDataAndRefreshUI(mailingsList.get(position));
    }

    /**
     * 数据的条目数
     * @return
     */
    @Override
    public int getItemCount() {
        LogCat.i(TAG,"----getItemCount----");
        if(mailingsList!=null){
            return mailingsList.size();
        }
        return 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        private ImageView icon_in_mailingItem; //最左边的icon

        private TextView title_mailing_item; //标题

        private TextView desc_mailing_item; //信息描述

        private TextView time_mailing_item; //时间

        private TextView count_mailing_item; //邮件数量

        public MyHolder(View itemView) {
            super(itemView);
            icon_in_mailingItem =  (ImageView)itemView.findViewById(R.id.icon_in_mailingItem);
            title_mailing_item = (TextView)itemView.findViewById(R.id.title_mailing_item);
            desc_mailing_item = (TextView)itemView.findViewById(R.id.desc_mailing_item);
            time_mailing_item = (TextView)itemView.findViewById(R.id.time_mailing_item);
            count_mailing_item = (TextView)itemView.findViewById(R.id.count_mailing_item);
        }

        /**
         * 刷新数据
         * @param mailingDataBean
         */
        public void setDataAndRefreshUI(MailingDataBean mailingDataBean) {
//            mTv.setText(mailingDataBean.text);
            icon_in_mailingItem.setImageResource(mailingDataBean.iconId);
        }
    }
}
