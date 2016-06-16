package com.example.dllo.gift.mainactivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/5/30.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private EditText etUserName;
    private EditText etPassWord;
    private TextView buttonLogin;
    private String userName, passWord;
    private TextView registerUser;
    private boolean isLogin = true;


    @Override
    public void initActivity() {
        setContentView(R.layout.activity_login);
        findViewById(R.id.close_login).setOnClickListener(this);
        etUserName = (EditText) findViewById(R.id.et_cellphone_login);
        etPassWord = (EditText) findViewById(R.id.et_password_login);
        buttonLogin = (TextView) findViewById(R.id.btn_login);
        buttonLogin.setOnClickListener(this);
        registerUser = (TextView) findViewById(R.id.tv_register_login);
        registerUser.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close_login:
            finish();
                break;
            case R.id.et_cellphone_login:

                break;
            case R.id.et_password_login:

                break;
            case R.id.btn_login:
                //登陆 通过Bmob实现
                userName =  etUserName.getText().toString();
                passWord = etPassWord.getText().toString();
//                Log.d("LoginActivity", userName + " " + passWord);
                if(isLogin){
                    BmobUser user = new BmobUser();
                    user.setUsername(userName);
                    user.setPassword(passWord);
                    user.login(this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(LoginActivity.this, "登陆失败:" + s, Toast.LENGTH_SHORT).show();
                        }
                    });

                }else {
                    BmobUser regsterUser = new BmobUser();
                    regsterUser.setUsername(etUserName.getText().toString());
                    regsterUser.setPassword(etPassWord.getText().toString());
                    regsterUser.signUp(this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(LoginActivity.this, "注册失败:" + s, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                break;
            case R.id.tv_register_login:
                if (isLogin) {
                    isLogin = false;
                    buttonLogin.setText("注册");
                    registerUser.setText("立即登陆");
                }else {
                    isLogin = true;
                    buttonLogin.setText("登陆");
                    registerUser.setText("注册新用户");
                }

                break;
        }
    }
}
