package com.example.dllo.gift;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/6/13.
 */
public class UserInfo extends BmobUser {
    private static UserInfo ourInstance = new UserInfo();

    public static UserInfo getInstance() {

        return ourInstance;
    }

    private UserInfo() {
    }
}
