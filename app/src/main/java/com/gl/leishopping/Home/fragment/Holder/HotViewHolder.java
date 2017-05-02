package com.gl.leishopping.Home.fragment.Holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.gl.leishopping.Cart.fragment.bean.GoodsBean;
import com.gl.leishopping.Home.fragment.Adapter.HotViewAdapter;
import com.gl.leishopping.Home.fragment.bean.ResultBeanData;
import com.gl.leishopping.LeiShoppingActivity.GoodsInfoActivity;
import com.gl.leishopping.R;

import java.util.List;

/**
 * 作者：高镭
 * 时间：2017/4/27 18:43
 * 功能：
 */
public class HotViewHolder extends RecyclerView.ViewHolder {
    private final Context gGcontext;
    private List<ResultBeanData.ResultBean.HotInfoBean> gData;
    private final TextView gTv_more_hot;
    private final GridView gGv_hot;
    private static final String Goods_Bean="new GoodsBean()";
    private GoodsBean goodsBean=new GoodsBean();
    public HotViewHolder(Context gcontext, View inflate) {
        super(inflate);
        gGcontext = gcontext;
        gTv_more_hot = (TextView) inflate.findViewById(R.id.tv_more_hot);
        gGv_hot = (GridView) inflate.findViewById(R.id.gv_hot);
        //设置GridView的点击事件
        gGv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //根据点击事件跳转到商品详情页,因为好多地方都要用到此逻辑,所以抽取此方法
                Intent intent = new Intent(gGcontext,GoodsInfoActivity.class);
                intent.putExtra(Goods_Bean,goodsBean);
                gGcontext.startActivity(intent);
            }
        });
    }

    public void setData(List<ResultBeanData.ResultBean.HotInfoBean> data) {
        //有数据,创建适配器对象
        HotViewAdapter hotViewAdapter=new HotViewAdapter(gGcontext,data);
        gGv_hot.setAdapter(hotViewAdapter);
    }
}
