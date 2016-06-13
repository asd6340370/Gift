package com.example.dllo.gift.details;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseActivity;
import com.example.dllo.gift.details.detailsadapter.DetailsSpecialAdapter;
import com.example.dllo.gift.details.detailsbean.DetailsSpecialBean;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;
import com.example.dllo.gift.tools.MyPopupWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/4.
 */
public class DetailsSpecialActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private TextView tvTitle;
    private MyPopupWindow popupWindow;
    private NetTools netTools;
    private DetailsSpecialBean specialBean;
    private DetailsSpecialAdapter specialAdapter;
    private ListView listViewDetailsSpecial;

    @Override
    public void initActivity() {
        setContentView(R.layout.activity_details_special);
        EventBus.getDefault().register(this);
        findViewById(R.id.back_details_special).setOnClickListener(this);
        findViewById(R.id.share_details_special).setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.tv_title_details_special);
        listViewDetailsSpecial =  (ListView)findViewById(R.id.listview_details_special);
        specialAdapter = new DetailsSpecialAdapter(this);
        listViewDetailsSpecial.setAdapter(specialAdapter);
        listViewDetailsSpecial.setOnItemClickListener(this);

        popupWindow = new MyPopupWindow(this, R.id.share_details_special);

        Intent intent = getIntent();
        String urlId = intent.getStringExtra("urlId");
//        Log.d("DetailsSpecialActivity", urlId);
        netTools = new NetTools();
        String url = URLValues.DETAILS_SPECIAL_BEFORE + urlId + URLValues.DETAILS_SPECIAL_AFTER;
        netTools.getDataForEventBus(url,DetailsSpecialBean.class);



    }
    @Subscribe
    public void getSpecialData(DetailsSpecialBean bean){
        specialAdapter.setSpecialBean(bean);
        tvTitle.setText(bean.getData().getTitle());
        specialBean = bean;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_details_special:
                finish();
                break;
            case R.id.share_details_special:
                popupWindow.showSharePopupWindow();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ArrayList<String> idArray = new ArrayList<>();
        for (DetailsSpecialBean.DataBean.PostsBean b :
                specialBean.getData().getPosts()) {
                idArray.add(String.valueOf(b.getId()));
        }
        Intent intent = new Intent(this,DetailsRaiderActivtiy.class);
        intent.putExtra("idArray",idArray);
        intent.putExtra("position",position);
        startActivity(intent);

    }
}
