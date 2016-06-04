package com.example.dllo.gift.discover.disadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.discover.disbean.SpecialListHeaderBean;
import com.example.dllo.gift.tools.RecyclerOnClickListener;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dllo on 16/5/21.
 */
public class DiscoverSRVAdapter extends RecyclerView.Adapter<DiscoverSRVAdapter.MyViewHolder> {
    private SpecialListHeaderBean datas;
    private Context context;
    private RecyclerOnClickListener recyclerOnClickListener;

    public void setRecyclerOnClickListener(RecyclerOnClickListener recyclerOnClickListener) {
        this.recyclerOnClickListener = recyclerOnClickListener;
    }

    public DiscoverSRVAdapter(Context context) {
        this.context = context;
        EventBus.getDefault().register(this);
    }
    @Subscribe
    public void getSpecialList (SpecialListHeaderBean listBean){
        setDatas(listBean);
    }

    public SpecialListHeaderBean getDatas() {
        return datas;
    }

    public void setDatas(SpecialListHeaderBean datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_srv_discover_selection,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
    String url = datas.getData().getSecondary_banners().get(position).getImage_url();
        Picasso.with(context).load(url).centerCrop().fit().into(holder.ivShow);
        holder.ivShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerOnClickListener.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 :datas.getData().getSecondary_banners().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        private  ImageView ivShow;


        public MyViewHolder(View itemView) {
            super(itemView);

            ivShow = (ImageView) itemView.findViewById(R.id.iv_srv_discover);

        }
    }

    public void unregister(){
        EventBus.getDefault().unregister(this);
    }
}
