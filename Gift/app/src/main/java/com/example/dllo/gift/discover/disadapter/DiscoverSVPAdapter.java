package com.example.dllo.gift.discover.disadapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.discover.disbean.SpecialBannerBean;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dllo on 16/5/21.
 */
public class DiscoverSVPAdapter extends PagerAdapter {
    private SpecialBannerBean datas;
    private Context context;
    private ImageView ivShow;

    public DiscoverSVPAdapter(Context context) {
        this.context = context;
        EventBus.getDefault().register(this);
    }
    @Subscribe
    public void getBanner (SpecialBannerBean specialBannerBean){
       setDatas(specialBannerBean);
    }

    public void setDatas(SpecialBannerBean datas) {
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
        String url = datas.getData().getBanners().get(position % datas.getData().getBanners().size()).getImage_url();
//        Log.d("DiscoverSVPAdapter", url);
        Picasso.with(context).load(url).centerCrop().fit().into(ivShow);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView();
    }

    public void unregister(){
        EventBus.getDefault().unregister(this);
    }
}
