package com.example.data_sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /*
       SP的保存和获取
     */

    public void saveToSP(View view) {
        /*
          Context.MODE_PRIVATE 常规私有
          Context.MODE_APPEND 追加
         */
   //获取SP:SharedPreferences    getSharedPreferences(string name , int mode)
        SharedPreferences sPname = getSharedPreferences("SPname", Context.MODE_PRIVATE);
        //需要调用编辑器，put数据的key和value类型;apply()才能应用进去
        sPname.edit().putString("lxd", "开心就好").apply();
//点击运行，会生成一个shard_prefs目录，SPname.xml文件

    }

    public void getspDATA(View view) {
        SharedPreferences sp= getSharedPreferences("SPname", Context.MODE_PRIVATE);
        String string = sp.getString("lxd", "xxx");
        //弹框提示：
        Toast.makeText(this,"内容展示："+string,Toast.LENGTH_SHORT).show();
    }
}
