package com.gl.leishopping.LeiShoppingActivity;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 作者：高镭
 * 时间：2017/4/25 18:28
 * 功能：自定义的Application(记得在清单文件进行配置),对使用的第三方开源框架进行初始化的地方
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化okhttp工具类
        initOkhttp();
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
