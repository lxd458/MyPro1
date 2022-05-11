package com.example.retrofit;

import org.junit.Test;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TestPathHeader {

    Retrofit retrofit;
    HttpService httpService;
    @Test
    public void testPath() throws IOException {
        retrofit = new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
        httpService = retrofit.create(HttpService.class);
        //获取postPathHeader方法
        Call<ResponseBody> call = httpService.postPathHeader("post", "android","lxd", "1234");
        Response<ResponseBody> response = call.execute();
        //response.body().string()
        System.out.println(response.body().string());
        /* path:"post"对应着form表单
            "form": {
            "password": "1234",
            "username": "lxd"
                },
         */
    }
}