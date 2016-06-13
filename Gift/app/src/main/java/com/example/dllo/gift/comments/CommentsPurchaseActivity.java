package com.example.dllo.gift.comments;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseActivity;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dllo on 16/6/6.
 */
public class CommentsPurchaseActivity extends BaseActivity implements View.OnClickListener {

    private ListView listViewCommentsPurchase;
    private EditText etSendMessage;
    private TextView sendMessage;
    private CommentsPurchaseAdapter purchaseAdapter;
    private NetTools netTools;
    private String urlId;

    @Override
    public void initActivity() {
        setContentView(R.layout.activity_comments_purchase);
        EventBus.getDefault().register(this);
        listViewCommentsPurchase = (ListView) findViewById(R.id.listview_bottom_comments_purchase_details);
        etSendMessage = (EditText) findViewById(R.id.et_send_message_comments_purchase_details);
        findViewById(R.id.back_comments_purchase_title_details).setOnClickListener(this);
        sendMessage = (TextView) findViewById(R.id.tv_send_message_comments_purchase_details);
        sendMessage.setOnClickListener(this);

        purchaseAdapter = new CommentsPurchaseAdapter(this);
        listViewCommentsPurchase.setAdapter(purchaseAdapter);

        Intent intent = getIntent();
        urlId = String.valueOf(intent.getStringExtra("urlId"));
//        Log.d("CommentsPurchaseActivit", urlId);
        netTools = new NetTools();
        String url = URLValues.COMMENTS_PURCHASE_BEFORE + urlId + URLValues.COMMENTS_PURCHASE_AFTER;
        netTools.getDataForEventBus(url, CommentsPurchaseBean.class);

    }

    @Override
    public void onClick(View v) {
    switch(v.getId()){
        case R.id.back_comments_purchase_title_details:
            finish();
            break;
        case R.id.tv_send_message_comments_purchase_details:

            break;

    }

    }

    @Subscribe
    public void getCommentsBeanData(CommentsPurchaseBean bean) {
        purchaseAdapter.setbeans(bean);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
