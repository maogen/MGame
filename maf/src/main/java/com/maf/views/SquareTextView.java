package com.maf.views;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * 项目名称：RentalRoom
 * 类描述：正方形TextView
 * 创建人：zgmao
 * 创建时间：2017/6/20
 * 修改人：zgmao
 * 修改时间：2017/6/20
 * 修改备注：
 * Created by zgmao on 2017/6/20.
 */

public class SquareTextView extends AppCompatTextView
{
    public SquareTextView(Context context)
    {
        super(context);
    }

    public SquareTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public SquareTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));

        int childWidthSize = getMeasuredWidth();
        //高度和宽度一样
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
