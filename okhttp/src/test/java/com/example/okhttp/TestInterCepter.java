package com.example.okhttp;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestInterCepter {
    /*
    addInterceptor() 先执行
    addNetworkInterceptor() 后执行
     */
    @Test
    public void testIntercepeter(){
        OkHttpClient client = new OkHttpClient.Builder()
                //添加缓存
                .cache(new Cache(new File("C:\\Users\\HP\\Desktop"),1024*1024))
                //添加拦截器
                .addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
               //前置拦截器处理:统一的拦截器，简化了重复的代码
                Request request = chain.request().newBuilder().addHeader("hp", "android")
                        .addHeader("version", "1.1.0")
                        .build();

                Response response = chain.proceed(request);
                //后置拦截器处理
                 //XXXXXXXXXXXXXXXXXXXXX
                return response;
            }
        }).addNetworkInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                System.out.println("version:"+chain.request().header("1.1.0"));
                return chain.proceed(chain.request());
            }
        }).build();

        Request request = new Request.Builder().url("https://www.httpbin.org/get?name=lxd&age=26").build();
        //调用newCall()方法，生成Call对象
        Call call = client.newCall(request);
        try {
            //
            Response response = call.execute();//对象调用execute执行方法
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*
     "headers": {
    "Accept-Encoding": "gzip",
    "Host": "www.httpbin.org",
    "Hp": "android",
    "User-Agent": "okhttp/4.9.3",
    "Version": "1.1.0",
    "X-Amzn-Trace-Id": "Root=1-6254f962-47ead6754f6dcb5543120efe"
  },
     */

}
