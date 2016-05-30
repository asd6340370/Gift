package com.example.dllo.gift.me;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;

/**
 * Created by dllo on 16/5/19.
 */
public class MeFragment extends BaseFragment {

    private ImageView ivMessage;

    @Override
    public int setLayout() {
        return R.layout.fragment_me;
    }

    @Override
    public void initView(View view) {
        ivMessage = (ImageView) view.findViewById(R.id.iv_message_me);
        ivMessage.setFocusable(true);
        ivMessage.setFocusableInTouchMode(true);
        ivMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "yoyo", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void initData() {

    }
}
