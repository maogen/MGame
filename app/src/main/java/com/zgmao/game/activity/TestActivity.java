package com.zgmao.game.activity;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.maf.utils.Lg;
import com.zgmao.game.R;
import com.zgmao.game.adapter.AddNumberAdapter;
import com.zgmao.game.bean.AddNumberItem;
import com.zgmao.game.bean.PlayingCard;
import com.zgmao.game.utils.AnimationUtil;
import com.zgmao.game.utils.PlayingCardDataUtil;

import java.io.IOException;
import java.io.InputStream;
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
public class TestActivity extends TouchActivity
{
    // 测试翻转图片
    private Button btn_turn;
    private ImageView image_cube;
    // 测试获取图片
    private Spinner spinner;
    private ArrayAdapter<String> spinnerAdapter;
    private List<PlayingCard> playingCardList;
    private ImageView imageCard;
    private Bitmap cardBitmap;

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

        spinner = (Spinner) findViewById(R.id.spinner);
        imageCard = (ImageView) findViewById(R.id.image_card);
    }

    @Override
    protected void initEvent()
    {
        btn_turn.setOnClickListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                PlayingCard playingCard = playingCardList.get(position);
                Bitmap selectBitmap = Bitmap.createBitmap(cardBitmap,
                        (int) playingCard.getX(), (int) playingCard.getY(),
                        (int) playingCard.getWidth(), (int) playingCard.getHeight());
                imageCard.setImageBitmap(selectBitmap);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    @Override
    protected void initValue()
    {
        // 初始化牌的解析数据
        playingCardList = PlayingCardDataUtil.getCardJson(this);

        List<String> stringList = new ArrayList<>();
        if (null != playingCardList) {
            for (PlayingCard item : playingCardList) {
                stringList.add(item.getName());
            }
        } else {
            playingCardList = new ArrayList<>();
        }
        String[] mItems = stringList.toArray(new String[]{});
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mItems);
        spinner.setAdapter(spinnerAdapter);
        // 初始化牌的图片
        cardBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.playingcards);
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
