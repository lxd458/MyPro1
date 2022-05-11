package com.example.retrofit;

import org.junit.Test;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TestURL {
    Retrofit retrofit;
    HttpService httpService;
    @Test
    public void testUrl() throws IOException {
        retrofit = new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
        httpService = retrofit.create(HttpService.class);
       //获取postURL方法
        Response<ResponseBody> response = httpService.postURL("https://www.httpbin.org/post").execute();
        System.out.println(response.body().string());

    }
}
