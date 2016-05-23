package com.example.dllo.gift.category;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;

import java.util.ArrayList;



/**
 * Created by dllo on 16/5/20.
 */
public class CategoryFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ArrayList<Fragment> fragments;
    private ViewPager categoryViewPager;
    private CategoryRaiderFragment categoryRaiderFragment;
    private CategoryGiftFragment categoryGiftFragment;
    private RadioButton btnLeftCategory,btnRightCategory;

    @Override
    public int setLayout() {
        return R.layout.fragment_category;
    }

    @Override
    public void initView(View view) {

        categoryViewPager = (ViewPager) view.findViewById(R.id.viewPager_category);
        btnLeftCategory = (RadioButton) view.findViewById(R.id.btn_left_title_category);
        btnRightCategory = (RadioButton) view.findViewById(R.id.btn_right_title_category);
        btnLeftCategory.setChecked(true);

    }

    @Override
    public void initData() {
        //viewPager
        CategoryVPAdapter adapter = new CategoryVPAdapter(getChildFragmentManager());
        categoryViewPager.setAdapter(adapter);

        categoryRaiderFragment = new CategoryRaiderFragment();
        categoryGiftFragment = new CategoryGiftFragment();
        fragments = new ArrayList<>();
        fragments.add(categoryRaiderFragment);
        fragments.add(categoryGiftFragment);
        adapter.setFragments(fragments);
        //radioButton
        btnLeftCategory.setOnClickListener(this);
        btnRightCategory.setOnClickListener(this);
        categoryViewPager.addOnPageChangeListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_left_title_category:
                categoryViewPager.setCurrentItem(0);
                break;
            case R.id.btn_right_title_category:
                categoryViewPager.setCurrentItem(1);
                break;

        }

    }
    //viewPager addOnPageChangeListener 第一个方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //viewPager addOnPageChangeListener 第二个方法
    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0 :
                btnLeftCategory.setChecked(true);
                break;
            case 1 :
                btnRightCategory.setChecked(true);
                break;
        }
    }
    //viewPager addOnPageChangeListener 第三个方法
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
