package com.zgmao.game.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zgmao.game.R;
import com.zgmao.game.adapter.AddNumberAdapter;
import com.zgmao.game.bean.AddNumberItem;

import java.util.ArrayList;
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
public class AddNumberActivity extends TouchActivity
{
    // 得分
    private TextView text_score;
    // 最好的得分
    private TextView text_best_score;
    // 方块
    private RecyclerView recycler_cube;
    // 最大数目
    private int maxNumber = 16;
    private List<AddNumberItem> numberList;
    private AddNumberAdapter addNumberAdapter;

    @Override
    protected int getLayoutResId()
    {
        return R.layout.activity_add_number;
    }

    @Override
    protected void initView()
    {
        text_score = (TextView) findViewById(R.id.text_score);
        text_best_score = (TextView) findViewById(R.id.text_best_score);
        recycler_cube = (RecyclerView) findViewById(R.id.recycler_cube);
        recycler_cube.setLayoutManager(new GridLayoutManager(mContext, 4));
        numberList = new ArrayList<>();
        for (int i = 0; i < maxNumber; i++) {
            AddNumberItem item = new AddNumberItem();
            numberList.add(item);
        }
        addNumberAdapter = new AddNumberAdapter(mContext, numberList);
        recycler_cube.setAdapter(addNumberAdapter);
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
