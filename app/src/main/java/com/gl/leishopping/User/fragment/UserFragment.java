package com.gl.leishopping.User.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gl.leishopping.LeiShoppingActivity.BaseFragment;

public class UserFragment extends BaseFragment {


    private TextView gTextView;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gTextView = new TextView(getActivity());
        return gTextView;
    }

    @Override
    public void initData() {
        gTextView.setText("我是个人中心");
    }
}
