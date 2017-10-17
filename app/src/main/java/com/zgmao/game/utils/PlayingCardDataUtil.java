package com.zgmao.game.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.reflect.TypeToken;
import com.maf.git.GsonUtils;
import com.zgmao.game.bean.PlayingCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 项目名称：MGame
 * 类描述：扑克牌数据处理
 * 创建人：zgmao
 * 创建时间：2017/10/17
 * 修改人：zgmao
 * 修改时间：2017/10/17
 * 修改备注：
 * Created by zgmao on 2017/10/17.
 */
public class PlayingCardDataUtil
{
    /**
     * 得到解析数据
     *
     * @param context
     * @return
     */
    public static List<PlayingCard> getCardJson(Context context)
    {
        AssetManager assetManager = context.getAssets();
        StringBuffer sb = new StringBuffer();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = assetManager.open("playingcards.json");
            reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String temp;
            while ((temp = reader.readLine()) != null) {
                sb.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        List<PlayingCard> playingCardList = GsonUtils.stringToGson(sb.toString(), new TypeToken<List<PlayingCard>>()
        {
        });
        return playingCardList;
    }
}
