package com.gl.leishopping.Home.fragment.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gl.leishopping.Home.fragment.bean.ResultBeanData;
import com.gl.leishopping.R;
import com.gl.leishopping.Utils.Constants;

import java.util.List;

/**
 * 作者：高镭
 * 时间：2017/4/27 19:14
 * 功能：这是主页秒杀的adapter,主要是给HomeFragmentAdapter下的内部类SeckillViewHolde使用
 */
public class SeckillViewAdapter extends RecyclerView.Adapter<SeckillViewAdapter.ViewHolder>{

    private final Context gGcontext;
    private final List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> gList;
    //构造方法,初始化上下文对象及数据
    public SeckillViewAdapter(Context gcontext, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list) {
        gGcontext = gcontext;
        gList = list;
    }
    //创建ViewHolder对象
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(gGcontext, R.layout.item_seckill, null);
        return new ViewHolder(view);
    }
    //给VIewHolder进行数据的绑定.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //更加位置得到对应的数据,再进行绑定
        ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = gList.get(position);
        //使用Glide开源框架根据网址是ImageView加载图片
        Glide.with(gGcontext).load(Constants.BASE_URL_IMAGE+listBean.getFigure()).into(holder.gIv_figure);
        holder.gTv_cover_price.setText(listBean.getCover_price());
        //画一横线过时的
        holder.gTv_lod_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|
                Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public int getItemCount() {
        return gList.size();
    }

    //创建一个单独的ViewHolder,RecycleView自身没有点击事件的方法,不过可以给VIew对象设置点击事件,至于条目的位置由getLayoutPosition()来获取
    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView gIv_figure;
        private final TextView gTv_cover_price;
        private final TextView gTv_lod_price;

        public ViewHolder(View itemView) {
            super(itemView);
            gIv_figure = (ImageView) itemView.findViewById(R.id.iv_figure);
            gTv_cover_price = (TextView) itemView.findViewById(R.id.tv_cover_price);
            gTv_lod_price = (TextView) itemView.findViewById(R.id.tv_lod_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(gOnSeckillRecyclerView!=null){
                        gOnSeckillRecyclerView.OmItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }
    //B.自定义的一个RecycleView点击事件的接口.
    public interface OnSeckillRecyclerView{
        //当某条被点击时回调
        public void OmItemClick(int position);
    }
    private OnSeckillRecyclerView gOnSeckillRecyclerView;

    public void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView) {
        gOnSeckillRecyclerView = onSeckillRecyclerView;
    }
}
