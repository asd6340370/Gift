package com.example.dllo.gift.me;

import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.bmob.UserBmobBean;
import com.example.dllo.gift.details.DetailsPurchaseActivity;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;


/**
 * Created by dllo on 16/5/23.
 */
public class MeGiftFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private GridView gridViewMeGift;
    private MeGiftGirdViewAdapter gridViewAdapter;
    private List<UserBmobBean> beans;
    private UserBmobBean bmobBean;

    @Override
    public int setLayout() {
        return R.layout.fragment_me_gift;
    }

    @Override
    public void initView(View view) {
        gridViewMeGift = (GridView) view.findViewById(R.id.gridview_me_gift);
        gridViewMeGift.setOnItemClickListener(this);

    }

    @Override
    public void initData() {
        gridViewAdapter = new MeGiftGirdViewAdapter(context);
        gridViewMeGift.setAdapter(gridViewAdapter);
        BmobQueryDataAll();
    }

    @Override
    public void onResume() {
        super.onResume();
        BmobQueryDataAll();
    }

    //查询数据
    private void BmobQueryDataAll() {
        if (BmobUser.getCurrentUser(context) != null) {
            BmobQuery<UserBmobBean> query = new BmobQuery<>();
            query.addWhereEqualTo("key", "gift");
            query.addWhereEqualTo("userName", BmobUser.getCurrentUser(context).getUsername());
            query.setLimit(50);
            query.findObjects(context, new FindListener<UserBmobBean>() {
                @Override
                public void onSuccess(List<UserBmobBean> list) {
                    gridViewAdapter.setDatas(list);
                    beans = list;
                }

                @Override
                public void onError(int i, String s) {
                }
            });
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //点击跳转到购买详情页面
        bmobBean = new UserBmobBean();
        bmobBean = beans.get(position);
        String purchaseUrl = bmobBean.getPurchaseUrl();
        String urlId = String.valueOf(bmobBean.getId());
        String titleName = bmobBean.getTitleName();
        Intent intent = new Intent(context, DetailsPurchaseActivity.class);
//        intent.putExtra("buy",dataItem);
        intent.putExtra("bmobBean", (Parcelable) bmobBean);
        intent.putExtra("purchaseUrl", purchaseUrl);
        intent.putExtra("urlId", urlId);
        intent.putExtra("titleName", titleName);
        startActivity(intent);
    }
}
