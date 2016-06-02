package com.example.dllo.gift.category;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.category.categoryadapter.CategoryRaiderHeaderRecyclerViewAdapter;
import com.example.dllo.gift.category.categoryadapter.CategoryGiftListViewContentAdapter;
import com.example.dllo.gift.category.categoryadapter.CategoryRaiderListViewAdapter;
import com.example.dllo.gift.category.categorybean.CategoryRaiderBean;
import com.example.dllo.gift.category.categorybean.CategoryRaiderHeaderRVBean;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dllo on 16/5/19.
 */
public class CategoryRaiderFragment extends BaseFragment implements View.OnClickListener {


    private ListView listViewRaider;
    private RecyclerView headerRecyclerView;
    private CategoryRaiderHeaderRecyclerViewAdapter headerRecyclerViewAdapter;
    private CategoryGiftListViewContentAdapter listViewContentAdapter;
    private NetTools netTools;
    private EventBus eventBus = EventBus.getDefault();
    private CategoryRaiderListViewAdapter raiderAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_category_raider;
    }

    @Override
    public void initView(View view) {
        listViewRaider = (ListView) view.findViewById(R.id.listview_category_raider);
        View headerView = LayoutInflater.from(context).inflate(R.layout.header_listview_category_raider, null);
        headerView.findViewById(R.id.tv_content_all_category_raider).setOnClickListener(this);
        headerRecyclerView = (RecyclerView) headerView.findViewById(R.id.recyclerview_category_raider);
        listViewRaider.addHeaderView(headerView);
    }

    @Override
    public void initData() {

        eventBus.register(this);
        headerRecyclerViewAdapter = new CategoryRaiderHeaderRecyclerViewAdapter(context);
        headerRecyclerView.setAdapter(headerRecyclerViewAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        headerRecyclerView.setLayoutManager(manager);

        netTools = new NetTools();
        netTools.getDataForEventBus(URLValues.CATEGORY_RAIDER_HEADER_BANNER, CategoryRaiderHeaderRVBean.class);
        netTools.getDataForEventBus(URLValues.CATEGORY_RAIDER, CategoryRaiderBean.class);
        raiderAdapter = new CategoryRaiderListViewAdapter(context);
        listViewRaider.setAdapter(raiderAdapter);
    }


    @Override
    public void onClick(View v) {

    }
    @Subscribe
    public void getData(CategoryRaiderBean raiderBean){
        raiderAdapter.setRaiderBeans(raiderBean);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        headerRecyclerViewAdapter.unregister();
        eventBus.unregister(this);
    }
}
