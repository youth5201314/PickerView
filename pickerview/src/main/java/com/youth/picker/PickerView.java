package com.youth.picker;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.youth.picker.adapter.DataAdapter;
import com.youth.picker.entity.PickerData;
import com.youth.picker.listener.OnPickerClickListener;

import java.util.Map;


public class PickerView extends PopupWindow implements View.OnClickListener {
    private RadioButton mTextFirst, mTextSecond, mTextThird, mTextFourth;
    private RadioGroup groupSelect;
    private ListView pickerList;
    private TextView emptyView;
    private TextView pickerConfirm;
    private View view;
    private int index = 1;
    private String[] currData;
    private DataAdapter adapter;
    private Activity context;
    private OnPickerClickListener listener;
    private PickerData pickerData;
    private int height;

    public PickerView(Activity context, PickerData pickerData) {
        super(context);
        this.context = context;
        this.pickerData = pickerData;
        this.height = pickerData.getHeight();
        if (height == 0) {
            height = getScreenH(context) / 2;
        }
        initPicker();
        initView();
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
        this.setHeight(height);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.AnimBottom);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                mTextFirst.setChecked(true);
                index = 1;
                WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                lp.alpha = 1f;
                context.getWindow().setAttributes(lp);
            }
        });
    }

    private void initView() {
        pickerConfirm = (TextView) view.findViewById(R.id.pickerConfirm);
        groupSelect = (RadioGroup) view.findViewById(R.id.groupSelect);
        mTextFirst = (RadioButton) view.findViewById(R.id.mTextFirst);
        mTextSecond = (RadioButton) view.findViewById(R.id.mTextSecond);
        mTextThird = (RadioButton) view.findViewById(R.id.mTextThird);
        mTextFourth = (RadioButton) view.findViewById(R.id.mTextFourth);
        pickerList = (ListView) view.findViewById(R.id.pickerList);
        emptyView = (TextView) view.findViewById(R.id.empty_data_hints);
        pickerList.setEmptyView(view.findViewById(R.id.picker_list_empty_data));
        mTextFirst.setOnClickListener(this);
        mTextSecond.setOnClickListener(this);
        mTextThird.setOnClickListener(this);
        pickerConfirm.setOnClickListener(this);
    }

    public void show(View view) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = 0.7f;
        context.getWindow().setAttributes(lp);
        initData();
        showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void initData() {
        currData = pickerData.getCurrDatas(index, "");
        adapter = new DataAdapter(context, currData);
        pickerList.setAdapter(adapter);
        if (currData == null)
            emptyView.setVisibility(View.VISIBLE);
        else
            emptyView.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(pickerData.getFirstText())) {
            mTextFirst.setText(pickerData.getFirstText());
            if (!TextUtils.isEmpty(pickerData.getSecondText())) {
                mTextSecond.setText(pickerData.getSecondText());
                if (!TextUtils.isEmpty(pickerData.getThirdText())) {
                    mTextThird.setText(pickerData.getThirdText());
                    if (!TextUtils.isEmpty(pickerData.getFourthText()))
                        mTextFourth.setText(pickerData.getFourthText());
                }
            }
            mTextFirst.setChecked(true);
        }
        pickerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String currText = currData[position];
                pickerData.clearSelectText(index);
                mTextFirst.setText(pickerData.getFirstText());
                mTextSecond.setText(pickerData.getSecondText());
                mTextThird.setText(pickerData.getThirdText());
                if (index == 1) {
                    pickerData.setFirstText(currText);
                    mTextFirst.setText(currText);
                    groupSelect.check(mTextFirst.getId());
                    new UpdateData(currText,pickerData.getSecondDatas()).invoke();
                } else if (index == 2) {
                    pickerData.setSecondText(currText);
                    mTextSecond.setText(currText);
                    groupSelect.check(mTextSecond.getId());
                    new UpdateData(currText,pickerData.getSecondDatas()).invoke();
                } else if (index == 3) {
                    pickerData.setThirdText(currText);
                    mTextThird.setText(currText);
                    groupSelect.check(mTextThird.getId());
                    new UpdateData(currText,pickerData.getSecondDatas()).invoke();
                } else if (index == 4) {
                    pickerData.setFourthText(currText);
                    mTextFourth.setText(currText);
                    groupSelect.check(mTextFourth.getId());
                    if (listener != null) {
                        listener.OnPickerClick(pickerData);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.mTextFirst) {
            index = 1;
            currData = pickerData.getCurrDatas(index, "");
            adapter.setList(currData);
        } else if (id == R.id.mTextSecond) {
            index = 2;
            currData = pickerData.getCurrDatas(index, mTextFirst.getText().toString());
            adapter.setList(currData);
        } else if (id == R.id.mTextThird) {
            index = 3;
            currData = pickerData.getCurrDatas(index, mTextSecond.getText().toString());
            adapter.setList(currData);
        } else if (id == R.id.mTextFourth) {
            index = 4;
            currData = pickerData.getCurrDatas(index, mTextFourth.getText().toString());
            adapter.setList(currData);
        } else if (id == R.id.pickerConfirm) {
            if (listener != null) {
                dismiss();
                listener.OnPickerConfirmClick(pickerData);
            }
        }
    }


    private class UpdateData {
        private String text;
        private Map<String, String[]> data;
        public UpdateData(String text, Map<String, String[]> data) {
            this.text = text;
            this.data=data;
        }
        public void invoke() {
            if (!data.isEmpty()) {
                currData = pickerData.getCurrDatas(index+1, text);
                adapter.setList(currData);
                if (currData == null) {
                    if (listener != null) {
                        listener.OnPickerClick(pickerData);
                    }
                } else {
                    index ++;
                }
            } else {
                if (listener != null) {
                    listener.OnPickerClick(pickerData);
                }
            }
        }
    }


    public int getScreenW(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }


    public int getScreenH(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

}
