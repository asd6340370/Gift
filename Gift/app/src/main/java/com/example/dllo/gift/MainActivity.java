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

        //初始化

        discoverFragment = new DiscoverFragment();
        hotFragment = new HotFragment();
        categoryFragment = new CategoryFragment();
        meFragment = new MeFragment();
        //默认页面
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout_home,discoverFragment)
                .add(R.id.frameLayout_home,hotFragment).add(R.id.frameLayout_home,categoryFragment)
                .add(R.id.frameLayout_home,meFragment)
                .commit();
        showFragment(discoverFragment);

    }

    //通过FragmentManager 碎片管理器 替换fragment方法
    private void showFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.hide(discoverFragment).hide(hotFragment).hide(categoryFragment).hide(meFragment)
                .show(fragment).commit();

//        transaction.add(new DiscoverFragment(),"discoverFragment").add(new HotFragment(),"hot")
//                .add(new CategoryRaiderFragment(),"category").add(new MeFragment(),"me").commit();
//        transaction.hide(manager.getFragment(null,"discoverFragment")).commit()

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_discover:
                showFragment( discoverFragment);
                tvTitle.setText("礼物说");
                titleLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_hot:
                showFragment( hotFragment);
                tvTitle.setText("热门");
                titleLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_category:
                showFragment( categoryFragment);
                titleLayout.setVisibility(View.GONE);
                break;
            case R.id.btn_me:
                showFragment( meFragment);
                titleLayout.setVisibility(View.GONE);
                break;

        }

    }
}
