package com.example.mygson.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/*
 public boolean serialize() default true;//序列化默认开启
  public boolean deserialize() default true;//反序列化默认开启
 */
public class User {
    @Expose ////只有调用new GsonBuilder()才能让注解生效
    //gson注解 ： 可加可不加，因为默认参与序列与反序列化
    private String userName;
    @Expose
    private  String pwd;
    @Expose
    private int age;
    @Expose
    private boolean isStudent;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public int getTest1() {
        return test1;
    }

    public void setTest1(int test1) {
        this.test1 = test1;
    }

    public int getTest2() {
        return test2;
    }

    public void setTest2(int test2) {
        this.test2 = test2;
    }

    public int getCls() {
        return cls;
    }

    public void setCls(int cls) {
        this.cls = cls;
    }

    @Expose
    private Job job;//嵌套类的对象
    @Expose(serialize = false , deserialize = false) //可以具体指定都不参与序列与反序列化
    private  int test1;

    @Expose //只有调用new GsonBuilder()才能让注解生效
    private  transient  int test2;//transient表示不参与任何序列与反序列化。但加 @Expose可以具体指定

    @SerializedName("class") //指定JSON串的数据输出为：class
    private  int cls;

    public User(String userName, String pwd, int age, boolean isStudent) {
        this.userName = userName;
        this.pwd = pwd;
        this.age = age;
        this.isStudent = isStudent;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }


}
