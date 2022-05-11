package com.example.okhttp;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



/*
测试文件上传
 */
public class TestUpload {
      @Test
    public  void upload() throws IOException {
        //yi一、创建客户端
        OkHttpClient okHttpClient = new OkHttpClient();
        //二、声明两个文件的位置
        File file1 = new File("C:\\Users\\HP\\Desktop\\1.txt");//记住为双斜杠,补充
        File file2= new File("C:\\Users\\HP\\Desktop\\2.txt");//记住为双斜杠,补充
        //三、生成文件的内容和类型
        RequestBody body1 = RequestBody.create("file1", MediaType.parse("text/plain"));
        RequestBody body2 = RequestBody.create("file2", MediaType.parse("text/plain"));
        //四、创建MultipartBody对象来传值addFormDataPart
        MultipartBody multipartBody = new MultipartBody.Builder()
                .addFormDataPart("文件1", file1.getName(), body1)
                .addFormDataPart("文件2", file2.getName(), body2)
                .addFormDataPart("a","1")
                .build();

        //五、创建Request对象，添加请求路径和请求体
        Request request = new Request.Builder().url("https://www.httpbin.org/post")
                .post(multipartBody)
                .build();
        //六、通过newCall()方法生成Call对象
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();//同步方法
        System.out.println(response.body().string());

//

    }

    @Test
    public  void testJson() throws IOException {
          //一、
        OkHttpClient okHttpClient = new OkHttpClient();
       //二、定义文件的内容和类型  :{\"a\":1,\"b\":2}" JSON格式
        RequestBody requestBody = RequestBody.create("{\"a\":1,\"b\":2}", MediaType.parse("application/json"));
        //三、创建Request对象的请求路径和请求方式
        Request request = new Request.Builder().url("https://www.httpbin.org/post")
                .post(requestBody)
                .build();
        //四、通过newCall()方法把request交给OkhttpClient
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();//抛出异常
        System.out.println(response.body().string());
    }
/*
"json": {
    "a": 1,
    "b": 2
  },
 */

}
