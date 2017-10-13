package com.zgmao.game.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.maf.git.GsonUtils;
import com.maf.utils.Lg;
import com.maf.utils.SPUtils;
import com.zgmao.game.R;
import com.zgmao.game.adapter.AddNumberAdapter;
import com.zgmao.game.bean.AddNumberItem;
import com.zgmao.game.bean.NumberEnum;
import com.zgmao.game.dialog.GameOverDialog;

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
    // 索引矩阵
    private int[][] lines = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}};
    // 数字列表
    private List<AddNumberItem> numberList;
    // 数字列表设配器
    private AddNumberAdapter addNumberAdapter;

    // 当前分数
    private long currentScore;
    // 历史最好分数
    private long bestScore;
    // 保存历史最好分数key
    private final String BEST_SCORE_KEY = "BEST_SCORE";
    // 保存上次应用退出时的界面
    private final String LAST_GAME_KEY = "LAST_GAME_KEY";

    // 游戏结束对话框
    private GameOverDialog gameOverDialog;

    @Override
    protected int getLayoutResId()
    {
        return R.layout.activity_add_number;
    }

    @Override
    protected void initView()
    {
        // 当前分数
        text_score = (TextView) findViewById(R.id.text_score);
        // 获取历史最好分数
        bestScore = (Long) SPUtils.get(mContext, BEST_SCORE_KEY, new Long(0));
        text_best_score = (TextView) findViewById(R.id.text_best_score);
        text_best_score.setText(String.valueOf(bestScore));

        recycler_cube = (RecyclerView) findViewById(R.id.recycler_cube);
        recycler_cube.setLayoutManager(new GridLayoutManager(mContext, 4));
        numberList = new ArrayList<>();
        for (int i = 0; i < maxNumber; i++) {
            AddNumberItem item = new AddNumberItem();
            numberList.add(item);
        }
        addNumberAdapter = new AddNumberAdapter(mContext, numberList);
        recycler_cube.setAdapter(addNumberAdapter);

        gameOverDialog = new GameOverDialog(mContext);
    }

    @Override
    protected void initEvent()
    {
        recycler_cube.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                AddNumberActivity.super.onTouchEvent(event);
                return false;
            }
        });
        gameOverDialog.setTextOkListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                gameOverDialog.dismiss();
                for (int i = 0; i < numberList.size(); i++) {
                    numberList.get(i).setNumberEnum(null);
                }
                addNumberAdapter.notifyDataSetChanged();
                // 初始出现两次数字
                int indexOne = randomNumber(true);
                addNumberAdapter.notifyItemInserted(indexOne);
                int indexTwo = randomNumber(true);
                addNumberAdapter.notifyItemInserted(indexTwo);
            }
        });
    }

    @Override
    protected void initValue()
    {
        restartGame();
    }

    /**
     * 恢复上次退出的游戏
     */
    private void restartGame()
    {
        String lastGameStr = (String) SPUtils.get(mContext, LAST_GAME_KEY, "");
        if (!TextUtils.isEmpty(lastGameStr)) {
            List<AddNumberItem> tempNumberList = GsonUtils.stringToGson(lastGameStr, new TypeToken<List<AddNumberItem>>()
            {
            });
            if (tempNumberList != null && tempNumberList.size() == numberList.size()) {
                // 回复
                for (int i = 0; i < numberList.size(); i++) {
                    numberList.get(i).setNumberEnum(tempNumberList.get(i).getNumberEnum());
                }
                addNumberAdapter.notifyDataSetChanged();
                return;
            }
        }
        // 初始出现两次数字
        int indexOne = randomNumber(true);
        addNumberAdapter.notifyItemInserted(indexOne);
        int indexTwo = randomNumber(true);
        addNumberAdapter.notifyItemInserted(indexTwo);
    }

    @Override
    public void onClick(View v)
    {

    }

    @Override
    public void onMoveUp()
    {
        showNumber(0);
    }

    @Override
    public void onMoveDown()
    {
        showNumber(1);
    }

    @Override
    public void onMoveLeft()
    {
        showNumber(2);
    }

    @Override
    public void onMoveRight()
    {
        showNumber(3);
    }

    /**
     * 加分
     *
     * @param numberEnum
     */
    private void addScore(NumberEnum numberEnum)
    {
        long addScore = numberEnum.getNumber();
        currentScore = currentScore + addScore;
        text_score.setText(String.valueOf(currentScore));
        if (currentScore > bestScore) {
            // 当前分数大于最好分数
            bestScore = currentScore;
            text_best_score.setText(String.valueOf(bestScore));
            SPUtils.put(mContext, BEST_SCORE_KEY, bestScore);
        }

    }


    /**
     * 合并，并且显示下次数字
     */
    private void showNumber(int direction)
    {
        addNumber(direction);
        addNumberAdapter.notifyDataSetChanged();
        int successIndex = randomNumber(false);
        if (successIndex != -1) {
//            Lg.d("出现数字");
            addNumberAdapter.notifyItemInserted(successIndex);
        } else if (isGameOver()) {
//            Lg.d("游戏结束");
//            BaseToast.makeTextShort("游戏结束");
            gameOverDialog.showDialog(getCurrentFocus());
        }
    }

    /**
     * 判断两个数字相加时，如果遇到第二个索引为null，则第二个索引往后移动一位，或者在移动空格的时候，遇到空格一样要后移动一位
     */
    private int spaceIndex;

    /**
     * 合并数字
     *
     * @param direction 方向 0-上；1-下；2-左；3-右
     */
    private void addNumber(int direction)
    {
        int[][] lineDirection = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}};
        if (direction == 0) {
            // 上
            lineDirection = new int[][]{{12, 8, 4, 0}, {13, 9, 5, 1}, {14, 10, 6, 2}, {15, 11, 7, 3}};
        } else if (direction == 1) {
            // 下
            lineDirection = new int[][]{{0, 4, 8, 12}, {1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}};
        } else if (direction == 2) {
            // 左
            lineDirection = new int[][]{{3, 2, 1, 0}, {7, 6, 5, 4}, {11, 10, 9, 8}, {15, 14, 13, 12}};
        } else if (direction == 3) {
            // 右
            lineDirection = new int[][]{{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}};
        }
        // 执行数字相加判断逻辑
        for (int i = 0; i < lineDirection.length; i++) {
            int[] itemLine = lineDirection[i];
            // 循环执行，从数组后面，往前面相加
            spaceIndex = 1;
            for (int number = itemLine.length - 1; number > 0; ) {
                if (number - spaceIndex < 0) {
                    break;
                }
                int indexOne = itemLine[number];
                int indexTwo = itemLine[number - spaceIndex];
                AddNumberItem addNumberItemOne = numberList.get(indexOne);// 第一个item
                AddNumberItem addNumberItemTwo = numberList.get(indexTwo);// 第二个
                if (null == addNumberItemOne.getNumberEnum()) {
                    // 如果第一个为null，下一个
                    number--;
                    spaceIndex = 1;
                } else if (null == addNumberItemTwo.getNumberEnum()) {
                    // 如果第二个为null
                    spaceIndex++;
                } else if (addNumberItemOne.getNumberEnum() == addNumberItemTwo.getNumberEnum()) {
                    // 第一个第二个相等，相加
                    NumberEnum numberOne = addNumberItemOne.getNumberEnum();
                    NumberEnum numberResult = null;
                    NumberEnum[] numberEnumArray = NumberEnum.values();
                    // 循环查找符合条件的，然后取出后一个
                    for (int k = 0; k < numberEnumArray.length - 1; k++) {
                        NumberEnum kEnum = numberEnumArray[k];
                        if (numberOne == kEnum) {
                            // 前后两个数一样，得到相加之后的那个数
                            numberResult = numberEnumArray[k + 1];
                            addScore(numberResult);
                            break;
                        }
                    }
                    if (null != numberResult) {
                        addNumberItemOne.setNumberEnum(numberResult);
                        addNumberItemTwo.setNumberEnum(null);
                    }
                    // 两个数相加之后，直接跳过这两个，执行上上一个
                    number = number - 2;
                    spaceIndex = 1;
                } else {
                    number--;
                    spaceIndex = 1;
                }
            }
            // 上个循环是执行相加逻辑，相加之后，再执行空格处理
            spaceIndex = 1;
            for (int j = itemLine.length - 1; j > 0; ) {
                if (j - spaceIndex < 0) {
                    break;
                }
                int indexOne = itemLine[j];
                int indexTwo = itemLine[j - spaceIndex];
                AddNumberItem addNumberItemOne = numberList.get(indexOne);// 第一个item
                AddNumberItem addNumberItemTwo = numberList.get(indexTwo);// 第二个
                if (addNumberItemOne.getNumberEnum() != null) {
                    j--;
                    spaceIndex = 1;
                } else if (addNumberItemOne.getNumberEnum() == null && addNumberItemTwo.getNumberEnum() == null) {
                    spaceIndex++;
                } else if (addNumberItemOne.getNumberEnum() == null && addNumberItemTwo.getNumberEnum() != null) {
                    addNumberItemOne.setNumberEnum(addNumberItemTwo.getNumberEnum());
                    addNumberItemTwo.setNumberEnum(null);
                    j--;
                    spaceIndex = 1;
                }
            }
        }
    }

    /**
     * 随机出现数字
     * 1：如果是第一次，出现两个数字2；如果不是第一次，随机出现2或者4
     * 2：出现2的几率是80%
     * 3：
     *
     * @param isFirst 是否是第一次，如果是，产生两个
     * @return 生成的位置索引，如果=-1，表示没有成功生成，游戏结束
     */
    private int randomNumber(boolean isFirst)
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
        int row = randomRow();
