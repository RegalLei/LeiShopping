package com.gl.leishopping.Home.fragment.Holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.gl.leishopping.Cart.fragment.bean.GoodsBean;
import com.gl.leishopping.Home.fragment.Adapter.RecommendViewAdapter;
import com.gl.leishopping.Home.fragment.bean.ResultBeanData;
import com.gl.leishopping.LeiShoppingActivity.GoodsInfoActivity;
import com.gl.leishopping.R;

import java.util.List;

/**
 * 作者：高镭
 * 时间：2017/4/27 18:42
 * 功能：
 */
public class RecommendViewHolder extends RecyclerView.ViewHolder {
    private final Context gGcontext;
    private List<ResultBeanData.ResultBean.RecommendInfoBean> gData;
    private final TextView gTv_more_recommend;
    private final GridView gGv_recommend;
    private static final String Goods_Bean="goodsbean";
    private GoodsBean goodsBean=new GoodsBean();
    public RecommendViewHolder(final Context gcontext, View inflate) {
        super(inflate);
        gGcontext = gcontext;
        gTv_more_recommend = (TextView) inflate.findViewById(R.id.tv_more_recommend);
        gGv_recommend = (GridView) inflate.findViewById(R.id.gv_recommend);

        //设置Gridview的点击事件
        gGv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //根据点击事件跳转到商品详情页,因为好多地方都要用到此逻辑,所以抽取此方法
                Intent intent = new Intent(gGcontext,GoodsInfoActivity.class);
                intent.putExtra(Goods_Bean,goodsBean);
                gGcontext.startActivity(intent);
            }
        });
    }

    public void setData(List<ResultBeanData.ResultBean.RecommendInfoBean> data) {
        //有数据,创建适配器类对象
        RecommendViewAdapter recommendViewAdapter=new RecommendViewAdapter(gGcontext,data);
        //为GridVIew设置适配器(注意:要有效果的话,记得把getItemCount()该为5即可)
        gGv_recommend.setAdapter(recommendViewAdapter);
    }
}
