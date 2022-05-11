package com.example.room.room;
/*
单例模式优点：1.一个类Class只有一个实例在
             2.只允许创建一个对象，节省内存资源
             3.不用频繁创建销毁对象
        缺点：1.不易保存动态的状态
             2.不被子类所继承
 */
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
/*
 数据库关联表
 */
//1.注解: 四个参数，只写三个:实体类，版本号，导出计划
@Database(entities = {Student.class},version = 1,exportSchema = false)
public abstract class StudentDataBase extends RoomDatabase{ //2.继承RoomDatabase //不实现方法，而是生成抽象类
    //3.设计单例模式
    //3.1把类名声明为静私有态变量
    private  static  StudentDataBase studentDataBase;
    //3.2私有构造
    public StudentDataBase() {
        /* private 修改成 public
        错误: StudentDataBase() 在 StudentDataBase 中是 private 访问控制
         */
    }
    //3.2公共静态访问：synchronized保证线程安全
    public static  synchronized StudentDataBase getInstance(Context context){//3.3必须传入Context环境
        if (studentDataBase==null){ //3.4判断是否为空
            //创建Builder().build()
            studentDataBase = Room.databaseBuilder(context.getApplicationContext(),StudentDataBase.class,"student_db").build();//context,class,name
        }
        //return创建的赋值的对象
        return studentDataBase;
    }
    //4.关联DAO操作，引入增删改查等功能 ： 声明抽象函数
    public  abstract  StudentDao  getStudentDao();

}
