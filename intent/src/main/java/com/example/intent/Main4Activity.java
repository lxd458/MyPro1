package com.example.intent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
          //1.获取intent对象
        Intent intent = getIntent();
        //2.获取参数key和Person对象 ：注意：需要把Parcelable改成Person,才能进行: 类名.变量名 调用
        Person person = intent.getParcelableExtra("Person");/*针对活动四 */
        //3.弹窗打印显示
Toast.makeText(this, "name:"+person.name+" age:"+person.age, Toast.LENGTH_SHORT).show();
    }
}
