package com.example.dllo.gift.hot;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.android.volley.VolleyError;
import com.example.dllo.gift.DetailsActivity;
import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.net.NetListener;
import com.example.dllo.gift.net.NetTools;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/19.
 */
public class HotFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private GridView gridViewHot;

    @Override
    public int setLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    public void initView(View view) {
        gridViewHot = (GridView) view.findViewById(R.id.gridView_hot);
        gridViewHot.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
   final HotAdapter adapter = new HotAdapter(context);
        gridViewHot.setAdapter(adapter);

        NetTools netTools = new NetTools();
        netTools.getHotData(new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
               HotBean hotBean = gson.fromJson(result,HotBean.class);
                adapter.setDatas(hotBean);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });





    }
    //GridView 行布局item点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(context, DetailsActivity.class);
        startActivity(intent);

    }
}
