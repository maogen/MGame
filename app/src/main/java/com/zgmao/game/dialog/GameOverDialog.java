package com.zgmao.game.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.maf.popupwindow.BasePopup;
import com.maf.utils.DensityUtils;
import com.zgmao.game.R;

/**
 * 项目名称：MGame
 * 类描述：
 * 创建人：zgmao
 * 创建时间：2017/10/13
 * 修改人：zgmao
 * 修改时间：2017/10/13
 * 修改备注：
 * Created by zgmao on 2017/10/13.
 */
public class GameOverDialog extends BasePopup
{
    private TextView textOk;

    public GameOverDialog(Context context)
    {
        super(context);
        setWidth(DensityUtils.dp2px(context, 300));
        setFocusable(false);
        setOutsideTouchable(false);
    }

    @Override
    protected int getViewId()
    {
        return R.layout.view_game_over;
    }

    @Override
    protected void initView()
    {
        textOk = (TextView) view.findViewById(R.id.text_ok);
    }

    @Override
    protected void initValue()
    {

    }

    @Override
    protected void initEvent()
    {
        textOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });
    }

    /**
     * 显示对话框
     *
     * @param v
     */
    public void showDialog(View v)
    {
        showAtLocation(v, Gravity.CENTER, 0, 0);
        backgroundAlpha(0.5f);
    }

    /**
     * @param onClickListener
     */
    public void setTextOkListener(View.OnClickListener onClickListener)
    {
        textOk.setOnClickListener(onClickListener);
    }
}
