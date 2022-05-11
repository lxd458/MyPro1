package com.example.retrofit;

import org.junit.Test;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TestHeraders {
    Retrofit retrofit;
    HttpService httpService;
    @Test
    public void testHeaders() throws IOException {
        retrofit = new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
        httpService = retrofit.create(HttpService.class);
        //获取postWithHeaders()方法
        Call<ResponseBody> call = httpService.postWithHeaders();
        Response<ResponseBody> response = call.execute();
        //response.body().string()
        System.out.println(response.body().string());
        /*
         非法参数异常：没有明确指出 @POST（"post"）
         */
    }
    /*
 "data": "",
  "files": {},
  "form": {},
  "headers": {
    "Accept-Encoding": "gzip",
    "Content-Length": "0",
    "Host": "www.httpbin.org",
    "Os": "android",
    "User-Agent": "okhttp/3.3.0",
    "Version": "1.0",
    "X-Amzn-Trace-Id": "Root=1-625666be-480f43957831fddd202c67ad"
  },
     */
}
