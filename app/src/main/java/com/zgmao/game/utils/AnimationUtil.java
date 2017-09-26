package com.zgmao.game.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.zgmao.game.R;

/**
 * 项目名称：MGame
 * 类描述：动画工具类
 * 创建人：zgmao
 * 创建时间：2017/9/26
 * 修改人：zgmao
 * 修改时间：2017/9/26
 * 修改备注：
 * Created by zgmao on 2017/9/26.
 */
public class AnimationUtil
{

    /**
     * 翻转45度动画
     */
    private static Animation animUp;
    /**
     * 快速翻转45度动画
     */
    private static Animation animDown;

    public static void init(Context mContext)
    {
        // 初始化翻转动画
        animUp = AnimationUtils.loadAnimation(mContext, R.anim.cube_turn_up);
        animDown = AnimationUtils.loadAnimation(mContext, R.anim.cube_turn_down);
    }

    /**
     * 设置图片翻转动画
     *
     * @param view
     */
    public static void setImageTurn(final View view)
    {
        view.startAnimation(animUp);
        animUp.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                view.startAnimation(animDown);
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
    }

    /**
     * 设置图片上弹
     *
     * @param imageView
     */
    public static void setImageUp(View imageView)
    {

    }
}
