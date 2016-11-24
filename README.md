
# PickerView一种全新的选择器 *如果对你有帮助请star哦！*

[![Apache 2.0 License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0.html)

<br>
现在的绝大数app的地址选择器都是三级联动或者滚轮选择的形式，刚刚公司开发的项目弄了个新的交互，当然不仅仅可以用于省市区的选择，
只要是类似城市选择的层级形式都行，比如电商菜单分类啊等等。目前最多支持4级菜单。

`控件比较冷门，所以就没有封装的特别精细，如果不符合你们可以自行修改哈！我就提供一种思路`

##效果预览
![效果示例](http://oceh51kku.bkt.clouddn.com/PickerView.gif)


### 联系方式  <a target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=KBkYGhAfGhEYEB5oWVkGS0dF" style="text-decoration:none;"><img src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_11.png"/></a>
![效果示例](http://oceh51kku.bkt.clouddn.com/Android%E6%8A%80%E6%9C%AF%E4%BA%A4%E6%B5%81%E7%BE%A4%E4%BA%8C%E7%BB%B4%E7%A0%81.png)
* 如果遇到问题和建议欢迎在给我发送邮件或者加入qq群，希望让这个工程越来越完善。


##PickerData方法
|方法名|描述
|---|---|
|setFirstDatas(String[] mFirstDatas)| 第一级菜单数组
|setSecondDatas(Map<String , String[]> mSecondDatas)| 第二级菜单map，key对应上一级value
|setThirdDatas(Map<String , String[]> mSecondDatas)| 第三级菜单map，key对应上一级value
|setFourthDatas(Map<String , String[]> mSecondDatas)| 第四级菜单map，key对应上一级value
|setHeight(int height)| 设置选择器高度
|setPickerTitleName(String pickerTitleName)| 设置选择器标题
|setInitSelectText(...)| 设置默认显示的数据(参数顺序对应层级顺序，参数个数可以自己选择)
|getFirstText()| 获取第一级选择结果
|getSecondText()| 获取第二级选择结果
|getThirdText()| 获取第三级选择结果
|getFourthText()| 获取第四级选择结果
|getSelectText()| 获取完整选择的结果（拼接后结果）
|getCurrDatas(int index,String currText)| 通过当前key获取指定层级的values数组

##PickerView方法
|方法名|描述
|---|---|
|PickerView(Activity context, PickerData pickerData)| 构造方法传入上下文和封装的数据
|setOnPickerClickListener(OnPickerClickListener listener)| 设置点击事件
|show(View view)| 显示选择器
|dismiss()| 关闭选择器

##PickerView点击事件方法
|方法名|描述
|---|---|
|OnPickerClick(PickerData pickerData)| 数据展示列表点击事件，实时返回选择结果
|OnPickerConfirmClick(PickerData pickerData)| 点击确定按钮的回调事件，点击后自动关闭选择器，返回选择结果

##使用步骤 

#### Step 1.PickerView
Gradle 
```groovy
dependencies{
    compile 'com.youth.picker:pickerview:0.1.4@aar'
}
```
或者引用本地lib
```groovy
compile project(':PickerView')
```

#### Step 2.代码实现
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    //选择器数据实体类封装
    PickerData data=new PickerData();
    //设置数据，有多少层级自己确定
    data.setFirstDatas(mProvinceDatas);
    data.setSecondDatas(mCitisDatasMap);
    data.setThirdDatas(mDistrictDatasMap);
    data.setFourthDatas(new HashMap<String, String[]>());
    //设置弹出框的高度
    data.setHeight(高度);
    //设置初始化默认显示的菜单(此方法可以选择传参数量1到4个)
    data.setInitSelectText("河北省","石家庄市","平山县");
    
    //初始化选择器
    pickerView=new PickerView(this,data);
    
    点击按钮View.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           //显示选择器
           pickerView.show(点击按钮View);
       }
    });
    
    //选择器点击回调
    pickerView.setOnPickerClickListener(new OnPickerClickListener() {
       //选择列表时触发的事件（手动关闭）
       @Override
       public void OnPickerClick(PickerData pickerData) {
           text.setText(pickerData.getSelectText());
           pickerView.dismiss();//关闭选择器
       }
       //点击确定按钮触发的事件（自动关闭）
       @Override
       public void OnPickerConfirmClick(PickerData pickerData) {
           text.setText(pickerData.getSelectText());
       }
    });
}

```
###修改字体颜色
在自己工程的color.xml中定义就行了！
```xml
    列表和默认的提示颜色
    <color name="picker_text_color">#878787</color>
    选中颜色
    <color name="picker_select_text_color">#03a9f4</color>
    标题颜色
    <color name="picker_title_text_color">#000000</color>
    分割线颜色
    <color name="picker_border_color">#e5e5e5</color>
```
###修改选中指示器颜色
由于控件比较冷门就没有封装的特别好，不过你可以在drawable文件夹里放入你想要的指示器图片（.9图片哦！），
命名tab_indicator.9.png就行了！不清楚怎么花9patch的图片得看下面：
<br>
![tab_indicator.9.png例子截图](http://oceh51kku.bkt.clouddn.com/picker_tab_indicator.png)