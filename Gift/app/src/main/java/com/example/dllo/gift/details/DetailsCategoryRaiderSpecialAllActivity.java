package com.example.dllo.gift.details;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseActivity;
import com.example.dllo.gift.category.categorybean.CategoryRaiderSpecialRVBean;
import com.example.dllo.gift.details.detailsadapter.DetailsCategoryRaiderSpecialallAdapter;
import com.example.dllo.gift.nettools.NetListener;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/6/4.
 */
public class DetailsCategoryRaiderSpecialAllActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ImageView back;
    private ListView listViewSpecialAll;
    private CategoryRaiderSpecialRVBean specialRVBean;
    private NetTools netTools;
    private DetailsCategoryRaiderSpecialallAdapter specialallAdapter;
    @Override
    public void initActivity() {
        setContentView(R.layout.activity_details_category_raider_specialall);
        back = (ImageView) findViewById(R.id.back_details_category_raider_specialall_title);
        back.setOnClickListener(this);
        listViewSpecialAll = (ListView) findViewById(R.id.listview_details_category_raider_specialall);
        specialallAdapter = new DetailsCategoryRaiderSpecialallAdapter(this);
        listViewSpecialAll.setAdapter(specialallAdapter);
        listViewSpecialAll.setOnItemClickListener(this);

        netTools = new NetTools();
        netTools.getNormalData(URLValues.CATEGORY_RAIDER_HEADER_BANNER, new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson  = new Gson();
                specialRVBean = gson.fromJson(result,CategoryRaiderSpecialRVBean.class);
                specialallAdapter.setSpecialRVBean(specialRVBean);

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String urlId = String.valueOf(specialRVBean.getData().getCollections().get(position).getId());
        Intent intent = new Intent(this,DetailsSpecialActivity.class);
        intent.putExtra("urlId",urlId);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
