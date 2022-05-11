package com.example.room.room.engine;

import android.content.Context;
import android.os.AsyncTask;

import com.example.room.room.Student;
import com.example.room.room.StudentDao;
import com.example.room.room.StudentDataBase;

import java.util.List;

public class DBEngine {

    //1.注入DAO
    private StudentDao studentDao;
    //2.构造函数
    public DBEngine(Context context){
        //3.通过数据库StudentDataBase获取单例模式的公共方法
        StudentDataBase instance = StudentDataBase.getInstance(context);
        //4.获取数据库关联DAO的声明方法
        studentDao = instance.getStudentDao();//拿到DAO

    }
    /*
       Dao增删改查
     */
    //1.插入新增 MyAsyncTask1
    public  void  insert (Student... students){
       new MyAsyncTask1(studentDao) {
           @Override
           protected Void  doInBackground(Student... students) {
               studentDao.insertDemo(students); //调用抽象方法
               return null;
           }
       }.execute(students);
    }
    //2.修改 : MyAsyncTask2
    public void update(Student... students){
        new MyAsyncTask2(studentDao) {
            @Override
            protected Void doInBackground(Student... students) {
                 studentDao.updateDemo(students);
                return null;
            }
        }.execute(students);
    }
    //3.根据条件删除
    public  void delete ( Student... students){
        
        new MyAsyncTask3(studentDao) {
            @Override
            protected Void doInBackground(Student... students) {
                studentDao.deleteDemo(students);
                return null;
            }
        }.execute(students);
    }
   //4.全部删除
   public  void deleteAll (){
        
       new MyAsyncTask4(studentDao) { //传入
           @Override
           protected Void doInBackground(Void... voids) {
               studentDao.deleteDemo2();
               return null;
           }
       }.execute();
   }
    //5.全部查询
    public  void selectAll (){
        
        new MyAsyncTask5(studentDao) {
            @Override
            protected Void doInBackground(Void... voids) {
                List<Student> list = studentDao.getAllDemo();
                for (Student student : list
                ) {
                    System.out.println("查询所有：" + student.toString());
                }
                return null;
            }
        }.execute();
    }
    //========================抽象类

   static abstract class  MyAsyncTask1 extends AsyncTask<Student,Void,Void>{//<Params,Progress,Result>

       private StudentDao dao;
       public MyAsyncTask1(StudentDao studentDao) {
           dao=studentDao;
       }//过期了，

   }
    static abstract class  MyAsyncTask2 extends AsyncTask<Student,Void,Void>{//<Params,Progress,Result>
         private StudentDao dao;
        public MyAsyncTask2(StudentDao studentDao) { //不等传值
            dao = studentDao;
        }//过期了，

    }
    static abstract class  MyAsyncTask3 extends AsyncTask<Student,Void,Void>{//<Params,Progress,Result>
        private StudentDao dao;
        public MyAsyncTask3(StudentDao studentDao) { //不等传值
            dao = studentDao;
        }
    }
    static abstract class  MyAsyncTask4 extends AsyncTask<Void,Void,Void>{//过期了，
        private StudentDao dao;
        public MyAsyncTask4(StudentDao studentDao) { //不等传值
            dao = studentDao;
        }
    }
    static abstract class  MyAsyncTask5 extends AsyncTask<Void,Void,Void>{//过期了，
        private StudentDao dao;
        public MyAsyncTask5(StudentDao studentDao) { //不等传值
            dao = studentDao;
        }
    }

}
/*      varargs:可变参数 //<Params,Progress,Result>
警告: 最后一个参数使用了不准确的变量类型的 varargs 方法的非 varargs 调用;
对于 varargs 调用, 应使用 Object
对于非 varargs 调用, 应使用 Object[], 这样也可以抑制此警告
 */