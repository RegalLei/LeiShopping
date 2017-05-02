package com.gl.leishopping.Cart.fragment.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 作者：高镭
 * 时间：2017/5/2 13:59
 * 功能：事件就是SP缓存工具类,保存,读取数据在本地上
 */
public class CacheUtils {
    //得到保存的String类型数据
    public static String getString (Context context,String key){
        SharedPreferences gl = context.getSharedPreferences("gl", Context.MODE_PRIVATE);
        return gl.getString(key,"");
    }
    //保存String类型数据,参数:1上下文 2.保存键 3.保存String类型数据
    public static void saveString (Context context,String key, String value){
        SharedPreferences gl = context.getSharedPreferences("gl", Context.MODE_PRIVATE);
        gl.edit().putString(key,value).commit();
    }
}
