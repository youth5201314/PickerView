package com.test.picker;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/26.
 */

public class CityAdapter extends BaseAdapter {
    private String[] mDatas;
    private Context context;
    public CityAdapter(Context context,String[] mDatas) {
        this.context=context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.length;
    }

    @Override
    public Object getItem(int position) {
        return mDatas[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView=new TextView(context);
        textView.setText(mDatas[position]);
        return textView;
    }
    public void setList(String[] datas) {
        if (datas != null && datas.length>0) {
            mDatas=datas;
        }
        notifyDataSetChanged();
    }
}
