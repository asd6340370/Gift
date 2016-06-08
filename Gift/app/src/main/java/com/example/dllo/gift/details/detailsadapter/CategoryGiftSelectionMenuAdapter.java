package com.example.dllo.gift.details.detailsadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.details.detailsbean.CategoryGiftSelectionMenuBean;

/**
 * Created by dllo on 16/6/7.
 */
public class CategoryGiftSelectionMenuAdapter extends BaseAdapter {
    private Context context ;
    private CategoryGiftSelectionMenuBean.DataBean.FiltersBean menuBean;
    private int mCheckedPosition = 0;

    public void setmCheckedPosition(int mCheckedPosition) {
        this.mCheckedPosition = mCheckedPosition;
        notifyDataSetChanged();
    }

    public void setMenuBean(CategoryGiftSelectionMenuBean.DataBean.FiltersBean menuBean) {
        this.menuBean = menuBean;
        notifyDataSetChanged();
    }

    public CategoryGiftSelectionMenuAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return menuBean == null ? 0 :menuBean.getChannels().size();
    }

    @Override
    public Object getItem(int position) {
        return menuBean == null ? null :menuBean.getChannels().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder ;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview_category_gift_selection_menu,null);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) convertView .getTag();
        }

        if (mCheckedPosition == position){
            myViewHolder.showName.setSelected(true);
        }else {
            myViewHolder.showName.setSelected(false);
        }

        myViewHolder.showName.setText(menuBean.getChannels().get(position).getName());
        return convertView;
    }

    class MyViewHolder{

        private final TextView showName;

        public MyViewHolder (View view){
            showName = (TextView) view.findViewById(R.id.tv_name_gift_selection_menu);
        }
    }
    interface mCheckedPosition{

    }
}
