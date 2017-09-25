package com.zgmao.game.activity;

import android.view.MotionEvent;

import com.maf.activity.BaseActivity;

/**
 * 项目名称：MGame
 * 类描述：能够监听上下左右滑动的activity
 * 创建人：zgmao
 * 创建时间：2017/9/25
 * 修改人：zgmao
 * 修改时间：2017/9/25
 * 修改备注：
 * Created by zgmao on 2017/9/25.
 */
public abstract class TouchActivity extends BaseActivity
{
    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    private float x1 = 0;
    private float x2 = 0;
    private float y1 = 0;
    private float y2 = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //继承了Activity的onTouchEvent方法，直接监听点击事件
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //当手指按下的时候
                x1 = event.getX();
                y1 = event.getY();
//                Lg.d("x1:" + x1 + ";y1:" + y1);
                break;
            case MotionEvent.ACTION_UP:
                //当手指离开的时候
                x2 = event.getX();
                y2 = event.getY();
//                Lg.d("x2:" + x2 + ";y2:" + y2);
                //经过测试，手指的右滑、左滑的准确度不同，所以差值不同
                if ((Math.abs(y1 - y2) < 200) && (x1 - x2 > 50)) {
//                    BaseToast.makeTextShort("向左滑");
                    onMoveLeft();
                } else if ((Math.abs(y1 - y2) < 105) && (x2 - x1 > 50)) {
//                    BaseToast.makeTextShort("向右滑");
                    onMoveRight();
                } else if ((Math.abs(x1 - x2) < 240) && (y1 - y2 > 50)) {
//                    BaseToast.makeTextShort("向上滑");
                    onMoveUp();
                } else if ((Math.abs(x1 - x2) < 105) && (y2 - y1 > 50)) {
//                    BaseToast.makeTextShort("向下滑");
                    onMoveDown();
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 上滑
     */
    public abstract void onMoveUp();

    /**
     * 下滑
     */
    public abstract void onMoveDown();

    /**
     * 左滑
     */
    public abstract void onMoveLeft();

    /**
     * 右滑
     */
    public abstract void onMoveRight();
}
