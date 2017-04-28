package com.gl.leishopping.Home.fragment.Holder;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gl.leishopping.Home.fragment.Adapter.ActAdapter;
import com.gl.leishopping.Home.fragment.bean.ResultBeanData;
import com.gl.leishopping.R;

import java.util.List;

/**
 * 作者：高镭
 * 时间：2017/4/27 13:10
 * 功能：
 */
public class ActViewHolder extends RecyclerView.ViewHolder {

    private final Context gGcontext;


    private List<ResultBeanData.ResultBean.ActInfoBean> gData;
    private final ViewPager gAct_viewpager;

    public ActViewHolder(Context gcontext, View inflate) {
        super(inflate);
        gGcontext = gcontext;
        gAct_viewpager = (ViewPager) inflate.findViewById(R.id.act_viewpager);
    }

    public void setData(List<ResultBeanData.ResultBean.ActInfoBean> data) {
        ActAdapter actAdapter=new ActAdapter(gGcontext,data);
        gAct_viewpager.setAdapter(actAdapter);
    }
}
