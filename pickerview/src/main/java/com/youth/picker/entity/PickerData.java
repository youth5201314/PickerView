package com.youth.picker.entity;


import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class PickerData {
    private String[] mFirstDatas;
    private Map<String, String[]> mSecondDatas = new HashMap<String, String[]>();
    private Map<String, String[]> mThirdDatas = new HashMap<String, String[]>();
    private Map<String, String[]> mFourthDatas = new HashMap<String, String[]>();
    private String firstText="";
    private String secondText="";
    private String thirdText="";
    private String fourthText="";
    private String pickerTitleName="";
    private int height=0;

    /**
     * 获取当前的列表
     * @param index 当前层级
     * @param currText 当前选中的文字key
     * @return 返回当前的数据数组
     */
    public String[] getCurrDatas(int index,String currText) {
        String[] curr = new String[]{};
        switch (index){
            case 1:
                curr=mFirstDatas;
                break;
            case 2:
                curr=mSecondDatas.get(currText);
                break;
            case 3:
                curr=mThirdDatas.get(currText);
                break;
            case 4:
                curr=mFourthDatas.get(currText);
                break;
        }
        return curr;
    }

    public void setInitSelectText(String firstText) {
        this.firstText = firstText;
    }
    public void setInitSelectText(String firstText, String secondText) {
        this.firstText = firstText;
        this.secondText = secondText;
    }
    public void setInitSelectText(String firstText, String secondText, String thirdText) {
        this.firstText = firstText;
        this.secondText = secondText;
        this.thirdText = thirdText;
    }
    public void setInitSelectText(String firstText, String secondText, String thirdText, String fourthText) {
        this.firstText = firstText;
        this.secondText = secondText;
        this.thirdText = thirdText;
        this.fourthText = fourthText;
    }
    public void clearSelectText(int index) {
        Log.i("--","index:"+index);
        switch (index){
            case 1:
                secondText="";
                thirdText="";
                fourthText="";
                break;
            case 2:
                thirdText="";
                fourthText="";
                break;
            case 3:
                fourthText="";
                break;
        }
    }

    public String getSelectText() {
        return firstText+secondText+thirdText+fourthText;
    }


    public String getPickerTitleName() {
        return pickerTitleName;
    }

    public void setPickerTitleName(String pickerTitleName) {
        this.pickerTitleName = pickerTitleName;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String[] getFirstDatas() {
        return mFirstDatas;
    }

    public void setFirstDatas(String[] mFirstDatas) {
        this.mFirstDatas = mFirstDatas;
    }

    public Map<String, String[]> getSecondDatas() {
        return mSecondDatas;
    }

    public void setSecondDatas(Map<String, String[]> mSecondDatas) {
        this.mSecondDatas = mSecondDatas;
    }

    public Map<String, String[]> getThirdDatas() {
        return mThirdDatas;
    }

    public void setThirdDatas(Map<String, String[]> mThirdDatas) {
        this.mThirdDatas = mThirdDatas;
    }

    public Map<String, String[]> getFourthDatas() {
        return mFourthDatas;
    }

    public void setFourthDatas(Map<String, String[]> mFourthDatas) {
        this.mFourthDatas = mFourthDatas;
    }

    public String getFirstText() {
        return firstText;
    }

    public void setFirstText(String firstText) {
        this.firstText = firstText;
    }

    public String getSecondText() {
        return secondText;
    }

    public void setSecondText(String secondText) {
        this.secondText = secondText;
    }

    public String getThirdText() {
        return thirdText;
    }

    public void setThirdText(String thirdText) {
        this.thirdText = thirdText;
    }

    public String getFourthText() {
        return fourthText;
    }

    public void setFourthText(String fourthText) {
        this.fourthText = fourthText;
    }
}
