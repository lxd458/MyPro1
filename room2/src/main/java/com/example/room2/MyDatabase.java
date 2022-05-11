package com.example.room2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
/*
    数据库的抽象类
 */
@Database(entities = {Student.class},version = 1,exportSchema = false) //没有view
public abstract class MyDatabase extends RoomDatabase {
   private  static  MyDatabase db;

    public static MyDatabase getInstance(Context context) {//通过单例对象获取数据库
        if(db==null){                   //context,class,name
            db= Room.databaseBuilder(context,MyDatabase.class,"student") //name为数据库名
                    //.addMigrations(MIGRATION_1_2)更新数据库
                    .build();
        }
        return db;
    }

    public abstract StudentDao getStudentDao();//创建抽象的dao方法以便获取dao对象



}
