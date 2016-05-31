package com.example.dllo.gift.tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.dllo.gift.DiscoverListViewDetailsActivtiy;
import com.example.dllo.gift.R;

/**
 * Created by dllo on 16/5/31.
 */
public class SharePopupWindow implements View.OnClickListener {


    private View view;
    private PopupWindow popupWindow;

    private Activity activity;
    private int id;


    public SharePopupWindow(final Activity activity, int id) {
        this.id = id;
        this.activity = activity;
        popupWindow = new PopupWindow(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT) {
            //构造方法中重写dismiss方法
            @Override
            public void dismiss() {
                super.dismiss();
                // 重写dismiss方法 改回父容器背景透明度
                WindowManager.LayoutParams params1 = activity.getWindow().getAttributes();
                params1.alpha = 1;
                activity.getWindow().setAttributes(params1);

            }


        };
        view = LayoutInflater.from(activity).inflate(R.layout.share_popupwindow, null);
        view.findViewById(R.id.share_cancle_popup).setOnClickListener(this);
        view.findViewById(R.id.share_wechat_timeline_popup).setOnClickListener(this);
        view.findViewById(R.id.share_wechat_popup).setOnClickListener(this);
        view.findViewById(R.id.share_sina_weibo_popup).setOnClickListener(this);
        view.findViewById(R.id.share_qq_zone_popup).setOnClickListener(this);
        view.findViewById(R.id.share_qq_popup).setOnClickListener(this);
        view.findViewById(R.id.share_hyperlink_popup).setOnClickListener(this);
    }


    public void showPopupWindow() {
        //设置焦点 实现当点击window外面消失
        popupWindow.setFocusable(true);
        //设置背景 实现当点击window外面消失
//        ColorDrawable drawable = new ColorDrawable(0xb0000000);//半透明
        ColorDrawable drawable = new ColorDrawable(0XFFFFFFFF);//白色
        popupWindow.setBackgroundDrawable(drawable);
        //设置自定义view布局
        popupWindow.setContentView(view);
        //设置动画
        popupWindow.setAnimationStyle(R.style.myPopupWindow_anim_style);
        //在底部显示
        popupWindow.showAtLocation(activity.findViewById(id),
                Gravity.BOTTOM, 0, 0);
        //设置父容器背景透明度
        final WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = 0.7f;
        activity.getWindow().setAttributes(params);
//            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//                @Override
//                public void onDismiss() {
//                    WindowManager.LayoutParams params1 = activity.getWindow().getAttributes();
//                    params1.alpha = 1;
//                    activity.getWindow().setAttributes(params1);
//                }
//            });
    }

    public void dismissPopupWindow() {
        popupWindow.dismiss();
    }

    //popupWindow点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_wechat_timeline_popup:
                Toast.makeText(activity, "tu一下", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share_wechat_popup:
                Toast.makeText(activity, "tu一下", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share_sina_weibo_popup:
                Toast.makeText(activity, "tu一下", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share_qq_zone_popup:
                Toast.makeText(activity, "tu一下", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share_qq_popup:
                Toast.makeText(activity, "tu一下", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share_hyperlink_popup:
                Toast.makeText(activity, "tu一下", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share_cancle_popup:
                popupWindow.dismiss();
                break;
        }
    }
}



