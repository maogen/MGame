package com.zgmao.game.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.maf.utils.Lg;
import com.zgmao.game.R;
import com.zgmao.game.adapter.AddNumberAdapter;
import com.zgmao.game.bean.AddNumberItem;
import com.zgmao.game.bean.NumberEnum;

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
    // 行数
    private int rows = 4;
    //列数
    private int column = 4;
    // 数字列表
    private List<AddNumberItem> numberList;
    // 数字列表设配器
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
        // 初始出现两次数字
        randomNumber(true);
        randomNumber(true);
        addNumberAdapter.notifyDataSetChanged();
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

    /**
     * 随机出现数字
     * 1：如果是第一次，出现两个数字2；如果不是第一次，随机出现2或者4
     * 2：出现2的几率是80%
     * 3：
     *
     * @param isFirst 是否是第一次，如果是，产生两个
     * @return 是否成功生成，如果没有，表示游戏结束
     */
    private boolean randomNumber(boolean isFirst)
    {
        NumberEnum randomNumber;
        if (isFirst) {
            // 第一次，出现的是初始值
            randomNumber = NumberEnum.ONE;
        } else {
            if (isRate(0.9)) {
                randomNumber = NumberEnum.ONE;
            } else {
                randomNumber = NumberEnum.TWO;
            }
        }
        // 出现在哪个索引位置
        int row = 0;
        if (!isFirst) {
            // 不是第一次，才随机，否则始终是第0行
            row = randomRow();
        }

        Lg.d("出现在第" + row + "行");
        if (row == -1) {
            return false;
        }
        int column = randomColumn();
        Lg.d("出现在第" + column + "列");
        if (column == -1) {
            return false;
        }
        int index = row * rows + column;
        Lg.d("下次出现的位置是:" + index);
        AddNumberItem addNumberItem = numberList.get(index);
        addNumberItem.setNumberEnum(randomNumber);
        return true;
    }

    /**
     * 出现数字的概率，有null位的行越多，出现数字的概率越大
     *
     * @return
     */
    private int randomRow()
    {
        // 是否已满，如果发现空位置，设为false
        boolean isFull = true;
        // 第一次发现null单位的行，如果根据概率依然没有得到null行，则返回第一次发现null单位的行
        int firstNullRow = 0;
        int[][] lines = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}};
        for (int i = 0; i < lines.length; i++) {
            int[] items = lines[i];
            int nullNumber = 0;// 每行有多少个null
            for (int j = 0; j < items.length; j++) {
                // 每行的每个
                int itemNumber = items[j];
                if (numberList.get(itemNumber).getNumberEnum() == null) {
                    nullNumber++;
                    if (isFull) {
                        // 第一次发现null行
                        firstNullRow = i;
                        isFull = false;
                    }
                }
            }
            // 出现数字的概率，有null位的行越多，出现数字的概率越大
            float showNumberRate = (float) nullNumber / items.length;
            Lg.d("第" + i + "行有" + nullNumber + "个空白区域，" + "概率是" + showNumberRate);
            if (isRate(showNumberRate)) {
                return i;
            }
        }
        if (isFull) {
            // 没有发现null行，直接返回-1
            return -1;
        }
        // 发现null行，但是凭借概率没有得到行，默认返回第一次发现的null行
        return firstNullRow;
    }

    /**
     * 所有null列，每列出现的概率相同
     *
     * @return
     */
    private int randomColumn()
    {
        // 第一次发现null单位的列，如果根据概率依然没有得到null列，则返回第一次发现null单位的列
        int[][] lines = {{0, 4, 8, 12}, {1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}};
        List<Integer> nullColumnList = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            int[] items = lines[i];
            for (int j = 0; j < items.length; j++) {
                // 每列的每个
                int itemNumber = items[j];
                if (numberList.get(itemNumber).getNumberEnum() == null) {
                    nullColumnList.add(i);
                }
            }
        }
        int size = nullColumnList.size();
        if (size == 0) {
            return -1;
        }
        double random = Math.random() * size;
        return nullColumnList.get((int) random);
    }

    /**
     * 按照比例，随机产生是/否
     *
     * @param rate 比例0.0-1.0
     * @return
     */
    private boolean isRate(double rate)
    {
        double random = Math.random();
        if (random < rate) {
            return true;
        }
        return false;
    }
}
