package com.gl.leishopping.Home.fragment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gl.leishopping.Home.fragment.Holder.ActViewHolder;
import com.gl.leishopping.Home.fragment.Holder.ChannelViewHolder;
import com.gl.leishopping.Home.fragment.Holder.HotViewHolder;
import com.gl.leishopping.Home.fragment.Holder.RecommendViewHolder;
import com.gl.leishopping.Home.fragment.Holder.SeckillViewHolder;
import com.gl.leishopping.Home.fragment.bean.ResultBeanData;
import com.gl.leishopping.LeiShoppingActivity.GoodsInfoActivity;
import com.gl.leishopping.R;
import com.gl.leishopping.Utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者：高镭
 * 时间：2017/4/26 13:25
 * 功能：就是HomeFragment下的RecyclerView适配器,在这里实现主页面丰富多彩的效果
 * 1.搭建加载不同类型的Item的RecyclerView适配器框架
 * 2.实现第一个item,广告条效果
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter {
    //上下文
    private Context gcontext;
    //A.RecycleView加载的Bean数据
    private ResultBeanData.ResultBean gresult;
    //A.方面加载布局的填充器对象
    private final LayoutInflater gLayoutInflater;

    public HomeFragmentAdapter(Context context, ResultBeanData.ResultBean result) {
        gcontext = context;
        gresult = result;
        gLayoutInflater = LayoutInflater.from(gcontext);
    }

    //A.实际相当于创建getView里的创建item一样(这里创建的是Holder)
    // 参数:viewType,代表了当前的类型,由getItemViewType的返回值决定
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //B.判断返回类型,根据返回类型,决定创建什么样的Holder
        if (viewType == BANNER) {
            //返回创建的BannerViewHolder
            return new BannerViewHolder(gcontext, gLayoutInflater.inflate(R.layout.banner_viewpager, null));
        }else if(viewType==CHANNEL){
            //C.返回创建的ChannelViewHolder
            return  new ChannelViewHolder(gcontext,gLayoutInflater.inflate(R.layout.channel_item,null));
        }else if(viewType==ACT){
            //D.返回创建的ActViewHolder
            return  new ActViewHolder(gcontext,gLayoutInflater.inflate(R.layout.act_holder,null));
        }else if(viewType==SECKILL){
            //E.返回创建的SeckillViewHolder
            return  new SeckillViewHolder(gcontext,gLayoutInflater.inflate(R.layout.seckill_item,null));
        }else if(viewType==RECOMMEND){
            //F.返回创建的RecommendViewHolder
            return  new RecommendViewHolder(gcontext,gLayoutInflater.inflate(R.layout.recommend_item,null));
        }else if(viewType==HOT){
            //G.返回创建的HotViewHolder
            return  new HotViewHolder(gcontext,gLayoutInflater.inflate(R.layout.hot_item,null));
        }
        return null;
    }

    //A.相当getView中的绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        //B.更加Item的位置,调用getItemViewType,知道给此item绑定什么类型的ViewHolder
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //给BannerViewHolder设置数据
            bannerViewHolder.setData(gresult.getBanner_info());
            //C
        }else if(getItemViewType(position)==CHANNEL){
            ChannelViewHolder channelViewHolder= (ChannelViewHolder) holder;
            //给频道的ViewHolder设置数据
            channelViewHolder.setData(gresult.getChannel_info());
            //D
        }else if(getItemViewType(position)==ACT){
            ActViewHolder actViewHolder= (ActViewHolder) holder;
            //给ActViewHolder设置数据
            actViewHolder.setData(gresult.getAct_info());
            //E
        }else if(getItemViewType(position)==SECKILL){
            SeckillViewHolder seckillViewHolder= (SeckillViewHolder) holder;
            //给SeckillViewHolder设置数据
            seckillViewHolder.setData(gresult.getSeckill_info());
        }else if(getItemViewType(position)==RECOMMEND){
            RecommendViewHolder recommendViewHolder= (RecommendViewHolder) holder;
            //给RecommendViewHolder设置数据
            recommendViewHolder.setData(gresult.getRecommend_info());
        }else if(getItemViewType(position)==HOT){
            //给 HotViewHolder设置数据
            HotViewHolder hotViewHolder= (HotViewHolder) holder;
            hotViewHolder.setData(gresult.getHot_info());
        }

    }

    //A.广告条幅类型(int数从0开始,数组从0开始,程序界:万物从0开始)
    private static final int BANNER = 0;
    //A.频道类型
    private static final int CHANNEL = 1;
    //A.活动类型
    private static final int ACT = 2;
    //A.秒杀类型
    private static final int SECKILL = 3;
    //A.推荐类型
    private static final int RECOMMEND = 4;
    //A.热卖类型
    private static final int HOT = 5;

    //A.当前类型
    private int currentType = BANNER;

    //A.得到类型  position就代表条目的位置
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    /**
     * A.决定RecyclerView有几条Item
     * 提示:我们没做一个ViewHolder,这里的数据必须变化,才能有效果
     *
     * @return
     */

    @Override
    public int getItemCount() {
        return 6;
    }

    /**
     * B.主界面的广告条,Banner,各种类型的VIewHolder都要集成到RecyclerView的VIewHolder
     */

    private class BannerViewHolder extends RecyclerView.ViewHolder {
        private final Banner mBanner;
        private Context mContext;

        public BannerViewHolder(Context context, View inflate) {
            super(inflate);
            mContext = context;
            mBanner = (Banner) inflate.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            //给Banner控件设置加载图片的数据,如果仅仅加载网址,就要设置监听,在其内部使用图片开源框架加载图片Glide
            ArrayList<String> images = new ArrayList<>();
            //从BannerInfoBean容器中拿到图片的网址,在放到集合里
            for (int i = 0; i < banner_info.size(); i++) {
                String image = banner_info.get(i).getImage();
                images.add(image);
            }
            //设置广告条循环时所用到的小点.//设置广告条循环时所用到的小点.
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

            //参数:1.String类型的集合    2.加载图片后的回调监听
            mBanner.setImages(images, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    //联网请求图片,使用的是Glide根据网址获取到图片,使ImageView与Glide转换的数据进行绑定
                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE + url)
                            .into(view);
                }
            });

            //设置广告条轮播时就是手风琴的效果
            mBanner.setBannerAnimation(Transformer.Accordion);
            //设置轮播图的item点击事件
            mBanner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    //根据点击事件跳转到商品详情页,因为好多地方都要用到此逻辑,所以抽取此方法
                    Intent intent = new Intent(gcontext,GoodsInfoActivity.class);
                    gcontext.startActivity(intent);
                }
            });
        }
    }


}
