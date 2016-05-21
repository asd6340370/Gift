package com.example.dllo.gift;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.gift.base.BaseActivity;
import com.example.dllo.gift.category.CategoryFragment;
import com.example.dllo.gift.discover.DiscoverFragment;
import com.example.dllo.gift.discover.DiscoverSelectionFragment;
import com.example.dllo.gift.hot.HotFragment;
import com.example.dllo.gift.me.MeFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private RadioButton btnDiscover, btnHot, btnCategory, btnMe;
    private FragmentTransaction transaction;
    private DiscoverFragment discoverFragment;

    private HotFragment hotFragment;
    private CategoryFragment categoryFragment;
    private MeFragment meFragment;
    private TextView tvTitle;
    private RelativeLayout titleLayout;

    private TextView btnLeft,btnRight;

    private CategoryTitleButton categoryTitleButton;



    @Override
    public void initActivity() {
        setContentView(R.layout.activity_main);
        btnDiscover = (RadioButton) findViewById(R.id.btn_discover);
        btnHot = (RadioButton) findViewById(R.id.btn_hot);
        btnCategory = (RadioButton) findViewById(R.id.btn_category);
        btnMe = (RadioButton) findViewById(R.id.btn_me);
        tvTitle = (TextView) findViewById(R.id.tv_title_main);
        titleLayout = (RelativeLayout) findViewById(R.id.title_layout_main);
        btnDiscover.setChecked(true);
        btnDiscover.setOnClickListener(this);
        btnHot.setOnClickListener(this);
        btnCategory.setOnClickListener(this);
        btnMe.setOnClickListener(this);

        btnLeft = (TextView)findViewById(R.id.btn_left_title_category);
        btnRight = (TextView)findViewById(R.id.btn_right_title_category);

        //初始化

        discoverFragment = new DiscoverFragment();
        hotFragment = new HotFragment();
        categoryFragment = new CategoryFragment();
        meFragment = new MeFragment();
        //默认页面
        replaceFragment(R.id.frameLayout_home, discoverFragment);


    }

    //通过FragmentManager 碎片管理器 替换fragment方法
    private void replaceFragment(int id, Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(id, fragment).commit();

//        transaction.add(new DiscoverFragment(),"discoverFragment").add(new HotFragment(),"hot")
//                .add(new CategoryRaiderFragment(),"category").add(new MeFragment(),"me").commit();
//        transaction.hide(manager.getFragment(null,"discoverFragment")).commit()

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_discover:
                replaceFragment(R.id.frameLayout_home, discoverFragment);
                tvTitle.setText("礼物说");
                titleLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_hot:
                replaceFragment(R.id.frameLayout_home, hotFragment);
                tvTitle.setText("热门");
                titleLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_category:
                replaceFragment(R.id.frameLayout_home, categoryFragment);
                titleLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_me:
                replaceFragment(R.id.frameLayout_home, meFragment);
                titleLayout.setVisibility(View.GONE);
                break;

        }

    }
}