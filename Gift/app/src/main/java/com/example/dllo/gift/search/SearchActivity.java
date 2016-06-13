package com.example.dllo.gift.search;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseActivity;
import com.example.dllo.gift.details.DetailsCategoryGiftSelectionActivity;
import com.example.dllo.gift.nettools.NetListener;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;
import com.google.gson.Gson;

/**
 * Created by ZHI on 2016/6/11.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private EditText etSearch;
    private SearchFragment searchFragment;
    private FrameLayout frameLayoutSearch;
    private SearchRecyclerViewAdapter adapter;
    private NetTools netTools;

    @Override
    public void initActivity() {
       setContentView(R.layout.activity_search);
       findViewById(R.id.back_search_title).setOnClickListener(this);
        findViewById(R.id.tv_select_search).setOnClickListener(this);
        findViewById(R.id.tv_search_title).setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_search);
        etSearch = (EditText) findViewById(R.id.et_search_title);
        frameLayoutSearch = (FrameLayout) findViewById(R.id.framelayout_search);

        searchFragment = new SearchFragment();

        adapter = new SearchRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(this,4);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        netTools = new NetTools();
        netTools.getNormalData(URLValues.SEARCH_LIST, new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                SearchBean bean = gson.fromJson(result,SearchBean.class);
                adapter.setBeans(bean);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back_search_title:
                finish();
                break;
            case R.id.tv_select_search:
                Intent intent = new Intent(this, DetailsCategoryGiftSelectionActivity.class);
                startActivity(intent);
            case R.id.tv_search_title:
                frameLayoutSearch.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_search,searchFragment).commit();
                break;

        }

    }
}
