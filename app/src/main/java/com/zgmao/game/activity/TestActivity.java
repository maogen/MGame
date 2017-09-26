package com.zgmao.game.activity;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.zgmao.game.R;
import com.zgmao.game.adapter.AddNumberAdapter;
import com.zgmao.game.bean.AddNumberItem;
import com.zgmao.game.utils.AnimationUtil;

import java.util.List;

/**
 * 项目名称：MGame
 * 类描述：
 * 创建人：zgmao
 * 创建时间：2017/9/26
 * 修改人：zgmao
 * 修改时间：2017/9/26
 * 修改备注：
 * Created by zgmao on 2017/9/26.
 */
public class TestActivity extends TouchActivity
{
    private Button btn_turn;
    private ImageView image_cube;

    @Override
    protected int getLayoutResId()
    {
        return R.layout.activity_test;
    }

    @Override
    protected void initView()
    {
        btn_turn = (Button) findViewById(R.id.btn_turn);
        image_cube = (ImageView) findViewById(R.id.image_cube);
    }

    @Override
    protected void initEvent()
    {
        btn_turn.setOnClickListener(this);
    }

    @Override
    protected void initValue()
    {

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.btn_turn:
                // 方块翻转
                AnimationUtil.setImageTurn(image_cube);
                break;
            default:
                break;
        }
    }

    @Override
    public void onMoveUp()
    {

    }

    @Override
    public void onMoveDown()
    {

    }

    @Override
    public void onMoveLeft()
    {

    }

    @Override
    public void onMoveRight()
    {

    }
}
