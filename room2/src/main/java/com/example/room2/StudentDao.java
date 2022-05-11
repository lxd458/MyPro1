package com.example.room2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao //创建dao接口：
public interface StudentDao {
    @Insert //声明此为插入方法
    void insertStudents(Student... students);
    @Update //声明此外修改方法
    void updateStudents(Student... students);
    @Delete  //声明此外删除方法
    void deleteStudents(Student... students);
    @Query("Delete FROM Student")//全删清空
    void clear();

    @Query("SELECT * FROM Student")
    List<Student> queryAll();//查询所有;返回数据类型：LiveData<List<Student>>

   /*
     其他的查询操作：
    */
    @Query("SELECT * FROM Student WHERE name LIKE :str OR sex LIKE :str OR age LIKE :str ORDER BY id DESC")
    LiveData<List<Student>> queryWithWordsLive(String str);
    @Query("SELECT * FROM Student WHERE name LIKE :str OR sex LIKE :str OR age LIKE :str ORDER BY id DESC")
    List<Student>  queryWithWords(String str);


}
