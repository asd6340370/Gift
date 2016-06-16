package com.example.dllo.gift.discover;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.nettools.NetTools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/19.
 */
public class DiscoverFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener, DiscoverMenuFragment.DiscoverMenuOnItemClick {
    private TabLayout discoverTabLayout;
    private ViewPager discoverViewPager;
    private DiscoverVPAdapter adapter;
    private NetTools netTools;
    private CheckBox checkBoxMenu;
    private TextView tvTitle;
    private FrameLayout frameLayoutMenu;
    private DiscoverMenuFragment menuFragment;
    @Override
    public int setLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    public void initView(View view) {
        discoverTabLayout = (TabLayout) view.findViewById(R.id.tabLayout_discover);
        discoverViewPager = (ViewPager) view.findViewById(R.id.viewPager_discover);
        checkBoxMenu = (CheckBox) view.findViewById(R.id.checkbox_tablayout_menu);
        checkBoxMenu.setOnCheckedChangeListener(this);
        tvTitle = (TextView) view.findViewById(R.id.tv_tablayout_menu);
        frameLayoutMenu = (FrameLayout) view.findViewById(R.id.framelayout_tablayout_menu);

        menuFragment = new DiscoverMenuFragment();
        menuFragment.setMenuOnItemClick(this);
    }

    public FrameLayout getFrameLayoutMenu() {
        return frameLayoutMenu;
    }

    public CheckBox getCheckBoxMenu() {
        return checkBoxMenu;
    }

    @Override
    public void initData() {
        netTools = new NetTools();

        netTools.getDiscoverTitles();
        adapter = new DiscoverVPAdapter(getChildFragmentManager());
        discoverViewPager.setAdapter(adapter);

        discoverTabLayout.setupWithViewPager(discoverViewPager);
        discoverTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

getChildFragmentManager().beginTransaction().replace(R.id.framelayout_tablayout_menu,menuFragment).commit();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.unregister();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            tvTitle.setVisibility(View.VISIBLE);
            frameLayoutMenu.setVisibility(View.VISIBLE);
        }else {
            tvTitle.setVisibility(View.GONE);
            frameLayoutMenu.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(int position) {
        discoverViewPager.setCurrentItem(position);
        checkBoxMenu.setChecked(false);
    }



}
