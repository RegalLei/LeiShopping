package com.gl.leishopping.Home.fragment.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gl.leishopping.Home.fragment.bean.ResultBeanData;
import com.gl.leishopping.R;
import com.gl.leishopping.Utils.Constants;

import java.util.List;

/**
 * 作者：高镭
 * 时间：2017/4/26 20:28
 * 功能：这是主页频道的adapter,主要是给HomeFragmentAdapter下的内部类ChannelViewHolder使用
 */
public class ChannelAdapter extends BaseAdapter {


    private final Context context;
    private final List<ResultBeanData.ResultBean.ChannelInfoBean> gData;

    public ChannelAdapter(Context context, List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
        this.context = context;
        this.gData = channel_info;
    }

    @Override
    public int getCount() {
        return gData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            //使用复用参数,ListVIew的优化
            convertView = View.inflate(context, R.layout.item_channel, null);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_channel);
        viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_channel);
        //使用Gilde开源框架,让ImageView控件可以根据图片网址直接加载出图片
        Glide.with(context).load(Constants.BASE_URL_IMAGE + gData.get(position).getImage()).into(viewHolder.iv_icon);
        //文件控件加载出数据
        viewHolder.tv_title.setText(gData.get(position).getChannel_name());

        return convertView;
    }


    //创建GradeView的VIewHolder
    static class ViewHolder {
        ImageView iv_icon;
        TextView tv_title;
    }

}
