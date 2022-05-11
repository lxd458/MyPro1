package com.example.room2;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/*
repository仓库类 类似于 之前的【引擎类】
 */
public class StudentRepository {
    //1.注入数据库类和DAO接口
  private  MyDatabase db;
  private  StudentDao dao;

  //2.含参构造
    public  StudentRepository(Context context){
        db=  MyDatabase.getInstance(context);   //数据库类名.方法名  ==得到数据库
        dao = db.getStudentDao();//通过数据库调用关联DAO方法   ==得到DAO
    }

    /*  一、插入操作：
      3. 封装声明数据库操作方法
      4.创建内部类来继承AsyncTask类，实现异步效果
     */
    //3.
    public void  insert(Student ... students){//新增需要参数==可变参数==多个参数
        //3.1 引用匿名内部类 ：new关键字除了创建，还可以调用
        new InsertAsyncTask(dao).execute(students);
    }
  /*
     二、修改操作
   */
  public void  update(Student ... students){//新增需要参数==可变参数==多个参数
      //3.1 创建匿名内部类
      new UpdateAsyncTask(dao).execute(students);
  }
   /*
      三、删除操作 ： 根据条件删除//参数指定属于单个删除
    */
     public void delete(Student... students){
         //3.1 创建匿名内部类
         new DeleteAsyncTask(dao).execute(students);
     }
   /*
     四、清除操作/清空操作//全删操作:不需要参数指定
    */
   public void clear(){  //!!!!!!不用参数
       //3.1 创建匿名内部类
       new ClearAsyncTask(dao).execute();
   }
     /*
       五、查询所有操作:不需要创建匿名内部类的异步效果
          返回数据类型：LiveData<List<Student>> 必须和DAO接口里面的一致!!!!!!
      */
     public List<Student> queryAll(){
         //3.1 创建匿名内部类
         return dao.queryAll();//直接返回
     }
     /*
       六、额外查询的操作
      */
     public LiveData<List<Student>> queryWithWordsLive(String str){
         return dao.queryWithWordsLive(str);
     }

    public List<Student> queryWithWords(String str){
        return dao.queryWithWords(str);
    }


    //===================《继承异步函数：构造函数+重写方法》===========================================
    //一、插入操作：默认private
    // 4.创建内部类,继承AsyncTask，指定泛型
     static class InsertAsyncTask extends AsyncTask<Student,Void,Void>{
          //4.2注入DAO
        private StudentDao studentDao;
         //4.3含参构造器
        public InsertAsyncTask(StudentDao dao) {  //new InsertAynscTask(dao)
            //this.studentDao=dao;
            studentDao=dao;
        }
        @Override //4.1实现（重写）异步方法
        protected Void doInBackground(Student... students) {
            studentDao.insertStudents(students);
            return null;
        }
    }
    // 二、修改操作
    static class UpdateAsyncTask extends AsyncTask<Student,Void,Void>{
        //4.2注入DAO
        private StudentDao studentDao;
        //4.3含参构造器
        public UpdateAsyncTask(StudentDao dao) {  //new InsertAynscTask(dao)
            //this.studentDao=dao;
            studentDao=dao;
        }
        @Override //4.1实现（重写）异步方法
        protected Void doInBackground(Student... students) {
            studentDao.updateStudents(students);//调用DAO修改方法
            return null;
        }
    }
    //三、删除操作：根据条件删除
   static class DeleteAsyncTask extends AsyncTask<Student,Void,Void>{
       //4.2注入DAO
       private StudentDao studentDao;
       //4.3含参构造器
       public DeleteAsyncTask(StudentDao dao) {  //new InsertAynscTask(dao)
           //this.studentDao=dao;
           studentDao=dao;
       }
       @Override //4.1实现（重写）异步方法
       protected Void doInBackground(Student... students) {
           studentDao.deleteStudents(students);//调用dao删除方法
           return null;
       }
   }
    //四、清除操作/清空操作//全删操作
    static class ClearAsyncTask extends AsyncTask<Void,Void,Void>{//4.4修改泛型参数，清空为VOID
        //4.2注入DAO
        private StudentDao studentDao;
        //4.3含参构造器
        public ClearAsyncTask(StudentDao dao) {  //new InsertAynscTask(dao)
            //this.studentDao=dao;
            studentDao=dao;
        }
        @Override //4.1实现（重写）异步方法
        protected Void doInBackground(Void... voids) { //4.5修改为无返回值的可变参数：Void... voids
            studentDao.clear();/* 具体清空方法*/
            return null;
        }


    }
    //五、查询操作,无需创建内部类



    //
}
