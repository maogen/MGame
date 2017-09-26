package com.zgmao.game.bean;

/**
 * 项目名称：MGame
 * 类描述：数字枚举
 * 创建人：zgmao
 * 创建时间：2017/9/26
 * 修改人：zgmao
 * 修改时间：2017/9/26
 * 修改备注：
 * Created by zgmao on 2017/9/26.
 */
public enum NumberEnum
{
    ONE(2, "2"), TWO(4, "4"), THREE(8, "8"), FOUR(16, "16"),
    FIVE(32, "32"), SIX(64, "64"), SEVEN(128, "128"), EIGHT(256, "256"),
    NINE(512, "512"), TEN(1024, "1024"), ELEVEN(2048, "2048"), TWELVE(4096, "4096"),
    THIRTEEN(8192, "8192"), FOURTEEN(16384, "16384"), FIFTEEN(32768, "32768"), SIXTEEN(65536, "65536"),
    MAX(131072, "131072");
    private long number;
    private String value;

    NumberEnum(long number, String value)
    {
        this.number = number;
        this.value = value;
    }

    public long getNumber()
    {
        return number;
    }

    public String getValue()
    {
        return value;
    }
}
