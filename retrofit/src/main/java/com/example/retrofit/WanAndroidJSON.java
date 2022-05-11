package com.example.retrofit;

import com.example.retrofit.bean.BaseResponse;

import io.reactivex.rxjava3.core.Flowable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/*
Call<数据类型>
 */
public interface WanAndroidJSON {  //对应测试中的TestWanAndroidJSON

    /* //玩安卓的登录模块
      测试 【JSON生成Java实体类 + 反序列化效果】通过new Gson().fromJson() ：
      返回值：Call<ResponseBody> -----> Response<ResponseBody>
     */

    @POST("user/login")
    @FormUrlEncoded
    Call<ResponseBody> login(@Field("username") String userName, @Field("password") String pwd);

  /* //玩安卓的登录模块
     测试转换器：
     返回值：   Call<BaseResponse>----------> Response<BaseResponse>
   */
    @POST("user/login")
    @FormUrlEncoded
    Call<BaseResponse> login2(@Field("username") String userName, @Field("password") String pwd);



    /* RXjava3==>Flowable类型
      添加两个依赖，【适配器】可以解决回调地狱等问题
      Flowable<BaseResponse>
      Flowable<ResponseBody>
      */
    @POST("user/login") //登录
    @FormUrlEncoded
    //返回类型： Flowable<BaseResponse>
    Flowable<BaseResponse> login3(@Field("username") String userName, @Field("password") String pwd);

    @GET("lg/collect/list/{pageNum}/json") //文章页数
    //返回类型： Flowable<ResponseBody>
    Flowable<ResponseBody>  getArticle(@Path("pageNum") int  pageNum);


    /*-
      文件的上传与下载
     */
    @POST("post") // String value() default "";表示要声明默认值
    @Multipart
    Call<ResponseBody>  upload(@Part MultipartBody.Part file);
   // Call<ResponseBody>  upload2(@PartMap MultipartBody.Part file);

    //@GET("get") 不能 @Url 一起使用
    @Streaming //避免下载文件过大，造成堆栈溢出
    @GET
    Call<ResponseBody> download(@Url String url);
    @Streaming
    @GET
   Flowable<ResponseBody> downloadRXjava3(@Url String url);
}
