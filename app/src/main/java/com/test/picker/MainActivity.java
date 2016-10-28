package com.test.picker;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.youth.picker.PickerView;
import com.youth.picker.listener.OnPickerClickListener;

public class MainActivity extends BaseActivity {
    private Button show_city_selecter;
    PickerView pickerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initProvinceDatas();
        show_city_selecter= (Button) findViewById(R.id.show_city_selecter);
        /**
         * 实例化选择器
         * @param context activity
         * @param mFirstDatas 第一级数据数组  String[]
         * @param mSecondDatas 第二级数据map  Map<String（第一级数据）, String[]（第二级数组）>
         * @param mThirdDatas 第三级数据map   Map<String（第二级数据）, String[]（第三级数组）>
         */
        pickerView=new PickerView(this,mProvinceDatas,mCitisDatasMap,mDistrictDatasMap);
        //设置初始化默认显示的三级菜单
        pickerView.setInitSelectText("河北省","石家庄市","平山县");
        show_city_selecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示选择器
                pickerView.show(show_city_selecter);
            }
        });
        //选择器完成三级选择后点击回调
        pickerView.setOnPickerClickListener(new OnPickerClickListener() {
            @Override
            public void OnPickerClick(String firstText, String secondText, String thirdText) {
                Log.e("----","text:"+firstText+secondText+thirdText);
                pickerView.dismiss();//关闭选择器
            }
        });
    }


}
