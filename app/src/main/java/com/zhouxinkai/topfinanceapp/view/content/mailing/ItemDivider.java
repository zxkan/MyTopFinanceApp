package com.zhouxinkai.topfinanceapp.view.content.mailing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Daniel X.K. Chow on 2016/3/1 0001.
 */
public class ItemDivider extends RecyclerView.ItemDecoration{

    private Drawable mDrawable;

    public ItemDivider(Context context,int resId){
        mDrawable = context.getResources().getDrawable(resId);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right= parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for(int i = 0 ; i < childCount ; i++){
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();

            //以下用来确定绘制的位置
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top +mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left,top,right,bottom);
            mDrawable.draw(c);
        }
    }


    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        outRect.set(0,0,0,mDrawable.getIntrinsicWidth());
    }
}
