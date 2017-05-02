package com.gl.leishopping.LeiShoppingActivity;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 作者：高镭
 * 时间：2017/4/25 18:28
 * 功能：自定义的Application(记得在清单文件进行配置),对使用的第三方开源框架进行初始化的地方
 */
public class MyApplication extends Application{

    //B.创建一个上下文对象,方便其他类调用.
    private static Context gContext;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化okhttp工具类
        initOkhttp();
        //B.应用一启动,就给上下文赋值
        gContext=this;
    }
    //B.获取上下文的方法
    public static Context getgContext(){
        return gContext;
    }
    private void initOkhttp() {

        OkHttpClient client=new OkHttpClient.Builder()
                //设置连接超时
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                //设置读取超时
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        //进行okhttp工具类初始化的操作
        OkHttpUtils.initClient(client);

    }
}
