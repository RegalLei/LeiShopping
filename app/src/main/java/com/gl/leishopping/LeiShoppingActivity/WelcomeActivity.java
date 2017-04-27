package com.gl.leishopping.LeiShoppingActivity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gl.leishopping.R;

//要想把Toolbar取消掉,可以继承Activity,修改主题即可全屏显示该页面          .....Light.NoActionBar
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //3秒后进入主界面，使用Handler对象延时发送，参数 1.Runnable() 2.毫秒值
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //由启动页跳到主页面
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                //结束Activity
                finish();
            }
        }, 3000);
    }
}
