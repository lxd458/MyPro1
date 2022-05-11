package com.example.data_sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    EditText et_name;//全局变量
    EditText et_pwd;
    CheckBox cb_rember;
    CheckBox  cb_autologin;
    Button btn_login;
    Button btn_register;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         //获取SP首选项，配置名字和模式
         sp = getSharedPreferences("config", Context.MODE_PRIVATE);

        initView(); //初始化

        /*
           获取SP里面存的数据
         */
        //TODO 回显数据，显示两个打勾状态: true /false ===> Boolean类型
        boolean remberpwd = sp.getBoolean("remberpwd", false);//如果为空，获取false默认值
        boolean autologin = sp.getBoolean("autoLogin", false);
        //判断打勾里面的内容
        if (remberpwd){
            String name = sp.getString("name", "");//默认值为空
            String pwd = sp.getString("pwd", "");
             //输入的数据进行回显
             et_name.setText(name);
             et_pwd.setText(pwd);
             cb_rember.setChecked(true);//设置打勾状态回显为true
        }
        //判断打勾里面的内容
        if (autologin){
            //自动登录的打勾状态
            cb_autologin.setChecked(true);
            /*
             模拟进入主界面的效果:二次打开可以看见
             */
            //登录后的提示框:第二次打开页面才会给出Toast提示
            Toast.makeText(Main2Activity.this,"我在自动登录了",Toast.LENGTH_SHORT).show();
        }

    }

    private void initView() {

         et_name = findViewById(R.id.et_name);
         et_pwd = findViewById(R.id.et_pwd);
       cb_rember = findViewById(R.id.cb_rember);
       cb_autologin = findViewById(R.id.cb_autologin);
         btn_login= findViewById(R.id.btn_login);
         btn_register= findViewById(R.id.btn_register);
        //需要调用监听器
        MyOnclickListener listener = new MyOnclickListener();
        btn_login.setOnClickListener(listener );
        btn_register.setOnClickListener(listener );


    }
   //内部类
    private  class   MyOnclickListener implements  View.OnClickListener {

       @Override
       public void onClick(View view) {
           //判断多种情况，采用switch case
            switch (view.getId()){
                case  R.id.btn_register:
                    break;
              //登录操作
              case  R.id.btn_login:
                    String name = et_name.getText().toString().trim();//防止空格出现
                    String pwd = et_pwd.getText().toString().trim();
              if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)){
              Toast.makeText(Main2Activity.this,"用户名或密码为空",Toast.LENGTH_SHORT).show();
              }else {
                    /*
                      保存记住密码和自动登录的数据到SP。
                     */
                    if (cb_rember.isChecked()){  //判断记住密码是否打勾
                      //获取SP编辑器:将用户名和密码 保存 进SP
                      SharedPreferences.Editor editor = sp.edit();
                       editor.putString("name",name);
                        editor.putString("pwd",pwd);
                        editor.putBoolean("remberpwd",true); //记住密码打勾的状态
                        editor.apply();
                    }
                    if (cb_autologin.isChecked()){ //判断自动登录是否打勾
                      //获取SP编辑器:
                      SharedPreferences.Editor editor = sp.edit();
                      editor.putBoolean("autoLogin",true);//实现自动登录打勾的状态
                        editor.apply();
                    }
                  }
                    break;
            }
       }
   }





}
