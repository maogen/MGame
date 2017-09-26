package com.zgmao.game.adapter;

import android.view.View;
import android.widget.TextView;

import com.maf.adapter.BaseRecycleViewHolder;
import com.maf.views.SquareTextView;
import com.zgmao.game.R;

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
public class AddNumberViewHolder extends BaseRecycleViewHolder
{
    SquareTextView textContent;

    public AddNumberViewHolder(View itemView)
    {
        super(itemView);
    }

    @Override
    protected void initView()
    {
        textContent = (SquareTextView) itemView.findViewById(R.id.text_item_content);
    }
}
