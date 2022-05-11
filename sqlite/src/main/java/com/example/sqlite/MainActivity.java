package com.example.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "lxd" ;//constant

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createDB(View view) {
        /* 【生成db文件，把文件保存到磁盘，拖到可视化工具查看】
          因为构造私有化，不能创建对象，只能调用公共静态方法getSqlite()
         */
        SQLiteOpenHelper helper = MySQLiteOpenHelper.getSqlite(this);//类名.方法名
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();//读取

    }

  /*
    进行增删改查，通过按钮触发函数的SQL语句
    1.获取单例模式下的帮助类
    2.获取读写的数据库
    3.if判断是否打开数据库
    4.内部编写SQL语句
    5.执行SQL，打印SQL
    6.关闭数据库
   */ //查询触发事件
    public void query(View view) {
        //类名.方法
        SQLiteOpenHelper helper = MySQLiteOpenHelper.getSqlite(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        if (db.isOpen()){//打开数据库
            //通过查询获取游标  db.rawQuery（）
            Cursor cursor = db.rawQuery("select * from persons ", null);
            //迭代遍历游标
            while (cursor.moveToNext()){
//                int anInt = cursor.getInt(0); //简写
//                String string = cursor.getString(1);
                int id = cursor.getInt(cursor.getColumnIndex("_id"));//细写
                String name = cursor.getString(cursor.getColumnIndex("name"));
           //打印
                Log.d(TAG, "query: id="+id +';'+"name="+ name);

            }
               //关闭游标
                cursor.close();
            //关闭数据库
               db.close();
        }
    }
    /*
       //插入/新增触发事件
     */
    public void insert(View view) {
        SQLiteOpenHelper helper = MySQLiteOpenHelper.getSqlite(this);
        //插入为写操作
        SQLiteDatabase db = helper.getWritableDatabase();
        if (db.isOpen()){
            String sql = "insert into persons(name) values('lxd') ";
            db.execSQL(sql);//执行插入SQL
            Log.d(TAG, "insert:  "+sql);
            db.close();
        }


    }
   /*
      //修改触发事件
    */
    public void update(View view) {
        SQLiteOpenHelper helper = MySQLiteOpenHelper.getSqlite(this);
        //更新为写操作
        SQLiteDatabase db = helper.getWritableDatabase();
        if (db.isOpen()){
            String sql = "update persons set name = ?  where _id= ? ";//修改操作使用占位符？
            db.execSQL(sql,new Object[]{"琳儿",2});//更新赋值,new Object[]{} 但缺点是id写死了
            Log.d(TAG, "update: 已修改第四个");
            db.close();
        }

    }
        //删除触发事件
    public void delete(View view) {
        SQLiteOpenHelper helper = MySQLiteOpenHelper.getSqlite(this);
        //更新为写操作
        SQLiteDatabase db = helper.getWritableDatabase();
        if (db.isOpen()){
            String sql = "delete from persons where _id = ?";
            db.execSQL(sql,new Object[]{5});//根据id条件删除进行赋值；但缺点是id写死了
            Log.d(TAG, "delete: 已删除id为4的所有字段");
            db.close();
        }

    }
}
