package com.gl.leishopping.LeiShoppingActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：高镭
 * 时间：2017/4/24 19:36
 * 功能：
 */
public abstract class BaseFragment extends Fragment{

    @Nullable
    @Override
    //让Fragment加载XML布局资源,因为每个子类Fragment显示的界面都不同,所以返回一个抽象方法
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, savedInstanceState);
    }
    public abstract View initView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    public abstract void initData();
}
