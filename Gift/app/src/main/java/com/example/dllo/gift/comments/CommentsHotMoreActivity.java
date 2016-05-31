package com.example.dllo.gift.comments;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseActivity;
import com.example.dllo.gift.nettools.NetListener;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/5/31.
 */
public class CommentsHotMoreActivity extends BaseActivity implements View.OnClickListener {


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

        Intent intent = getIntent();
        id = intent.getStringExtra("url");

        netTools = new NetTools();
        netTools.getNormalList(URLValues.COMMENTS_BEFORE + id +URLValues.COMMENTS_HOT_AFTER + URLValues.COMMENTS_HOT_OTHER ,
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
}
