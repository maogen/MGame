package com.zgmao.game.activity;

import android.view.View;
import android.widget.Button;

import com.maf.activity.BaseActivity;
import com.maf.utils.BaseToast;
import com.zgmao.game.R;

/**
 * 项目名称：MGame
 * 类描述：首页
 * 创建人：zgmao
 * 创建时间：2017/9/19
 * 修改人：zgmao
 * 修改时间：2017/9/19
 * 修改备注：
 * Created by zgmao on 2017/9/19.
 */
public class MainActivity extends TouchActivity
{

    @Override
    protected int getLayoutResId()
    {
        return R.layout.activity_main;
    }

    @Override
    protected void initView()
    {
    }

    @Override
    protected void initEvent()
    {
    }

    @Override
    protected void initValue()
    {

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            default:
                break;
        }
    }

    @Override
    public void onMoveUp()
    {
        BaseToast.makeTextShort("向上滑");
    }

    @Override
    public void onMoveDown()
    {
        BaseToast.makeTextShort("向下滑");
    }

    @Override
    public void onMoveLeft()
    {
        BaseToast.makeTextShort("向左滑");
    }

    @Override
    public void onMoveRight()
    {
        BaseToast.makeTextShort("向右滑");
    }
}
