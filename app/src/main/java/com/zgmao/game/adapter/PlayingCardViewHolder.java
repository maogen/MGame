package com.zgmao.game.adapter;

import android.view.View;
import android.widget.ImageView;

import com.maf.adapter.BaseRecycleViewHolder;
import com.zgmao.game.R;

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
public class PlayingCardViewHolder extends BaseRecycleViewHolder
{
    ImageView imageContent;

    public PlayingCardViewHolder(View itemView)
    {
        super(itemView);
    }

    @Override
    protected void initView()
    {
        imageContent = (ImageView) itemView.findViewById(R.id.image_content);
    }
}
