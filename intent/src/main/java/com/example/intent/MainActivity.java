package com.example.intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
/*
  <!--当前为活动一：分为三种跳转格式：-->
        <!--活动一 跳转 活动二
	     活动一  跳转到  活动三
          活动一 跳转到 活动四-->
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
     /*
       活动一 跳转 活动二
      */
    public void Jump(View view) {
        //1.当前活动一 跳转到 活动页面2
        Intent intent = new Intent(this,Main2Activity.class);
        //2.方法一、携带多个参数
        Intent name = intent.putExtra("name", "lxd");
        Intent sex = intent.putExtra("sex", "男");
        //3、putExtras封装数据
        intent.putExtras(name);
        intent.putExtras(sex);
        //方法二、携带bundle封装的数据
//        Bundle bundle = new Bundle();//Bundle封装数据
//        bundle.putString("name","wlp");
//        bundle.putString("sex","女"); //''单引号字符
//        intent.putExtras(bundle);

       //4.开启跳转
        startActivity(intent);
    }

   /*
     活动一  跳转到  活动三
    */
    public void Jump2(View view) {
        ////1、指定传输：活动一 跳转到 活动页面三
        Intent intent = new Intent(this,Main3Activity.class);
       //2、引用序列化类，并赋值
        Student student = new Student();
        student.id = 1;
        student.name = "wcl";
        student.sex = "女";
        //3、
        intent.putExtra("Student",student);//KEY==> Student
        //4、
        startActivity(intent);//开启跳转

    }
    /*
      活动一 跳转到 活动四
     */
    public void Jump3(View view) {
//        活动一跳转到活动页面四
        //1.创建Intent对象，指定跳转页面
        Intent intent = new Intent(this,Main4Activity.class);
         //2.创建Person对象，并赋值
        Person person = new Person();//缺少无参构造，所以报错
        person.name="小李";//赋初值
        person.age=18;
        //3.封装数据
        intent.putExtra("Person",person);//传入person，指定KEY
       // intent.putExtras()
        //4.开启跳转
        startActivity(intent);//
    }
}
