package com.example.intent;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable { //1.声明抽象类 //2. 实现方法 //3.Add Parcelable
    //在PErson上按住alt+enter 添加3
    public String name;
    public  int age;
    //手动生成无参构造
    public Person(){

    }
   //根据快捷键生存的构造
    protected Person(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }
    //根据快捷键生存的重写方法
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
