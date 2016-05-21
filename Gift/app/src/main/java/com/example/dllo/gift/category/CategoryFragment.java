package com.example.dllo.gift.category;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.dllo.gift.CategoryTitleButton;
import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/20.
 */
public class CategoryFragment extends BaseFragment {

    private ArrayList<Fragment> fragments;
    private ViewPager categoryViewPager ;
    private CategoryRaiderFragment categoryRaiderFragment;
    private CategoryGiftFragment categoryGiftFragment;

    @Override
    public int setLayout() {
        return R.layout.fragment_category;
    }

    @Override
    public void initView(View view) {

        categoryViewPager = (ViewPager) view.findViewById(R.id.viewPager_category);

    }

    @Override
    public void initData() {
        //fragment中的fragment 的fragmentManager 需要用getchildFragmentManager()
        CategoryVPAdapter adapter = new CategoryVPAdapter(getChildFragmentManager());
        categoryViewPager .setAdapter(adapter);

         categoryRaiderFragment = new CategoryRaiderFragment();
         categoryGiftFragment = new CategoryGiftFragment();
        fragments = new ArrayList<>();
        fragments.add(categoryRaiderFragment);
        fragments.add(categoryGiftFragment);
        adapter.setFragments(fragments);

    }


//    @Override
//    public void onClick(int i) {
//        //通过FragmentManager替换Fragmet
//        FragmentManager manager = getChildFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        switch (i){
//            case R.id.btn_left_title_category:
//                transaction.replace(R.id.frameLayout_category,categoryRaiderFragment);
//                break;
//            case R.id.btn_right_title_category:
//                transaction.replace(R.id.frameLayout_category,categoryGiftFragment);
//                break;
//        }
//        transaction.commit();
//    }
}
