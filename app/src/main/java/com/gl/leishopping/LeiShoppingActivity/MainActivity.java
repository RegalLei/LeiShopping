package com.gl.leishopping.LeiShoppingActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import com.gl.leishopping.Cart.fragment.CartFragment;
import com.gl.leishopping.Community.fragment.CommunityFragment;
import com.gl.leishopping.Home.fragment.HomeFragment;
import com.gl.leishopping.R;
import com.gl.leishopping.Type.fragment.TypeFragment;
import com.gl.leishopping.User.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.radio)
    RadioGroup gRadio;
    private List<BaseFragment> gFragments;

    /**
     * 1.完成XML布局
     * 2.搭建ButterKnife环境,安装studio插件(提示:ButterKnife7.0,如果用8.0,记着还要对工作空间的build文件进行配置)
     * 3.创建BaseFragment
     * 4.定义各个子页面的Fragment
     * 5.初始化Fragment,将其中放入容器中
     * 6.设置RadioGroup的监听
     * 7.得到对应的Fragment,进行切换
     * 8.对Fragment进行优化,解决切换Fragment,会再次创建Fragment,重走生命周期方法的问题,以及横竖屏切换Fragment,重走生命周期方法的问题
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化Fragment,把每一个都创建出来
        initFragment();
        //默认主界面一运行,就选中的第一个按钮
        gRadio.check(R.id.rb_home);
        //默认加载home的Fragment即可
        RePlaceFM(mContent,gFragments.get(0));
    }

    //初始化各个模块的Fragment，并装入容器中
    private void initFragment() {
        gFragments = new ArrayList<>();
        //添加到容器集合中,按照顺序进行添加
        gFragments.add(new HomeFragment());
        gFragments.add(new TypeFragment());
        gFragments.add(new CommunityFragment());
        gFragments.add(new CartFragment());
        gFragments.add(new UserFragment());
    }

    //选择fragment对应的编号
    private int position;

    @OnClick({R.id.rb_home, R.id.rb_type, R.id.rb_community, R.id.rb_cart, R.id.rb_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                position = 0;
                gRadio.check(R.id.rb_home);
                break;
            case R.id.rb_type:
                position = 1;
                gRadio.check(R.id.rb_type);
                break;
            case R.id.rb_community:
                position = 2;
                gRadio.check(R.id.rb_community);
                break;
            case R.id.rb_cart:
                position = 3;
                gRadio.check(R.id.rb_cart);
                break;
            case R.id.rb_user:
                position = 4;
                gRadio.check(R.id.rb_user);
                break;
            default:
                position = 0;
                gRadio.check(R.id.rb_home);
                break;
        }
        BaseFragment baseFragment = gFragments.get(position);
        //替换Fragment
        RePlaceFM(mContent,baseFragment);
    }



//    private void RePlaceFM(BaseFragment baseFragment) {
//        //拿到事务，获取fragment的经理,方便对fragment的管理
//        FragmentManager supportFragmentManager = getSupportFragmentManager();
//        //通过事务去开启对象
//        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
//        //通过事务对象,去把XML里的控件替换成Fragment,并且提交
//        fragmentTransaction.replace(R.id.framelayout, baseFragment).commit();
//    }

    /*对Fragment进行优化,解决切换Fragment,会再次创建Fragment,重走生命周期方法的问题,
    * 在项目中切换Fragment,一直都用的是replace
    * ,但是这样做有一个问题,每次切换fragment,都会重新实例化,重新加载fragment,
    * 官方文档解释说:replace()这个方法只是在上一个Fragment
    * 不需要时,采用的简便方法,正确的切换方法是add(),切换时hide,add()另一个fragment,
    * 再次切换时,只需hide当前的fragment,show显示另
    * 一个fragment,就能够做到多个Fragment切换时不重新实例化
    * */
    private Fragment mContent;

    /**
     * 替换Fragment,进行缓存优化
     *
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to   马上要切换到的Fragment，一会要显示
     */
    private void RePlaceFM(Fragment from, Fragment to) {
        //刚刚显示的Fragment和马上切换的Fragment是否是同一个对象,是就不做切换,不是就进行切换
        if (from != to) {
            //把切换到的新Fragment替换缓存的Fragment
            mContent = to;
            //拿到事务，获取fragment的经理,方便对fragment的管理
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            //通过事务去开启对象
            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();

            //判断内存中有没有添加这个Fragment
            if (!to.isAdded()) {
                //如果没有被添加的话
                //就开始对上一个进行非空的判断
                if (from != null) {
                    //就开始隐藏上一个Fragment
                    fragmentTransaction.hide(from);
                }
                //对要进行切换的fragment进行非空的判断
                if (to != null) {
//                    添加切换的Fragment,并且要提交
                    fragmentTransaction.add(R.id.framelayout, to).commit();
                }
            }
            //如果已经被添加了
            else{
                //该Fragment已经被添加
                //对上一个fragment进行非空判断
                if(from!=null){
                    //隐藏上一个fragment
                    fragmentTransaction.hide(from);
                }
                //对要切换的Fragment进行非空判断
                if(to!=null){
                    //显示隐藏的要切换的Fragment,并提交,show就不用替换fragment的ID值了
                    fragmentTransaction.show(to).commit();
                }
            }
        }

    }

}
