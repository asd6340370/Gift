package com.example.dllo.gift.welcome;

import android.content.Intent;
import android.os.CountDownTimer;

import com.example.dllo.gift.MainActivity;
import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseActivity;

/**
 * Created by dllo on 16/5/19.
 */
public class WelcomeActivity extends BaseActivity {


    @Override
    public void initActivity() {
        setContentView(R.layout.activity_welcome);

         //CountDownTimer 倒计时
       new CountDownTimer(4000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }.start();

    }
}
