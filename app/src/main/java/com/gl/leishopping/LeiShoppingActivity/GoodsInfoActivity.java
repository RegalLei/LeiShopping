package com.gl.leishopping.LeiShoppingActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gl.leishopping.R;

//展示商品详情的类,其XML布局最复杂
public class GoodsInfoActivity extends AppCompatActivity implements View.OnClickListener {
    //使用工具进行的初始化
    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private WebView wbGoodInfoMore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;

    //更多布局控件的实例化
    private TextView tv_more_share;
    private TextView tv_more_search;
    private TextView tv_more_home;
    private Button btn_more;
    private LinearLayout ll_root;

    private void findViews() {
        ibGoodInfoBack = (ImageButton)findViewById( R.id.ib_good_info_back );
        ibGoodInfoMore = (ImageButton)findViewById( R.id.ib_good_info_more );
        ivGoodInfoImage = (ImageView)findViewById( R.id.iv_good_info_image );
        tvGoodInfoName = (TextView)findViewById( R.id.tv_good_info_name );
        tvGoodInfoDesc = (TextView)findViewById( R.id.tv_good_info_desc );
        tvGoodInfoPrice = (TextView)findViewById( R.id.tv_good_info_price );
        tvGoodInfoStore = (TextView)findViewById( R.id.tv_good_info_store );
        tvGoodInfoStyle = (TextView)findViewById( R.id.tv_good_info_style );
        wbGoodInfoMore = (WebView)findViewById( R.id.wb_good_info_more );
        llGoodsRoot = (LinearLayout)findViewById( R.id.ll_goods_root );

        tvGoodInfoCallcenter = (TextView)findViewById( R.id.tv_good_info_callcenter );
        tvGoodInfoCollection = (TextView)findViewById( R.id.tv_good_info_collection );
        tvGoodInfoCart = (TextView)findViewById( R.id.tv_good_info_cart );

        btnGoodInfoAddcart = (Button)findViewById( R.id.btn_good_info_addcart );

        tv_more_share= (TextView) findViewById(R.id.tv_more_share);
        tv_more_search= (TextView) findViewById(R.id.tv_more_search);
        tv_more_home= (TextView) findViewById(R.id.tv_more_home);
        btn_more= (Button) findViewById(R.id.btn_more);

        ll_root = (LinearLayout) findViewById(R.id.ll_root);

        //点击事件
        ibGoodInfoBack.setOnClickListener( this );
        ibGoodInfoMore.setOnClickListener( this );
        btnGoodInfoAddcart.setOnClickListener( this );

        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);
        //设置更多布局栏里控件的点击事件
        tv_more_share.setOnClickListener(this);
        tv_more_search.setOnClickListener(this);
        tv_more_home.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        findViews();
    }

    @Override
    public void onClick(View v) {
        //返回的按钮,关闭商品详情页
        if ( v == ibGoodInfoBack ) {
            finish();
        } else if ( v == ibGoodInfoMore ) {
            Toast.makeText(GoodsInfoActivity.this, "更多", Toast.LENGTH_SHORT).show();
            ll_root.setVisibility(View.VISIBLE);

        } else if ( v == btnGoodInfoAddcart ) {
            Toast.makeText(this, "购物车", Toast.LENGTH_SHORT).show();
        } else if( v == tvGoodInfoCallcenter){
            Toast.makeText(GoodsInfoActivity.this, "联系客服", Toast.LENGTH_SHORT).show();
        }else if( v == tvGoodInfoCollection){
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        }else if( v == tvGoodInfoCart){
            Toast.makeText(GoodsInfoActivity.this, "购物车", Toast.LENGTH_SHORT).show();
        }else if( v == tv_more_share){
            Toast.makeText(GoodsInfoActivity.this, "分享", Toast.LENGTH_SHORT).show();
        }else if( v == tv_more_search){
            Toast.makeText(GoodsInfoActivity.this, "搜索", Toast.LENGTH_SHORT).show();
        }else if( v == tv_more_home){
            Toast.makeText(GoodsInfoActivity.this, "首页", Toast.LENGTH_SHORT).show();
        }
    }

}
