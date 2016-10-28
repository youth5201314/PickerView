package com.youth.picker;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.youth.picker.adapter.DataAdapter;
import com.youth.picker.listener.OnPickerClickListener;

import java.util.HashMap;
import java.util.Map;


public class PickerView extends PopupWindow implements View.OnClickListener {
    private RadioButton mTextFirst, mTextSecond, mTextThird;
    private ListView pickerList;
    private View view;
    private int index = 1;
    private String[] currData;
    private DataAdapter adapter;
    private Activity context;
    private OnPickerClickListener listener;
    private String[] mFirstDatas;
    private Map<String, String[]> mSecondDatas = new HashMap<String, String[]>();
    private Map<String, String[]> mThirdDatas = new HashMap<String, String[]>();
    private String firstText;
    private String secondText;
    private String thirdText;
    public PickerView(Activity context, String[] mFirstDatas, Map<String, String[]> mSecondDatas, Map<String, String[]> mThirdDatas) {
        super(context);
        this.context = context;
        this.mFirstDatas = mFirstDatas;
        this.mSecondDatas = mSecondDatas;
        this.mThirdDatas = mThirdDatas;
        initPicker();
        initView();
    }
    public void setInitSelectText(String firstText,String secondText,String thirdText){
        this.firstText=firstText;
        this.secondText=secondText;
        this.thirdText=thirdText;
    }
    public void setOnPickerClickListener(OnPickerClickListener listener) {
        this.listener = listener;
    }

    private void initPicker() {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.picker_view, null);
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(400);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.AnimBottom);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                lp.alpha = 1f;
                context.getWindow().setAttributes(lp);
            }
        });
    }

    private void initView() {
        mTextFirst = (RadioButton) view.findViewById(R.id.mTextFirst);
        mTextSecond = (RadioButton) view.findViewById(R.id.mTextSecond);
        mTextThird = (RadioButton) view.findViewById(R.id.mTextThird);
        pickerList = (ListView) view.findViewById(R.id.pickerList);
        mTextFirst.setOnClickListener(this);
        mTextSecond.setOnClickListener(this);
        mTextThird.setOnClickListener(this);
    }

    public void show(View view) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = 0.7f;
        context.getWindow().setAttributes(lp);
        initData();
        showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void initData() {
        currData = mFirstDatas;
        adapter = new DataAdapter(context, currData);
        pickerList.setAdapter(adapter);
        if (!TextUtils.isEmpty(firstText)
                &&!TextUtils.isEmpty(secondText)
                &&!TextUtils.isEmpty(thirdText)){
            mTextFirst.setText(firstText);
            mTextSecond.setText(secondText);
            mTextThird.setText(thirdText);
            mTextFirst.setChecked(true);
        }
        pickerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (index == 1) {
                    firstText=currData[position];
                    mTextFirst.setText(firstText);
                    currData = mSecondDatas.get(firstText);
                    adapter.setList(currData);
                    setChecked(index);
                    index = 2;
                } else if (index == 2) {
                    secondText = currData[position];
                    mTextSecond.setText(secondText);
                    currData = mThirdDatas.get(secondText);
                    adapter.setList(currData);
                    setChecked(index);
                    index = 3;
                } else if (index == 3) {
                    thirdText = currData[position];
                    mTextThird.setText(currData[position]);
                    setChecked(index);
                    if (listener!=null){
                        listener.OnPickerClick(firstText,secondText,thirdText);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.mTextFirst) {
            currData = mFirstDatas;
            adapter.setList(currData);
            index = 1;
        } else if (id == R.id.mTextSecond) {
            currData = mSecondDatas.get(mTextFirst.getText().toString());
            adapter.setList(currData);
            index = 2;
        } else if (id == R.id.mTextThird) {
            currData = mThirdDatas.get(mTextSecond.getText().toString());
            adapter.setList(currData);
            index = 3;
        }
    }

    private void setChecked(int i){
        switch (i){
            case 1:
                mTextFirst.setChecked(true);
                mTextSecond.setChecked(false);
                mTextThird.setChecked(false);
                break;
            case 2:
                mTextFirst.setChecked(false);
                mTextSecond.setChecked(true);
                mTextThird.setChecked(false);
                break;
            case 3:
                mTextFirst.setChecked(false);
                mTextSecond.setChecked(false);
                mTextThird.setChecked(true);
                break;
        }
    }
   
}