//        if (!isFirst) {
//            // 不是第一次，才随机，否则始终是第0行
//            row = randomRow();
//        }

//        Lg.d("出现在第" + row + "行");
        if (row == -1) {
            return -1;
        }
        int index = randomColumn(row);
        Lg.d("出现在第" + index + "的位置");
        if (index == -1) {
            return -1;
        }
        AddNumberItem addNumberItem = numberList.get(index);
        addNumberItem.setNumberEnum(randomNumber);
        return index;
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
//            Lg.d("第" + i + "行有" + nullNumber + "个空白区域，" + "概率是" + showNumberRate);
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
     * @param row 在确定某行的条件下，再次确定列，从而确定出现位置的索引
     * @return 下次出现数字的索引
     */
    private int randomColumn(int row)
    {
        // 第一次发现null单位的列，如果根据概率依然没有得到null列，则返回第一次发现null单位的列
//        int[][] lines = {{0, 4, 8, 12}, {1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}};
        List<Integer> nullColumnList = new ArrayList<>();
        int[] items = lines[row];
        for (int j = 0; j < items.length; j++) {
            // 每列的每个
            int itemNumber = items[j];
            if (numberList.get(itemNumber).getNumberEnum() == null) {
                nullColumnList.add(itemNumber);
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
     * 游戏是否结束
     *
     * @return
     */
    private boolean isGameOver()
    {
        int[][] lineDirection = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}};
        for (int i = 0; i < lineDirection.length; i++) {
            int[] itemLines = lineDirection[i];
            for (int j = 0; j < itemLines.length; j++) {
                int index = itemLines[j];
                if (numberList.get(index).getNumberEnum() == null) {
                    // 有空位，继续游戏
                    return false;
                }
                if (j < itemLines.length - 1) {
                    int indexTwo = j + 1;
                    if (numberList.get(index).getNumberEnum() == numberList.get(indexTwo).getNumberEnum()) {
                        // 相邻的两个相同
                        return false;
                    }
                }
            }
        }
        lineDirection = new int[][]{{0, 4, 8, 12}, {1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}};
        for (int i = 0; i < lineDirection.length; i++) {
            int[] itemLines = lineDirection[i];
            for (int j = 0; j < itemLines.length; j++) {
                int index = itemLines[j];
                if (numberList.get(index).getNumberEnum() == null) {
                    // 有空位，继续游戏
                    return false;
                }
                if (j < itemLines.length - 1) {
                    int indexTwo = j + 1;
                    if (numberList.get(index).getNumberEnum() == numberList.get(indexTwo).getNumberEnum()) {
                        // 相邻的两个相同
                        return false;
                    }
                }
            }
        }
        return true;
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

    @Override
    protected void onDestroy()
    {
        // 销毁界面之前，保存上次记录
        String lastGameStr = GsonUtils.gsonToString(numberList);
        SPUtils.put(mContext, LAST_GAME_KEY, lastGameStr);
        super.onDestroy();
    }
}
