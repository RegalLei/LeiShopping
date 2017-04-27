package com.gl.leishopping.Home.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.gl.leishopping.Home.fragment.bean.ResultBeanData;
import com.gl.leishopping.LeiShoppingActivity.BaseFragment;
import com.gl.leishopping.R;
import com.gl.leishopping.Utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class HomeFragment extends BaseFragment {

    private TextView gTextView;
    private View gView;
    private RecyclerView gRectcler_home;
    private ImageButton gIb_top;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private ResultBeanData.ResultBean gResult;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载布局资源
        gView = View.inflate(getActivity(), R.layout.activity_home_fragment, null);
        gRectcler_home = (RecyclerView) gView.findViewById(R.id.rectcler_home);
        gIb_top = (ImageButton) gView.findViewById(R.id.ib_top);
        tv_search_home = (TextView) gView.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) gView.findViewById(R.id.tv_message_home);
        //给按钮设置点击事件
        initListener();
        return gView;
    }

    private void initListener() {
        //搜索的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
            }
        });
        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "信息中心", Toast.LENGTH_SHORT).show();
            }
        });
        gIb_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //回到顶部
                gRectcler_home.scrollToPosition(0);
            }
        });
    }

    @Override
    public void initData() {
        //使用okhttp工具类get请求网络
        OkhttpGETData();
    }

    //使用okhttp工具类get请求网络
    private void OkhttpGETData() {
        //若要改变请求自己的网址,改变url即可
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()//设置请求网络的方式
                .url(url)//设置请求网址
                //如果请求网址不需要账号,密码,可以把参数删掉
/*                .addParams("username", "hyman")
                .addParams("password", "123")*/
                .build()
                .execute(new StringCallback() {
                    @Override//请求网络失败回调
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(getActivity(), "网络失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override//请求网络成功回调,使用fastJson进行json的解析
                    public void onResponse(String s, int i) {
                        //测试是否能请求网络成功,及线程是否允许在主线程
                        Toast.makeText(getActivity(), "网络成功", Toast.LENGTH_SHORT).show();
                        Log.d("gl", s);
                        //json解析数据
                        processprocessData(s);

                    }
                });
    }

    private void processprocessData(String jsonData) {
        //使用fastJson解析数据,把整理好的数据放入指定的容器中
        // 参数: json的数据   ,容器类的字节码
        ResultBeanData resultBeanData = JSON.parseObject(jsonData, ResultBeanData.class);
        //得到容器中装数据的集合
        gResult = resultBeanData.getResult();
        //对gResult判断对象是否为空
        if(gResult!=null){
            //有数据,创建RecyclerView的适配器,    参数:1.上下文   2.数据
            HomeFragmentAdapter homeFragmentAdapter=new HomeFragmentAdapter(getActivity(),gResult);
            //RecycleView设置适配器
            gRectcler_home.setAdapter(homeFragmentAdapter);
            //提示:使用Re测一测设置布局管理者,决定RecyclerView的整体面貌.    参数:1.上下文   2.决定面貌为一行
           gRectcler_home.setLayoutManager(new GridLayoutManager(getActivity(),1));

        }else{
            Toast.makeText(getActivity(),"没有数据",Toast.LENGTH_SHORT).show();
        }
    }
}
