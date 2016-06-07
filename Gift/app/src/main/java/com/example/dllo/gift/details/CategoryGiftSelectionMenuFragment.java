package com.example.dllo.gift.details;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.details.detailsadapter.CategoryGiftSelectionMenuAdapter;
import com.example.dllo.gift.details.detailsbean.CategoryGiftSelectionMenuBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dllo on 16/6/7.
 */
public class CategoryGiftSelectionMenuFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private GridView gridView;
    private ImageView dismiss;
    private MenuFragmentOnClickListener listener;
    private CategoryGiftSelectionMenuAdapter adapter;

    public  void setListener(MenuFragmentOnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_category_gift_selection;
    }

    @Override
    public void initView(View view) {
        gridView = (GridView) view.findViewById(R.id.gridview_menu_gift_selection);
        dismiss = (ImageView) view.findViewById(R.id.iv_menu_gift_selection);
        dismiss.setOnClickListener(this);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        adapter = new CategoryGiftSelectionMenuAdapter(context);
        gridView.setAdapter(adapter);
    }
    @Subscribe
    public void getMenuBean(CategoryGiftSelectionMenuBean.DataBean.FiltersBean bean){
            adapter.setMenuBean(bean);

    }

    @Override
    public void onClick(View v) {
        listener.onClickListener();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    interface MenuFragmentOnClickListener {
        void onClickListener();
    }
}
