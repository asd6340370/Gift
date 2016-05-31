package com.example.dllo.gift.comments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.dllo.gift.R;
import com.example.dllo.gift.nettools.NetListener;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dllo on 16/5/31.
 */
public class CommentsActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listViewTopCommentsDetails, listViewBottomCommentsDetails;
    private NetTools netTools;
    private CommentsBean commentsTopBean,commentsBottomBean;
    private CommentsAdapter adapterTop, adapterBottom;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        EventBus.getDefault().register(this);
        findViewById(R.id.comments_hot_more_comments).setOnClickListener(this);
        findViewById(R.id.back_comments_title_details).setOnClickListener(this);
        findViewById(R.id.tv_send_message_comments_details).setOnClickListener(this);
        listViewTopCommentsDetails = (ListView) findViewById(R.id.listview_top_comments_details);
        listViewBottomCommentsDetails = (ListView) findViewById(R.id.listview_bottom_comments_details);
        adapterTop = new CommentsAdapter(this);
        listViewTopCommentsDetails.setAdapter(adapterTop);
        adapterBottom = new CommentsAdapter(this);
        listViewBottomCommentsDetails.setAdapter(adapterBottom);





        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        netTools = new NetTools();
        //上部分数据
        netTools.getDataForEventBus(URLValues.COMMENTS_BEFORE + id + URLValues.COMMENTS_HOT_AFTER + URLValues.COMMENTS_HOT_TOP,
                commentsTopBean, CommentsBean.class);
        //下部分数据
        String url = URLValues.COMMENTS_BEFORE + id + URLValues.COMMENTS_AFTER;
        netTools.getNormalList(url,new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                commentsBottomBean = gson.fromJson(result,CommentsBean.class);
                adapterBottom.setCommentsBean(commentsBottomBean);
            }
            @Override
            public void onFailed(VolleyError error) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_comments_title_details:
                finish();
                break;
            case R.id.comments_hot_more_comments:
                Intent intent = new Intent(this,CommentsHotMoreActivity.class);
                intent.putExtra("url",id);
                startActivity(intent);
                break;
            case R.id.tv_send_message_comments_details:
                //TODO  需要添加popupWindow 事件
                break;
        }
    }

    @Subscribe
    public void getCommentDatas(CommentsBean bean) {
        adapterTop.setCommentsBean(bean);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
