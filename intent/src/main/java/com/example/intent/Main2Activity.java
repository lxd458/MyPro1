package com.example.intent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //接收活动1的跳转数据
        Intent intent = getIntent();
        //方法一、获取具体参数
        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");//默认值只能使用单引号
           //给出弹窗显示
        Toast.makeText(this, "name:"+name+'\n'+"sex:"+sex, Toast.LENGTH_LONG)
                .show();



    }
}
