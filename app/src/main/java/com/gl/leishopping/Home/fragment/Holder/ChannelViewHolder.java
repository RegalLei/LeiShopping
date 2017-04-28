package com.gl.leishopping.Home.fragment.Holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;

import com.gl.leishopping.Home.fragment.Adapter.ChannelAdapter;
import com.gl.leishopping.Home.fragment.bean.ResultBeanData;
import com.gl.leishopping.R;

import java.util.List;

/**
 * 作者：高镭
 * 时间：2017/4/26 20:18
 * 功能：
 */
public class ChannelViewHolder extends RecyclerView.ViewHolder {

    private final Context gContext;
    private final GridView gGv_channel;
    private ChannelAdapter gChannelAdapter1;

    public ChannelViewHolder(Context context, View inflate) {
        super(inflate);
        gContext = context;
        gGv_channel = (GridView) inflate.findViewById(R.id.gv_channel);
    }

    public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
        gChannelAdapter1 = new ChannelAdapter(gContext,channel_info);
        gGv_channel.setAdapter(gChannelAdapter1);
    }

    }


