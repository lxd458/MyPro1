package com.example.intent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //接收活动1的跳转数据
        Intent intent2 = getIntent();
        //用序列化对象的参数Serializable改为Stydent
        Student student = (Student) intent2.getSerializableExtra("Student");//kEY必须一致
        Toast.makeText(this, "id:"+student.id+" name:"+student.name+" sex:"+student.sex, Toast.LENGTH_SHORT).show();

    }
}
