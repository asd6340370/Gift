package com.example.dllo.gift.me;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.tools.bmob.UserBmobBean;
import com.example.dllo.gift.details.DetailsRaiderActivtiy;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dllo on 16/5/23.
 */
public class MeRaiderFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView listViewMeRaider;
    private List<UserBmobBean> beans;
    private MeRaiderListViewAdapter listViewAdapter;
    private UserBmobBean bmobBean;

    @Override
    public int setLayout() {
        return R.layout.fragment_me_raider;
    }

    @Override
    public void initView(View view) {
        listViewMeRaider = (ListView) view.findViewById(R.id.listView_meRaider);
        listViewMeRaider.setOnItemClickListener(this);


    }

    @Override
    public void initData() {
         listViewAdapter = new MeRaiderListViewAdapter(context);
        listViewMeRaider.setAdapter(listViewAdapter);


        BmobQueryDataAll();

    }

    @Override
    public void onResume() {
        super.onResume();
        BmobQueryDataAll();
    }


    private void BmobQueryDataAll() {
        if (BmobUser.getCurrentUser(context) != null) {
            BmobQuery<UserBmobBean> query = new BmobQuery<>();
            query.addWhereEqualTo("key", "raider");
            query.addWhereEqualTo("userName", BmobUser.getCurrentUser(context).getUsername());
            query.setLimit(50);
            query.findObjects(context, new FindListener<UserBmobBean>() {
                @Override
                public void onSuccess(List<UserBmobBean> list) {
                    listViewAdapter.setDatas(list);
                    beans = list;
                }
                @Override
                public void onError(int i, String s) {
                }
            });
        }else {
            beans = null;
            listViewAdapter.setDatas(beans);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //点击跳转到购买详情页面
        String urlId = String.valueOf(beans.get(position).getId());
        Intent intent = new Intent(context, DetailsRaiderActivtiy.class);
        intent.putExtra("urlId",urlId);
        startActivity(intent);
    }
}
