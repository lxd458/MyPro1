package com.example.room.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {

     //抽象方法
    @Insert
    void insertDemo(Student ... students);//多个参数===可变参数
    @Update
    void  updateDemo(Student ... students);

    @Delete
    void deleteDemo(Student ... students); //根据条件删除

    @Query("delete  from  Student")
    void deleteDemo2();


    @Query("select * from Student order by id desc")
    List<Student> getAllDemo();


}
