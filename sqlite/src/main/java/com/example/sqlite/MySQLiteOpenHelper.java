package com.example.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/*
 SQLiteOpenHelper帮助工具类======》单例模式：
                    1.私有构造方法
                    2.私有静态变量
                    3.提供对外静态函数

 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    //3.全局公共访问方法
    public  static  synchronized   SQLiteOpenHelper getSqlite(Context context){
        if(sqlite==null){
            //创建新的对象
            sqlite= new  MySQLiteOpenHelper(context,"DB.db",null,1);
        }
        return sqlite;
    }
    //2.私有静态变量
    private  static  SQLiteOpenHelper sqlite;
    //1.自动实现构造函数。需要改成私有
    //参数类型选用一
    private MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //参数类型二
    private MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
    //参数类型三
    private MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

  /*
   extends SQLiteOpenHelper 重写 onCreate 和 onUpgrade
   */

    @Override //初始化
    public void onCreate(SQLiteDatabase db) {
         //创建persons表。写SQL语句
        String sql = "create table persons(_id integer primary key autoincrement , name text )";
         //数据库执行SQL方法
        db.execSQL(sql);
    }

    @Override //数据库升级
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
