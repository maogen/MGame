package com.zgmao.game.adapter;

import android.content.Context;
import android.view.View;

import com.maf.adapter.BaseRecycleAdapter;
import com.maf.utils.DensityUtils;
import com.maf.utils.SPUtils;
import com.zgmao.game.R;
import com.zgmao.game.bean.AddNumberItem;
import com.zgmao.game.bean.NumberEnum;

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
public class AddNumberAdapter extends BaseRecycleAdapter<AddNumberItem, AddNumberViewHolder>
{
    public AddNumberAdapter(Context context, List<AddNumberItem> list)
    {
        super(context, list);
    }

    @Override
    protected int getResourceId()
    {
        return R.layout.item_add_number_cube;
    }

    @Override
    protected AddNumberViewHolder getViewHolder(View view)
    {
        return new AddNumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AddNumberViewHolder holder, int position)
    {
        AddNumberItem item = list.get(position);
        NumberEnum numberEnum = item.getNumberEnum();
        if (numberEnum != null) {
            // 没有数据，显示默认
            String value = numberEnum.getValue();
            holder.textContent.setText(value);
            if (value.length() <= 5) {
                holder.textContent.setTextSize(24);
            } else {
                holder.textContent.setTextSize(18);
            }
            switch (numberEnum) {
                // 设置背景颜色
                case ONE:
                    holder.textContent.setBackgroundResource(R.drawable.shape_yellow_one);
                    break;
                case TWO:
                    holder.textContent.setBackgroundResource(R.drawable.shape_yellow_two);
                    break;
                case THREE:
                    holder.textContent.setBackgroundResource(R.drawable.shape_yellow_three);
                    break;
                case FOUR:
                    holder.textContent.setBackgroundResource(R.drawable.shape_yellow_four);
                    break;
                case FIVE:
                    holder.textContent.setBackgroundResource(R.drawable.shape_yellow_five);
                    break;
                case SIX:
                    holder.textContent.setBackgroundResource(R.drawable.shape_red_one);
                    break;
                case SEVEN:
                    holder.textContent.setBackgroundResource(R.drawable.shape_red_two);
                    break;
                case EIGHT:
                    holder.textContent.setBackgroundResource(R.drawable.shape_red_three);
                    break;
                case NINE:
                    holder.textContent.setBackgroundResource(R.drawable.shape_red_four);
                    break;
                case TEN:
                    holder.textContent.setBackgroundResource(R.drawable.shape_red_five);
                    break;
                default:
                    holder.textContent.setBackgroundResource(R.drawable.shape_yellow);
                    break;
            }
        }
    }
}
