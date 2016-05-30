package com.example.dllo.gift;

import android.view.View;
import android.widget.ImageView;

import com.example.dllo.gift.base.BaseActivity;

/**
 * Created by dllo on 16/5/30.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {




    @Override
    public void initActivity() {
        setContentView(R.layout.activity_login);
        findViewById(R.id.close_login).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
