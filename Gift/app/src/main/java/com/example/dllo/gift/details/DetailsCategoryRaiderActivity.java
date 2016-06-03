package com.example.dllo.gift.details;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseActivity;
import com.example.dllo.gift.discover.disadapter.DiscoverLVAdapter;
import com.example.dllo.gift.discover.disbean.ListBean;
import com.example.dllo.gift.nettools.NetListener;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/3.
 */
public class DetailsCategoryRaiderActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private TextView tvTitleDetails;
    private ListView listViewDetails;
    private ListBean listBean;
    private DiscoverLVAdapter lvAdapter;
    private NetTools netTools;
    private ImageView menuSort;
    private Intent detailsIntent;

    @Override
    public void initActivity() {
        setContentView(R.layout.activity_details_category_raider_listview);
        findViewById(R.id.back_details_category_raider_title).setOnClickListener(this);
        menuSort = (ImageView) findViewById(R.id.menu_sort_details_category_raider_listview);
        menuSort.setOnClickListener(this);
        tvTitleDetails = (TextView) findViewById(R.id.tv_title_details_category_raider_listview);
        listViewDetails = (ListView) findViewById(R.id.listview_details_category_raider_listview);
        lvAdapter = new DiscoverLVAdapter(this);
        listViewDetails.setAdapter(lvAdapter);
        listViewDetails.setOnItemClickListener(this);
        //接收页面传来的数据
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String titleName = intent.getStringExtra("titleName");
        tvTitleDetails.setText(titleName);

        netTools = new NetTools();
        netTools.getNormalData(URLValues.CATEGORY_RAIDER_DETAILS_BEFORE + id + URLValues.CATEGORY_RAIDER_DETAILS_AFTER, new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                listBean = gson.fromJson(result, ListBean.class);
                lvAdapter.setDatas(listBean);
                //循环遍历创建 id集合 用于跳转传值
                ArrayList<String> idArray = new ArrayList<>();
                for (ListBean.DataBean.ItemsBean b :
                        listBean.getData().getItems()) {
                    idArray.add(String.valueOf(b.getId()));
                }
                //跳转传值
                detailsIntent = new Intent(DetailsCategoryRaiderActivity.this, DetailsListViewActivtiy.class);
                detailsIntent.putStringArrayListExtra("idArray", idArray);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.back_details_category_raider_title:
                finish();
                break;
            case R.id.menu_sort_details_category_raider_listview:

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        detailsIntent.putExtra("position", position);
        startActivity(detailsIntent);
    }
}
