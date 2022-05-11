package com.example.room2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
/*【没有用到整个类】
   AndroidViewModel封装仓库类所有的操作数据
   自定义方法封装
 */
public class StudentViewModel extends AndroidViewModel {//1.继承
    //3.注入仓库
    private  StudentRepository repository;
    //2.重写
    public StudentViewModel(@NonNull Application application) {
        super(application);

        //4.创建仓库对象
        repository = new StudentRepository(application);//application==context
    }
    /*
      通过ViewModel来获取 仓库数据：创建多个封装操作的调用方法

     */
    //自定义插入方法；携带参数
       public void insert1(Student... students){
           repository.insert(students);//调用仓库封装的操作方法
       }
       //
     public  void update2(Student... students){
           repository.update(students);//
     }
     public void delete3(Student... students){
           repository.delete(students);
     }
     public void clear4(){//无参
           repository.clear();//无参
     }
     //查询所有操作，无参，但有返回值
     public List<Student> queryAll(){
         return repository.queryAll();
     }
     /*
       其他查询操作
      */
     public LiveData<List<Student>> queryWithWordsLive(String str){
         return repository.queryWithWordsLive(str);
     }

    public List<Student> queryWithWords(String str){
        return repository.queryWithWords(str);
    }


}
