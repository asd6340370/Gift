package com.example.dllo.gift.discover;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.android.volley.VolleyError;
import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.discover.disbean.DiscoverTitlesBean;
import com.example.dllo.gift.nettools.NetListener;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/14.
 */
public class DiscoverMenuFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private NetTools netTools;
    private DiscoverTitlesBean titlesBean;
    private ArrayList<String> arrayList;
    private DiscoverMenuOnItemClick menuOnItemClick;

    public void setMenuOnItemClick(DiscoverMenuOnItemClick menuOnItemClick) {
        this.menuOnItemClick = menuOnItemClick;
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_discover_menu;
    }

    @Override
    public void initView(View view) {
        gridView = (GridView) view.findViewById(R.id.gridView_discover_menu);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        arrayList = new ArrayList<>();

        netTools = new NetTools();
        netTools.getNormalData(URLValues.DISCOVER_TABLAYOUT_TITLES, new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson= new Gson();
                titlesBean = gson.fromJson(result,DiscoverTitlesBean.class);
                for (int i = 0; i < titlesBean.getData().getChannels().size(); i++) {
                    arrayList.add(titlesBean.getData().getChannels().get(i).getName());
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1,arrayList);
                gridView.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        menuOnItemClick.onItemClick(position);
    }

    interface DiscoverMenuOnItemClick{
        void onItemClick(int position);
    }
}
