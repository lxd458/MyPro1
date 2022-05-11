package com.example.retrofit;

import org.junit.Test;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TestFormBody {
    Retrofit retrofit;
    HttpService httpService;
    @Test
    public void testBody() throws IOException {
     retrofit = new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
     httpService = retrofit.create(HttpService.class);
    //RequestBody改为使用FromBody
        FormBody formBody = new FormBody.Builder().add("lxd", "1234")
                .add("a", "1")
                .build();
        //获取postBody（）方法
        Call<ResponseBody> call = httpService.postBody(formBody);
        Response<ResponseBody> response = call.execute();
        //response.body().string()
        System.out.println(response.body().string());
/*
{
  "args": {},
  "data": "",
  "files": {},
  "form": {
    "a": "1",
    "lxd": "1234"
  },

 */
    }
}
