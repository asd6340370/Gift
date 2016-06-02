package com.example.dllo.gift.tools;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.dllo.gift.R;

/**
 * Created by dllo on 16/5/31.
 */
public class MyPopupWindow implements View.OnClickListener {


    private PopupWindow popupWindow;

    private Activity activity;
    private int id;


    public MyPopupWindow( Activity activity, int id) {
        this.id = id;
        this.activity = activity;
        popupWindow = new PopupWindow(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT) {
            //构造方法中重写dismiss方法
            @Override
            public void dismiss() {
                super.dismiss();
                // 重写dismiss方法 改回父容器背景透明度
                WindowManager.LayoutParams params1 = MyPopupWindow.this.activity.getWindow().getAttributes();
                params1.alpha = 1;
                MyPopupWindow.this.activity.getWindow().setAttributes(params1);

            }

        };
    }
    public void showCommentsSendMessagePopupWindow(){

       View view  = LayoutInflater.from(activity).inflate(R.layout.item_comments_popupwindow,null);
        view.findViewById(R.id.tv_cancel_comments_popupwindow).setOnClickListener(this);
        view.findViewById(R.id.tv_send_comments_popupwindow).setOnClickListener(this);
//        view.findViewById(R.id.et_message_cotent_popupwindow);
        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        ColorDrawable drawable = new ColorDrawable(0XFFFFFFFF);
        popupWindow.setBackgroundDrawable(drawable);
        popupWindow.showAtLocation(activity.findViewById(id),Gravity.BOTTOM,0,0);

        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = 0.7f;
        activity.getWindow().setAttributes(params);

    }

    public void showSharePopupWindow() {
        //设置焦点 实现当点击window外面消失
        popupWindow.setFocusable(true);
        //设置背景 实现当点击window外面消失
//        ColorDrawable drawable = new ColorDrawable(0xb0000000);//半透明
        ColorDrawable drawable = new ColorDrawable(0XFFFFFFFF);//白色
        popupWindow.setBackgroundDrawable(drawable);
        View view = LayoutInflater.from(activity).inflate(R.layout.item_share_popupwindow, null);
        view.findViewById(R.id.share_cancle_popup).setOnClickListener(this);
        view.findViewById(R.id.share_wechat_timeline_popup).setOnClickListener(this);
        view.findViewById(R.id.share_wechat_popup).setOnClickListener(this);
        view.findViewById(R.id.share_sina_weibo_popup).setOnClickListener(this);
        view.findViewById(R.id.share_qq_zone_popup).setOnClickListener(this);
        view.findViewById(R.id.share_qq_popup).setOnClickListener(this);
        view.findViewById(R.id.share_hyperlink_popup).setOnClickListener(this);

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
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//             @Override
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

            case R.id.tv_cancel_comments_popupwindow:
                popupWindow.dismiss();
                break;
            case R.id.tv_send_comments_popupwindow:
                popupWindow.dismiss();
                break;
        }
    }
}



