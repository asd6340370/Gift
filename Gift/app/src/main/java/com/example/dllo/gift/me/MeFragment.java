package com.example.dllo.gift.me;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.gift.LoginActivity;
import com.example.dllo.gift.LogoutActivity;
import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/5/19.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {

    private ImageView ivMessage;
    private TextView tvBuy;
    private FrameLayout frameLayout;
    private LinearLayout linearLayout;
    private ScrollView scrollView;
    private ImageView ivAvatarMe;
    private BmobUser bmobUser;
    private TextView userName;
    private TextView giftMe;
    private TextView raiderMe;
    private MeGiftFragment giftFragment;
    private MeRaiderFragment raiderFragment;
    private TextView tvColorGift ,tvColorRaider;

    @Override
    public int setLayout() {
        return R.layout.fragment_me;
    }

    @Override
    public void initView(View view) {
        tvColorGift = (TextView)view.findViewById(R.id.color_gift_me);
        tvColorRaider = (TextView) view.findViewById(R.id.color_raider_me);
        ivMessage = (ImageView) view.findViewById(R.id.iv_message_me);
        ivMessage.setOnClickListener(this);
        ivAvatarMe = (ImageView) view.findViewById(R.id.iv_avatar_me);
        userName = (TextView) view.findViewById(R.id.tv_account_me);
        view.findViewById(R.id.framelayout_me).setOnClickListener(this);
        giftMe = (TextView) view.findViewById(R.id.gift_me);
        giftMe.setOnClickListener(this);
        raiderMe = (TextView) view.findViewById(R.id.raider_me);
        raiderMe.setOnClickListener(this);


    }

    @Override
    public void initData() {
       giftFragment = new MeGiftFragment();
       raiderFragment = new MeRaiderFragment();

       bmobUser = BmobUser.getCurrentUser(context);
        if (bmobUser != null){
            userName.setText(BmobUser.getCurrentUser(context).getUsername());
        }

        getChildFragmentManager().beginTransaction().replace(R.id.framelayout_favorite_me,giftFragment).commit();

//        if (this.isHidden()){
//            Log.d("MeFragment", "显示");
//        }else {
//            Log.d("MeFragment", "不显示");
//        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_message_me:
                Toast.makeText(context, "yoyo", Toast.LENGTH_SHORT).show();

                break;
            case R.id.framelayout_me:
                if (bmobUser == null){
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }else {
                    Intent intent = new Intent(context, LogoutActivity.class);
                    context.startActivity(intent);
                }
                break;
            case R.id.gift_me:
                getChildFragmentManager().beginTransaction().replace(R.id.framelayout_favorite_me,giftFragment).commit();
                tvColorGift.setVisibility(View.VISIBLE);
                tvColorRaider.setVisibility(View.INVISIBLE);
                break;
            case R.id.raider_me:
                getChildFragmentManager().beginTransaction().replace(R.id.framelayout_favorite_me,raiderFragment).commit();
                tvColorRaider.setVisibility(View.VISIBLE);
                tvColorGift.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        bmobUser = BmobUser.getCurrentUser(context);
        if (bmobUser == null){
            userName.setText("未登录");
        }else {
            userName.setText(BmobUser.getCurrentUser(context).getUsername());
        }

    }
}
