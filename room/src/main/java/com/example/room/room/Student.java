package com.example.room.room;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity //实体类==一张表
public class Student {

    //主键唯一。自增id
    @PrimaryKey(autoGenerate = true)
    private  int id;
    private  String name ;
     private  int age;

    @Ignore //避免优先选择加载,所以可以选择不写无参构造
    public Student() {
    }
/* 警告: 有多个好的构造函数，Room会选择无arg构造函数。可以使用@Ignore注释来消除不需要的构造函数。
 警告: There are multiple good constructors and Room will pick the no-arg constructor. You can use the @Ignore annotation to eliminate unwanted constructors.
 */
    public Student( String name, int age) { //自增id不用传，所以不用生成

        this.name = name;
        this.age = age;
    }

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
                ", age=" + age +
                '}';
    }
}
