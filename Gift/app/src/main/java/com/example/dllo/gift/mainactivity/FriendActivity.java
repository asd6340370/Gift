package com.example.dllo.gift.mainactivity;

import android.view.View;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseActivity;
import com.example.dllo.gift.tools.MyPopupWindow;

/**
 * Created by ZHI on 2016/6/11.
 */
public class FriendActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public void initActivity() {
        setContentView(R.layout.activity_friend);
        findViewById(R.id.back_friend_activity).setOnClickListener(this);
        findViewById(R.id.share_friend_activity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_friend_activity:
                finish();
                break;
            case R.id.share_friend_activity:
                MyPopupWindow myPopupWindow = new MyPopupWindow(this,R.id.share_friend_activity);
                myPopupWindow.showSharePopupWindow();
                break;
        }

    }
}
