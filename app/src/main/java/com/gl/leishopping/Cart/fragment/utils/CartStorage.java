package com.gl.leishopping.Cart.fragment.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.gl.leishopping.Cart.fragment.bean.GoodsBean;
import com.gl.leishopping.LeiShoppingActivity.MyApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：高镭
 * 时间：2017/5/2 13:38
 * 功能：商品购物车数据操作类,首先是从本地拿到数据放入(SpareseArray)容器(内存)中,增删改查
 */
public class CartStorage {
    private static final String JSON_CART = "json_cart";
    //A.使类为单例模式,其构造方法里有上下文.
    private static CartStorage instance;
    private Context gContext;
    //B.此集合是安卓特有,而非java,是替代HashMap.
    private final SparseArray<GoodsBean> gGoodsBeanSparseArray;

    //A.得到购物车的实例.
    public static CartStorage getInstance() {
        if (instance == null) {
            instance = new CartStorage(MyApplication.getgContext());
        }
        return instance;
    }


    //A.各类的构造方法,此类以创建就执行的方法,上下文是从MyApplication里得到(MyApplication没有,就创建)
    public CartStorage(Context context) {
        gContext = context;
        //B.创建读取之前存储数据的容器,大小100
        gGoodsBeanSparseArray = new SparseArray<>(100);
        //C.SparseArray容器装好数据.
        listToSparseAeeay();
    }

    //C.把本地读取的数据加入到SparseArray中
    private void listToSparseAeeay() {
    //C.先把本地数据放入List集合里
       List<GoodsBean> goodsBeanList = getAllData();
        //E.把列表数据转化为SparseArray
        for (int i = 0; i < goodsBeanList.size(); i++) {
            //取出对应位置的goodsBean数据
            GoodsBean goodsBean = goodsBeanList.get(i);
            //存入到SparseArray集合中.参数:1.int型,以商品ID作为键(因为商品ID为String类型,所以要进行类型转换)  2.要存数据
            gGoodsBeanSparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);
        }
    }
    //C.读取本地的数据
    private List<GoodsBean> getAllData(){
        ArrayList<GoodsBean> goodsBeanArrayList=new ArrayList<>();
        //D.从本地获取
        String json = CacheUtils.getString(gContext, JSON_CART);
        //D.判断获取到的数据是否为null.
        //使用Gson把String类型转换为list列表
        if(!TextUtils.isEmpty(json))
            goodsBeanArrayList = new Gson().fromJson(json, new TypeToken<List<GoodsBean>>(){}.getType());
        return goodsBeanArrayList;
    }
}
