package com.example.dllo.gift.comments;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseActivity;
import com.example.dllo.gift.nettools.NetListener;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;
import com.example.dllo.gift.tools.MyPopupWindow;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/5/31.
 */
public class CommentsHotMoreActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    private ListView listViewHotCommentsMore;
    private CommentsAdapter adapter;
    private String id;
    private CommentsBean commentsBean;
    private NetTools netTools;

    @Override
    public void initActivity() {
        setContentView(R.layout.activity_hot_comments_more);
        findViewById(R.id.back_hot_comments_title_details).setOnClickListener(this);
        listViewHotCommentsMore = (ListView) findViewById(R.id.listview_hot_comments_details);
        adapter = new CommentsAdapter(this);
        listViewHotCommentsMore.setAdapter(adapter);
        listViewHotCommentsMore.setOnItemClickListener(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("url");

        netTools = new NetTools();
        netTools.getNormalData(URLValues.COMMENTS_RAIDER_BEFORE + id +URLValues.COMMENTS_RAIDER_HOT_AFTER + URLValues.COMMENTS_RAIDER_HOT_OTHER,
                new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                commentsBean = gson.fromJson(result,CommentsBean.class);
                adapter.setCommentsBean(commentsBean);

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_hot_comments_title_details:
                finish();
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MyPopupWindow myPopupWindow = new MyPopupWindow(this,R.id.back_hot_comments_title_details);
        myPopupWindow.showCommentsSendMessagePopupWindow();
    }
}
