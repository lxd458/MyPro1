package com.example.room;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.room.room.Student;
import com.example.room.room.engine.DBEngine;

public class MainActivity extends AppCompatActivity {
   private DBEngine dbEngine;//注入引擎对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         //创建引擎对象
        dbEngine = new DBEngine(this); //context = 当前activity==this

    }
   //新增插入
    public void insert(View view) {
        //创建实体类对象
        Student student1 = new Student("张三", 19);
        Student student2 = new Student("李四", 22);
        Student student3 = new Student("王五", 25);
        //调用引擎的增删改查方法
        dbEngine.insert(student1,student2 ,student3);//因为实体类和引擎类为可变的多个参数

        System.out.println("已插入新增");

    }
    //修改 id=3
    public void update(View view) {

        Student student = new Student("风云", 20);

        student.setId(3);//指定下标
        dbEngine.update(student);
        System.out.println("已修改下标为3的数据");
    }
     //条件删除: id=3
    public void delete(View view) {
        Student student = new Student(null,0);//
        student.setId(3);//指定下标为3的
        dbEngine.delete();
        System.out.println("已删除下标为3的数据");
    }

    public void deleteAll(View view) {
        dbEngine.deleteAll();
        System.out.println("已删除所有数据");
    }

    public void select(View view) {
        dbEngine.selectAll();
        System.out.println("显示所有数据");//JSON格式 查询所有：Student{id=4, name='张三', age=19}
    }
}
