package com.example.dllo.gift.comments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.gift.R;
import com.example.dllo.gift.nettools.NetListener;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;
import com.example.dllo.gift.tools.MyPopupWindow;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dllo on 16/5/31.
 */
public class CommentsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listViewTopCommentsDetails, listViewBottomCommentsDetails;
    private NetTools netTools;
    private CommentsBean commentsTopBean,commentsBottomBean;
    private CommentsAdapter adapterTop, adapterBottom;
    private String id;
    private MyPopupWindow popupWindow;
    private LinearLayout commentsAll;
    private Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        EventBus.getDefault().register(this);

        findViewById(R.id.back_comments_title_details).setOnClickListener(this);
        findViewById(R.id.tv_send_message_comments_details).setOnClickListener(this);


        View view = LayoutInflater.from(this).inflate(R.layout.header_comments,null);
        commentsAll = (LinearLayout) view.findViewById(R.id.comments_hot_more_comments);
        commentsAll.setOnClickListener(this);
        listViewTopCommentsDetails = (ListView)view.findViewById(R.id.listview_top_comments_details);

        listViewBottomCommentsDetails = (ListView) findViewById(R.id.listview_bottom_comments_details);
        listViewBottomCommentsDetails.addHeaderView(view);

        adapterTop = new CommentsAdapter(this);
        listViewTopCommentsDetails.setAdapter(adapterTop);
        adapterBottom = new CommentsAdapter(this);
        listViewBottomCommentsDetails.setAdapter(adapterBottom);
        listViewBottomCommentsDetails.setOnItemClickListener(this);


        gson = new Gson();


        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        netTools = new NetTools();
        //上部分数据
        String urlTop = URLValues.COMMENTS_BEFORE + id + URLValues.COMMENTS_HOT_AFTER + URLValues.COMMENTS_HOT_TOP;
        netTools.getNormalData(urlTop, new NetListener() {
            @Override
            public void onSuccessed(String result) {
                commentsTopBean = gson.fromJson(result,CommentsBean.class);
                adapterTop.setCommentsBean(commentsTopBean);
                if (commentsTopBean.getData().getComments().size() == 0){
                    commentsAll.setVisibility(View.GONE);
                }else {
//                    commentsAll.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailed(VolleyError error) {

            }
        });

        //下部分数据
        String urlBottom = URLValues.COMMENTS_BEFORE + id + URLValues.COMMENTS_AFTER;
        netTools.getNormalData(urlBottom,new NetListener() {
            @Override
            public void onSuccessed(String result) {
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
                 popupWindow = new MyPopupWindow(this,R.id.tv_content_comments);
                popupWindow.showCommentsSendMessagePopupWindow();

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //获得正确行布局位置
        int index = (int) parent.getAdapter().getItemId(position);

    }
}
