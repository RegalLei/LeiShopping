package com.gl.leishopping.Home.fragment.Holder;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gl.leishopping.Cart.fragment.bean.GoodsBean;
import com.gl.leishopping.Home.fragment.Adapter.SeckillViewAdapter;
import com.gl.leishopping.Home.fragment.bean.ResultBeanData;
import com.gl.leishopping.LeiShoppingActivity.GoodsInfoActivity;
import com.gl.leishopping.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：高镭
 * 时间：2017/4/27 18:41
 * 功能：
 */
public class SeckillViewHolder extends RecyclerView.ViewHolder {
    private final Context gGcontext;
    private ResultBeanData.ResultBean.SeckillInfoBean gData;
    private final RecyclerView gRv_seckill;
    private final TextView gTv_time_seckill;
    private final TextView gTv_more_seckill;
    private static final String Goods_Bean="goodsbean";
    //E2.倒计时的时间,从服务器那拿两个值,进行相减得到倒计时的真实数值.这里定义了个临时变量
    private long ms;
    Handler gHandler=new Handler(){
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
               ms=ms-1000;
            //E2.把拿到的秒值时间数据转换为小时,分,秒的形式
            SimpleDateFormat simpletime=new SimpleDateFormat("HH:mm:ss");
            String time = simpletime.format(new Date(ms));
            //E2.把倒计时显示到UI上
            gTv_time_seckill.setText(time);
            //E2.在不断发送消息前先移除一下,减少OOM
            gHandler.removeMessages(0);
            gHandler.sendEmptyMessageDelayed(0,1000);
            //E2.经过我们的仔细观察可以看到,时间为0时,依然再减,所以要进行判断
            if(ms<=0){
                //把所有消息移除
                gHandler.removeCallbacksAndMessages(null);
            }
        }
    };
    private GoodsBean goodsBean=new GoodsBean();

    public SeckillViewHolder(Context gcontext, View inflate) {
        super(inflate);
        gGcontext = gcontext;
        gTv_more_seckill = (TextView) itemView.findViewById(R.id.tv_more_seckill);
        gTv_time_seckill = (TextView) itemView.findViewById(R.id.tv_time_seckill);
        gRv_seckill = (RecyclerView) itemView.findViewById(R.id.rv_seckill);
    }

    public void setData(ResultBeanData.ResultBean.SeckillInfoBean data) {
        SeckillViewAdapter seckillViewAdapter=new SeckillViewAdapter(gGcontext,data.getList());
        gRv_seckill.setAdapter(seckillViewAdapter);
        //设置布局管理器,参数:  1.上下文    2.设置方向(LinearLayoutManager.HORIZONTAL水平方向)   3.是不是倒序,false代表不是
        //提示:要有效果的话,记得把getItemCount()该为4即可
        gRv_seckill.setLayoutManager(new LinearLayoutManager(gGcontext,LinearLayoutManager.HORIZONTAL,false));
        //设置item的点击事件(注意:RecycleView没有设置点击事件的方法,这个使用的是自己封装的方法)
        seckillViewAdapter.setOnSeckillRecyclerView(new SeckillViewAdapter.OnSeckillRecyclerView() {
            @Override
            public void OmItemClick(int position) {
                //根据点击事件跳转到商品详情页,因为好多地方都要用到此逻辑,所以抽取此方法
                Intent intent = new Intent(gGcontext,GoodsInfoActivity.class);
                intent.putExtra(Goods_Bean,goodsBean);
                gGcontext.startActivity(intent);
            }
        });

        //E2.计算秒杀倒计时,应为从bean集合里,拿到的时间数据不是int型,所以用Integer进行转换(其逻辑代码就是java基础的内容)
        ms=Integer.valueOf(data.getEnd_time())-Integer.valueOf(data.getStart_time());
        //E2.建立handler实现定时器的效果,循环发送消息,以便能够使时间不断减一
        gHandler.sendEmptyMessageDelayed(0,1000);
    }
}
