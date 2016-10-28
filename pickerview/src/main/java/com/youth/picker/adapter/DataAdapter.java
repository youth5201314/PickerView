package com.youth.picker.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class DataAdapter extends BaseAdapter {
    private String[] mDatas;
    private Context context;
    public DataAdapter(Context context, String[] mDatas) {
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
        textView.setTextSize(16f);
        textView.setTextColor(Color.GRAY);
        textView.setPadding(10,10,10,10);
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
