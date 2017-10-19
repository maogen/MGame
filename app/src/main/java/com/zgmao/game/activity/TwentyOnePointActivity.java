package com.zgmao.game.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.maf.activity.BaseActivity;
import com.zgmao.game.R;
import com.zgmao.game.adapter.PlayingCardAdapter;
import com.zgmao.game.bean.PlayingCard;
import com.zgmao.game.utils.PlayingCardDataUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：MGame
 * 类描述：
 * 创建人：zgmao
 * 创建时间：2017/10/17
 * 修改人：zgmao
 * 修改时间：2017/10/17
 * 修改备注：
 * Created by zgmao on 2017/10/17.
 */
public class TwentyOnePointActivity extends BaseActivity
{
    // 规则
    private ImageView imageRules;
    // 规则对话框

    // 牌列表
    private List<PlayingCard> playingCardList;

    // 庄家
    private RecyclerView recyclerOne;
    private PlayingCardAdapter adapterOne;
    private List<PlayingCard> cardListOne;
    // 贤家
    private RecyclerView recyclerTwo;
    private PlayingCardAdapter adapterTwo;
    private List<PlayingCard> cardListTwo;

    @Override
    protected int getLayoutResId()
    {
        return R.layout.activity_twenty_one;
    }

    @Override
    protected void initView()
    {
        imageRules = (ImageView) findViewById(R.id.image_rules);
        // 庄家
        recyclerOne = (RecyclerView) findViewById(R.id.recycler_player_one);
        cardListOne = new ArrayList<>();
        adapterOne = new PlayingCardAdapter(mContext, cardListOne, false);
        recyclerOne.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerOne.setAdapter(adapterOne);
        // 贤家
        recyclerTwo = (RecyclerView) findViewById(R.id.recycler_player_two);
        cardListTwo = new ArrayList<>();
        adapterTwo = new PlayingCardAdapter(mContext, cardListTwo, false);
        recyclerTwo.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerTwo.setAdapter(adapterTwo);
    }

    @Override
    protected void initEvent()
    {
        imageRules.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

    }

    @Override
    protected void initValue()
    {
        // 初始化牌的解析数据
        playingCardList = PlayingCardDataUtil.getCardJson(this);
    }

    @Override
    public void onClick(View v)
    {

    }
}
