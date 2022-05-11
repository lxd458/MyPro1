package com.example.okhttp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 */
public class MainActivity extends AppCompatActivity{
 public static final String TAG = "lxd";
  private   OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //1,创建OkHttpClient客户端
        client = new OkHttpClient();
  }
  /*
    完成get的同步请求,在线程中完成同步请求
    1.需要创建new Thread(){}子线程
    2.都要生成Call对象
    3.调用execute（）方法完成get请求
   Request request = new Request.Builder().url().build();
   */
    public void getSync(View view) { //测试URL：https://www.httpbin.org/
      /* 快速创建子线程的方法：
       //new Thread(){run}.start();
       */
     new Thread(){
         @Override
         public void run() {
             //上传对象Request.Builder().url("").build();
             Request request = new Request.Builder().url("https://www.httpbin.org/get?name=lxd&age=26").build();
             //调用newCall()方法，生成Call对象
             Call call = client.newCall(request);
             try {
                 //
                 Response response = call.execute();//对象调用execute执行方法
                 Log.d(TAG, "getSync:get同步 "+response.body().string());//获取响应体的get字符串内容
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }.start();
    }
/*
   get的异步请求：
 */
    public void getASync(View view) {
        //上传对象Request.Builder().url("").build();
        Request request = new Request.Builder().url("https://www.httpbin.org/get?name=lxd&age=26").build();
        //调用newCall()方法，生成Call对象
        Call call = client.newCall(request);
            //enqueue()方法：异步请求,不会自己阻塞，添加回调对象的方法重写
           //enqueue()方法：内部自动创建子线程；
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if(response.isSuccessful()){
                        Log.d(TAG, "getASync:get异步 "+response.body().string());//获取响应体的get字符串内容
                    }

                }
            });



    }
//====================================================================
    public void postSync(View view) {
        /*格式：  XXX xx = new XXXX..Builder().xxx().build();
         post同步请求:execute()
         1.创建子线程
         2.创建FormBody.Builder()对象
         3.上传对象Request
         3.newCall方法生成Call对象
         */
        new Thread(){
            @Override
            public void run() {
                //RequestBody:请求体
                FormBody formBody = new FormBody.Builder()
                        .add("a","1")
                        .add("b","2")
                        .build();
                //上传对象Request.Builder().url("").build();
                Request request = new Request.Builder().url("https://www.httpbin.org/post")
                        .post(formBody)
                        .build();
                //调用newCall()方法，生成Call对象
                Call call = client.newCall(request);
                try {
                    //对象调用execute执行方法:同步模式
                    Response response = call.execute();
                    Log.d(TAG, "postSync:post同步 "+response.body().string());//获取响应体的get字符串内容
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void postASync(View view) {
        /*
         post的异步请求：不需要创建子线程
         1.FormBody对象
         2.Request对象
         3.Call对象
         */
        //一、FormBody:表格体
        FormBody formBody = new FormBody.Builder()
                .add("a","1")
                .add("b","2")
                .build();

        //二、上传对象Request.Builder().url("").build();
        Request request = new Request.Builder().url("https://www.httpbin.org/post")
                .post(formBody)
                .build();
        //三、调用newCall()方法，生成Call对象
        Call call = client.newCall(request);
        //四、调用enqueue（new Callback(){}）方法：进入异步模式
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                    Log.d(TAG, "postASync:post异步 "+response.body().string());//获取响应体的get字符串内容


            }
        });
    }
}


/**----------------------------------------------------------------
 * D/lxd: getSync: {
 *       "args": {
 *         "age": "26",
 *         "name": "lxd"
 *       },
 *       "headers": {
 *         "Accept-Encoding": "gzip",
 *         "Host": "www.httpbin.org",
 *         "User-Agent": "okhttp/4.9.3",
 *         "X-Amzn-Trace-Id": "Root=1-6253ee92-16528b0e5958fba207d9ccb8"
 *       },
 *       "origin": "101.88.149.254",
 *       "url": "https://www.httpbin.org/get?name=lxd&age=26"
 *     }
 */