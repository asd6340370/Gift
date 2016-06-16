package com.example.dllo.gift.search;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.dllo.gift.tools.CodingFormat;
import com.example.dllo.gift.tools.RecyclerOnClickListener;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ZHI on 2016/6/11.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener, SearchRecyclerViewOnClickListerner, TextWatcher {

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
        etSearch = (EditText)findViewById(R.id.et_search_title);
        etSearch.addTextChangedListener(this);
        frameLayoutSearch = (FrameLayout) findViewById(R.id.framelayout_search);

        searchFragment = new SearchFragment();

        adapter = new SearchRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(this);
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        netTools = new NetTools();
        netTools.getNormalData(URLValues.SEARCH_LIST, new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                SearchBean bean = gson.fromJson(result, SearchBean.class);
                adapter.setBeans(bean);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_search, searchFragment).commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_search_title:
                finish();
                break;
            case R.id.tv_select_search:
                Intent intent = new Intent(this, DetailsCategoryGiftSelectionActivity.class);
                startActivity(intent);
            case R.id.tv_search_title:
                if (!etSearch.getText().toString().isEmpty()) {
                    String name = etSearch.getText().toString();
                    sendNameForBroadcast(name);
                    frameLayoutSearch.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(this, "请输如内容", Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }

    @Override
    public void onItemClick(int position, String name) {
        etSearch.setText(name);
        etSearch.setSelection(name.length());
        frameLayoutSearch.setVisibility(View.VISIBLE);
        sendNameForBroadcast(name);
    }


    //editText 监听
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty()) {
            frameLayoutSearch.setVisibility(View.GONE);
        }
    }

    private void sendNameForBroadcast(String name) {
        String newName = CodingFormat.codingFormat(name);
        Intent intent = new Intent("com.example.dllo.gift.SEARCH");
        intent.putExtra("name",newName);
        sendBroadcast(intent);
    }

}
