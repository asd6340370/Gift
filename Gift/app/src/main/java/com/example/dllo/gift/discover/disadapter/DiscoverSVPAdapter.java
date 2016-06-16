package com.example.dllo.gift.discover.disadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dllo.gift.R;
import com.example.dllo.gift.details.DetailsSpecialActivity;
import com.example.dllo.gift.discover.disbean.SpecialBannerBean;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dllo on 16/5/21.
 */
public class DiscoverSVPAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {
    private SpecialBannerBean datas;
    private Context context;
    private ImageView ivShow;
    private LinearLayout layout;
    private ViewPager vpDiscoverSelection;

    public void setVpDiscoverSelection(ViewPager vpDiscoverSelection) {
        this.vpDiscoverSelection = vpDiscoverSelection;
        notifyDataSetChanged();
    }

    public void setLayout(LinearLayout layout) {
        this.layout = layout;
        notifyDataSetChanged();
    }

    public DiscoverSVPAdapter(Context context) {
        this.context = context;
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void getBanner(SpecialBannerBean specialBannerBean) {
        setDatas(specialBannerBean);
        /**
         * 小圆点
         */
        layout.removeAllViews();
        for (int i = 0; i < datas.getData().getBanners().size(); i++) {
            CheckBox checkBox = new CheckBox(context);
            Drawable drawable = context.getResources().getDrawable(R.drawable.selector_checkbox_banner);
            checkBox.setBackground(drawable);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(18, 18);
            params.setMargins(8, 0, 8, 0);
            params.weight = 1;
            layout.addView(checkBox, params);
        }
        ((CheckBox) layout.getChildAt(0)).setChecked(true);


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
    public Object instantiateItem(ViewGroup container, final int position) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_vp_discover, container, false);
//        container.addView(view);
//        ivShow = (ImageView) view.findViewById(R.id.iv_vp_discover);
        ImageView imageView = new ImageView(context);
        String url = datas.getData().getBanners().get(position % datas.getData().getBanners().size()).getImage_url();
        Picasso.with(context).load(url).centerCrop().fit().into(imageView);

        //当图片少的时候,不会触发destroyItem
        //这个时候去向container中addView会报错
        //手动捕获这个异常,
        try {
            container.addView(imageView);
        } catch (IllegalStateException e) {
            //先从container中移除ImageView
            container.removeView(imageView);
            //再次添加
            container.addView(imageView);
        }
        vpDiscoverSelection.addOnPageChangeListener(this);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsSpecialActivity.class);
                intent.putExtra("urlId",String.valueOf(datas.getData().getBanners().get(position).getTarget_id()));
                context.startActivity(intent);
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if(container.getChildAt(position % datas.getData().getBanners().size()) == object){
            container.removeViewAt(position % datas.getData().getBanners().size());
        }



    }


    public void unregister() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int selectNum = position % datas.getData().getBanners().size();
        for (int i = 0; i < datas.getData().getBanners().size(); i++) {
            if (selectNum == i) {
                ((CheckBox) layout.getChildAt(i)).setChecked(true);
            } else {
                ((CheckBox) layout.getChildAt(i)).setChecked(false);

            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
