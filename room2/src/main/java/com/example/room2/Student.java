package com.example.room2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity //
public class Student {
    @PrimaryKey(autoGenerate = true)//UNIQUE constraint failed: Student.id
    @ColumnInfo(name = "id")
    private  int  id;
    @ColumnInfo(name = "name")
    private  String name;
    @ColumnInfo(name = "sex")
    private  String sex;
    @ColumnInfo(name = "age")
    private  int  age;
   //构造
/* 含参构造相比于无参，优先加载并使用无参构造，所以不用无参
警告: There are multiple good constructors and Room will pick the no-arg constructor. You can use the @Ignore annotation to eliminate unwanted constructors.
 */
//    public Student() {
//    }

    public Student(int id, String name, String sex, int age) {
        this.id = id;//如果不给id，让他自增，应该会累加插入新增内容进行显示
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    ///get/set参数
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
