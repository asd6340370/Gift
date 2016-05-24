package com.example.dllo.gift.discover.disadapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.gift.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/21.
 */
public class DiscoverSVPAdapter extends PagerAdapter {
    private ArrayList<Integer> datas;
    private Context context;
    private ImageView ivShow;

    public DiscoverSVPAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(ArrayList<Integer> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vp_discover,container,false);
        container.addView(view);
        ivShow = (ImageView) view.findViewById(R.id.iv_vp_discover);
        ivShow.setImageResource(datas.get(position % datas.size()));

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView();
    }
}
