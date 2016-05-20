package com.example.dllo.gift.hot;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/19.
 */
public class HotFragment extends BaseFragment {

    private GridView gridViewHot;

    @Override
    public int setLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    public void initView(View view) {
        gridViewHot = (GridView) view.findViewById(R.id.gridView_hot);
    }

    @Override
    public void initData() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            arrayList.add("gift"+ i );
        }

   HotAdapter adapter = new HotAdapter(context);
        gridViewHot.setAdapter(adapter);

        adapter.setDatas(arrayList);


    }
}
