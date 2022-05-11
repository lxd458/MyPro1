package com.example.room2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

/*
  MainActivity活动里 直接声明viewModel使用就好：
 */
public class MainActivity extends AppCompatActivity {
    MyDatabase studentDatabase;
    StudentDao studentDao;
    TextView textView;
    Button buttonInsert,buttonUpdate,buttonDelete,buttonClear,buttonQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        // allowMainThreadQueries() 强制允许在主线程执行，真实开发中不要这么做
        //db = MyDatabase.getsInstance(this);
        //studentDao = db.studentDao();
        studentDatabase = Room.databaseBuilder(this,MyDatabase.class,"student").allowMainThreadQueries().build();
        studentDao = studentDatabase.getStudentDao();

        updateView(); //把所有操作的记录都传到屏幕上显示，而不需要打印查看

        buttonInsert = findViewById(R.id.buttonInsert);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonClear = findViewById(R.id.buttonClear);
        buttonQuery = findViewById(R.id.buttonQuery);


        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student1 = new Student(1,"lxd","男",20);
                Student student2 = new Student(2,"lwj","男",22);
                Student student3 = new Student(3,"wlp","女",25);
                studentDao.insertStudents(student1,student2,student3);//
                updateView();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentDao.clear();//
                updateView();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student= new Student(2,"小可爱","女",20);
                studentDao.updateStudents(student);
                updateView();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //注意点
                Student student = new Student(3,"","",0);
                studentDao.deleteStudents(student);
                updateView();
            }
        });

        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentDao.queryAll();//基本用不上
                updateView();
            }
        });

    }

   public void updateView(){
      List<Student> list = studentDao.queryAll();//查询所有
       String text = "";
        for(int i = 0; i < list.size(); i++){
            Student student= list.get(i);
            text += student.getId() + ":" + student.getName()  + ":" + student.getSex() +
                    ":" + student.getAge() +"\n";
        }
        textView.setText(text);
    }


}










