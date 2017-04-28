package com.gl.leishopping.Home.fragment.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gl.leishopping.Home.fragment.bean.ResultBeanData;
import com.gl.leishopping.R;
import com.gl.leishopping.Utils.Constants;

import java.util.List;

/**
 * 作者：高镭
 * 时间：2017/4/27 13:17
 * 功能：
 */
public class ActAdapter extends PagerAdapter{

    private final Context gGcontext;
    private final List<ResultBeanData.ResultBean.ActInfoBean> gData;

    public ActAdapter(Context gcontext, List<ResultBeanData.ResultBean.ActInfoBean> data) {
        gGcontext = gcontext;
        gData = data;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=View.inflate(gGcontext, R.layout.act_item,null);
        ImageView act_image= (ImageView) view.findViewById(R.id.act_image);
        Glide.with(gGcontext).load(Constants.BASE_URL_IMAGE+gData.get(position).getIcon_url()).into(act_image);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    @Override
    public int getCount() {
        return gData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
