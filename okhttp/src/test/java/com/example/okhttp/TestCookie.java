package com.example.okhttp;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestCookie {
    //先定义map集合
    Map<String,List<Cookie>> cookies = new HashMap<>();
    Request request;
    @Test
    public void testCookies(){
            //一、创建OKp客户对象
        OkHttpClient client = new OkHttpClient.Builder()
                //1.添加缓存
//                .cache(new Cache(new File("C:\\Users\\HP\\Desktop"),1024*1024))
//                //2.添加第一种拦截器
//                .addInterceptor(new Interceptor() {
//                    @NotNull
//                    @Override
//                    public Response intercept(@NotNull Chain chain) throws IOException {
//                        //前置拦截器处理:统一的拦截器，简化了重复的代码
//                        Request request = chain.request().newBuilder().addHeader("hp", "android")
//                                .addHeader("version", "1.1.0")
//                                .build();
//                        Response response = chain.proceed(request);
//                        //后置拦截器处理
//
//                        return response;
//                    }
//                }) //3.继续添加第二种拦截器
//                .addNetworkInterceptor(new Interceptor() {
//                    @NotNull
//                    @Override
//                    public Response intercept(@NotNull Chain chain) throws IOException {
//                        System.out.println("version:"+chain.request().header("1.1.0"));
//                        return chain.proceed(chain.request());
//                    }
//                })
                   //4. 添加cookie：使用cookieJar(new CookieJar(){})
                .cookieJar(new CookieJar() { //重写两个方法：saveFromResponse + loadForRequest
                    @Override
                    public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
                        //
                        cookies.put(httpUrl.host(), list);//ip+cookies
                    }
                    @NotNull
                    @Override
                    public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
                        ////IP地址：httpUrl.host()
                        List<Cookie> cookies = TestCookie.this.cookies.get(httpUrl.host());//get(Obj)
                        //特殊返回处理：避免空值为null
                        return cookies == null ? new ArrayList() : cookies;
                    }
                })
                .build();
         //二、FormBody.Builder() 创建表单请求体
        FormBody formBody = new FormBody.Builder().add("username", "顾北辰")
                .add("password", "admin123456")
                .build();
         //三、到请求的网址登录，上传请求信息完成登录
         request = new Request.Builder().url("https://www.wanandroid.com/user/login")
                .post(formBody) //post请求格式
                .build();
        //四、调用newCall()方法，生成Call对象
        Call call = client.newCall(request);
        try {
            //
            Response response = call.execute();//对象调用execute执行 同步方法
            System.out.println(response.body().string());//打印登录表单内容
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* 打印内容如下：
        {"data":{"admin":false,"chapterTops":[],"coinCount":10,"collectIds":[22387],
        "email":"","icon":"","id":129645,"nickname":"顾北辰","password":"","publicName":"顾北辰","token":"",
        "type":0,"username":"顾北辰"},"errorCode":0,"errorMsg":""}
         */
        //======================================================
        //请求该网站下的收藏文章, //https://www.wanandroid.com/lg/collect/list/0/json
        //三、请求该网站下的自定义网站收藏的列表
        request = new Request.Builder().url("https://www.wanandroid.com/lg/collect/usertools/json")
                .build();
        //四、调用newCall()方法，生成Call对象
        Call call2 = client.newCall(request);
        try {
            //
            Response response = call2.execute();//对象调用execute执行 同步方法
            System.out.println(response.body().string());//打印收藏数据内容
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*打印内容如下：
        {"data":{"curPage":1,"datas":[{"author":"","chapterId":502,"chapterName":"自助","courseId":13,"desc":"","envelopePic":"","id":252501,
        "link":"https://juejin.cn/post/7084544971713282056/","niceDate":"21分钟前","origin":"","originId":22387,"publishTime":1649745506000,
        "title":" Android组件系列：Handler机制详解","userId":129645,"visible":0,"zan":0}],"offset":0,"over":true,"pageCount":1,"size":20,"total":1},"errorCode":0,"errorMsg":""}
         */


    }
}
