package com.example.wanghui.choosemenu;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.wanghui.choosemenu.model.Food;
import com.example.wanghui.choosemenu.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

     private EditText mNameEditText ;
     private RadioGroup mSexRadioGroup;
     private CheckBox mHotCheckBox;
     private  CheckBox mFishCheckBox;
     private  CheckBox mSourCheckBox ;
     private SeekBar mSeekBar;
     private  Button mSearchButton;
     private ImageView mimageView;
     private ToggleButton mNextButton;
     private Person person;
    private List<Food> foodResult;
    private List<Food> foods;
    private boolean isFish;
    private boolean isHot;
    private boolean isSour;
    private int price;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        findViews();

        //初始化数据
        initData();
        //为控件添加监听器，实现基本功能
        setListeners();
        //自测
    }

    private void setListeners() {

        //为EditText添加监听
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //更它的内容
                if(person!=null){
                    person.setName(s.toString());
                }
            }
        });
        //设置单选框listener
        mSexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){

                    case R.id.maleRaidoButton:
                          person.setSex("男");
                         break;
                    case R.id.femaleRaidoButton:
                        person.setSex("女");
                        break;
                     default:
                         break;
                }
            }
        });

        //设置复选框listener
        mFishCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isFish = isChecked;

            }
        });

        mHotCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isHot = isChecked;

            }
        });

        mSourCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isSour = isChecked;
            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                   price = seekBar.getProgress();
                Toast.makeText(MainActivity.this,"价格："+price,Toast.LENGTH_SHORT).show();
            }
        });

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              seekFood();
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(mNextButton.isChecked()){
                   currentIndex++;
                   if(currentIndex<foodResult.size()){
                       mimageView.setImageResource(foodResult.get(currentIndex).getPic());
                   }
               }else{
                   if(currentIndex<foodResult.size()){


                       String foodName = foodResult.get(currentIndex).getName();
                       //    String personName = mNameEditText.getText().toString();
                       String  personName = person.getName();
                       String  sex = person.getSex();
                       Toast.makeText(MainActivity.this,"菜名："+ foodName+",人名："+personName +",性别："+sex,Toast.LENGTH_SHORT).show();

                   }else{

                       Toast.makeText(MainActivity.this,"没有啦!",Toast.LENGTH_SHORT).show();

                   }



               }
            }
        });
    }

    //寻找菜品
    private void seekFood() {

        /**
         * 1.将存放结果的食物集合清空
         * 2.遍历所有事物
         * 3.将符合条件的食物存在foodResult里
         * */
        if(foodResult==null){
            foodResult= new ArrayList<>();
        }

        foodResult.clear();
        //结果食物列表中的当前索引
        currentIndex = 0;
        for(int index = 0;index<foods.size() ;index++){
            Food food = foods.get(index);
            if(food != null){

              if(food.getPrice()<price){
                  if( isHot ==true){
                      if(food.isHotFood())
                      foodResult.add(food);
                  }else if(isFish == true){
                        if(food.isFishFood())
                          foodResult.add(food);
                  } else if(isSour == true){
                         if(food.isSourFood())
                          foodResult.add(food);
                  }
                   if(isHot  && isFish ){

                       if(food.isHotFood()||food.isFishFood())
                           foodResult.add(food);
                   } else if (isHot  && isSour ){
                       if(food.isHotFood()||food.isSourFood())
                           foodResult.add(food);
                   }else if(isSour  && isFish ) {
                       if(food.isSourFood()||food.isFishFood())
                           foodResult.add(food);
                   }
                  if(isHot ==true && isFish == true &&isSour == true) {
                      if(food.isSourFood()||food.isFishFood()||food.isHotFood())
                          foodResult.add(food);
                  }
              }


            }
        }

        //先显示第一张图片
        if(currentIndex<foodResult.size()){
            mimageView.setImageResource(foodResult.get(currentIndex).getPic());
        }
    }

    private void initData() {
         foods = new ArrayList<>();
        //初始化食物数据
        foods.add(new Food("桂林米粉",25,R.drawable.guilin,false,false,true));
        foods.add(new Food("红烧肉",60,R.drawable.hongshaorou,false,false,false));
        foods.add(new Food("麻辣火锅",40,R.drawable.malahuoguo,true,false,false));
        foods.add(new Food("麻辣香锅",50,R.drawable.malaxiangguo,true,false,false));
        foods.add(new Food("木须肉",30,R.drawable.muxurou,false,false,false));
        foods.add(new Food("清蒸鲈鱼",65,R.drawable.qingzhengluyu,false,true,false));
        foods.add(new Food("水煮鱼",45,R.drawable.shuizhuyu,true,true,false));
        foods.add(new Food("酸辣汤",25,R.drawable.suanlatang,true,false,true));
        foods.add(new Food("西芹",23,R.drawable.xiqin,false,false,false));
        foods.add(new Food("娃娃菜",25,R.drawable.wawacai,false,false,false));
        foods.add(new Food("牛肉面",25,R.drawable.suncainiuroumian,false,false,true));

        //初始化person数据
         person = new Person();
         foodResult = new ArrayList<>();
    }

    private void findViews() {
        mNameEditText=findViewById(R.id.name_editText);
        mSexRadioGroup = findViewById(R.id.group_radio);
        mHotCheckBox = findViewById(R.id.hot_checkbox);
        mFishCheckBox = findViewById(R.id.fish_checkbox);
        mSourCheckBox = findViewById(R.id.sour_checkbox);
        mSeekBar = findViewById(R.id.seekbar);
        mSearchButton = findViewById(R.id.search_menu_button);
        mimageView = findViewById(R.id.food_imageView);
        mNextButton = findViewById(R.id.show_button);

    }
}
