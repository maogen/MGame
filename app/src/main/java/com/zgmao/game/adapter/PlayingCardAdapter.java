package com.zgmao.game.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.maf.adapter.BaseRecycleAdapter;
import com.maf.git.GlideUtils;
import com.maf.utils.ImageUtils;
import com.zgmao.game.R;
import com.zgmao.game.bean.PlayingCard;

import java.util.List;

/**
 * 项目名称：MGame
 * 类描述：显示扑克牌
 * 创建人：zgmao
 * 创建时间：2017/10/19
 * 修改人：zgmao
 * 修改时间：2017/10/19
 * 修改备注：
 * Created by zgmao on 2017/10/19.
 */
public class PlayingCardAdapter extends BaseRecycleAdapter<PlayingCard, PlayingCardViewHolder>
{
    /**
     * 图片
     */
    private Bitmap cardBitmap;

    public PlayingCardAdapter(Context context, List<PlayingCard> list, boolean hasBackground)
    {
        super(context, list, hasBackground);
        // 初始化牌的图片
        cardBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.playingcards);
    }

    @Override
    protected int getResourceId()
    {
        return R.layout.item_playing_card;
    }

    @Override
    protected PlayingCardViewHolder getViewHolder(View view)
    {
        return new PlayingCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayingCardViewHolder holder, int position)
    {
        PlayingCard item = list.get(position);
        Bitmap selectBitmap = Bitmap.createBitmap(cardBitmap,
                item.getX(), item.getY(),
                item.getWidth(), item.getHeight());
        holder.imageContent.setImageBitmap(selectBitmap);
    }
}
