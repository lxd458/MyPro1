package com.example.retrofit;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/*
  第一步：完成接口的请求方法    第二步：接口的实现类，完成对post请求的改造
  【TestFormBody + TestHeaders +TestPathHeader + TestURL】
 */
public interface HttpService {
 /*
    1.注意两种写死的请求格式   @GET("get")  //   @POST("post")
    2.注意多种注解的使用:  方法注解全大写（""） + 参数注解首字母大写("")
    3.注意URL路径的不同
  */
 //一、正常使用GET和POST注解请求：
      @GET("get")   // @GET("get")+ @Query("xxxx")
      Call<ResponseBody> get(@Query("username") String userName , @Query("password") String  pwd);
    //https://www.httpbin.org/post/
      @POST("post")
      @FormUrlEncoded//表单注解联合使用：【 @POST("post")+ @FormUrlEncoded + @Field】
      Call<ResponseBody>  post(@Field("username") String userName , @Field("password") String  pwd);
//==========================================
   //二、使用HTTP注解里面的GET/POST请求
    @HTTP(method = "GET",path = "get" )   // 表示HTTP下的GET请求，拼接get路径
    Call<ResponseBody> http(@Query("username") String userName , @Query("password") String  pwd);

    @HTTP(method = "POST",path = "get" ,hasBody = true)   // 表示HTTP下的GET请求，拼接get路径
    Call<ResponseBody> http2(@Query("username") String userName , @Query("password") String  pwd);

   //三、@GET请求下的多参处理
    @GET("get")   // Map ==HashMap
    Call<ResponseBody> get2(@QueryMap   Map<String ,String > map);
//=================================================
 /*
    四、POST方法请求+使用Body参数注解：不需要@FormUrlEncoded
     TestFormBody类测试
  */
    @POST("post")
    Call<ResponseBody>  postBody (@Body RequestBody requestBody);//参数为请求体而不是响应体

    /*
    五、使用 POST方法注解 + Path+Header参数注解：需要加上 @FormUrlEncoded注解
        TestPathHeader类测试
     */
     @POST("{id}") // {id} == "post/get" 即【@POST("post")==@POST("{id}") 】
     @FormUrlEncoded
    Call<ResponseBody>  postPathHeader (@Path("id") String path, @Header("os") String header ,
                                    @Field("username") String userName , @Field("password") String  pwd);

    /*
       六、使用Headers+PSOT方法注解的请求类型
       TestHeraders类测试
     */
    @Headers({"os:android","version:1.0"}) //{"a:1","b:2"}多个json数据
    @POST("post")
    Call<ResponseBody> postWithHeaders(); //该方法没有参数

    /*
       七、使用POST方法注解 + URL参数注解
       TestURL类测试
     */
    @POST //注意：这里没指定小的请求方法，但后面URL地址必须给出小写的post
    Call<ResponseBody> postURL(@Url String url);




}
//第一个注解括号为 KEY，必须全部小写
// 第二个形参，有多个或者整体
//后面调用该postPath（）方法传入真实的Value值

/* {"a:1","b:2"}多个json数据   {"K":"V","K2"："V2"}
//  "files": {}
   "headers": {     ==>  【   "xxxx" : {"xx":"xx","xx":"xx"}  】
    "Accept-Encoding": "gzip",
    "Content-Length": "0"
    }
 */