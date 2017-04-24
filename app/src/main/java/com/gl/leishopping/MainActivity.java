package com.gl.leishopping;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @Bind(R.id.radio)
    RadioGroup gRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        gRadio.check(R.id.rb_home);
    }
}
