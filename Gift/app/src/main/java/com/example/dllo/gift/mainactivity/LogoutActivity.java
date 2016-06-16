package com.example.dllo.gift.mainactivity;

import android.view.View;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseActivity;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/6/12.
 */
public class LogoutActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public void initActivity() {
        setContentView(R.layout.activity_logout);
        findViewById(R.id.back_logout).setOnClickListener(this);
        findViewById(R.id.exit_logout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_logout:
                finish();
                break;
            case R.id.exit_logout:
                BmobUser.logOut(this);
                //不用发广播了
//                Intent intent = new Intent("com.example.dllo.gift.LOGOUT");
//                sendBroadcast(intent);
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
