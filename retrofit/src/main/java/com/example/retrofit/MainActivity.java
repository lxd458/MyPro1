package com.example.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofit.bean.BaseResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/*
 采用new Retrofit.Builder().build()方法
   比使用OkHttp更简洁，更方便高效
 */


public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private HttpService httpService;//接口名
    WanAndroidJSON json;//接口名
    Response<BaseResponse> response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void getSync(View view) {
    }

    public void getASync(View view) {
    }

    public void postSync(View view)  {
      }

    public void postASync(View view) {
        /*
          post的异步请求：
          post:
          异步：不需要创建子线程
         */
        //1.创建对象
        retrofit = new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
        // 2.调用create方法：接口名.class
        httpService = retrofit.create(HttpService.class);//Class<Obj> service
        //3.Post异步请求:调用接口的post抽象方法
        Call<ResponseBody> call = httpService.post("lxd", "1234");
      //4.enqueue()异步方法:匿名内部类
       call.enqueue(new Callback<ResponseBody>() {
           private static final String TAG = "lxd";
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               try {
                   Log.d(TAG, "onResponse: "+response.body().string());
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {

           }
       });
    }
}
/* 在RUN里面查看打印内容:
D/lxd: onResponse: {
      "args": {},
      "data": "",
      "files": {},
      "form": {
        "password": "1234",
        "username": "lxd"
      },
      "headers": {
        "Accept-Encoding": "gzip",
        "Content-Length": "26",
        "Content-Type": "application/x-www-form-urlencoded",
        "Host": "www.httpbin.org",
        "User-Agent": "okhttp/3.3.0",
        "X-Amzn-Trace-Id": "Root=1-62559e6a-440dcb0a1c6325f25ad58391"
      },
      "json": null,
      "origin": "101.88.149.254",
      "url": "https://www.httpbin.org/post"
    }
 */
