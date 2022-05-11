package com.example.retrofit;

import com.example.retrofit.bean.BaseResponse;
import com.google.gson.Gson;

import org.junit.Test;
import org.reactivestreams.Publisher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
//测试只是为了方便快捷，而不是在JAva里面，需要加载，打包，运行模拟器
public class TestWandroidJSON {

    /*
      玩安卓测试：JSON生成Java实体类 +【反序列化】效果
      用户名：顾北辰
      密码：admin123456
       Response<ResponseBody>
     */
    @Test
    public void testJSon() throws IOException { //https://www.httpbin.org/

     Retrofit   retrofit1 = new Retrofit.Builder().baseUrl("https://www.wanandroid.com/").build();
     WanAndroidJSON  json = retrofit1.create(WanAndroidJSON.class);//注意接口名不一样了
        //调用接口login()方法
     Response<ResponseBody> response =  json.login("顾北辰", "admin123456").execute();
           String string = response.body().string();
        System.out.println(string); //需要bean类生成toString
        /*
          有了JavaBean类之后，进行【手动json数据转换】，需要用到Gson.达到【反序列化】效果
          1.再build.gradle里面设置依赖才行
         */
        BaseResponse baseResponse = new Gson().fromJson(string, BaseResponse.class);//Class<? extends Obj> class
        System.out.println(baseResponse);
        /* 打印如下：
        BaseResponse{data=Data{admin=false, chapterTops=[], coinCount=21, collectIds=[22387, 8857], email='', icon='', id=129645, nickname='顾北辰', password='', publicName='顾北辰', token='', type=0, username='顾北辰'}, errorCode=0, errorMsg=''}
         */
    }

    /*
       测试转换器：Response<BaseResponse>
     */
      @Test
      public  void testConverter() throws IOException {
           Retrofit   retrofit2  = new Retrofit.Builder()
                                     .baseUrl("https://www.wanandroid.com/")
                                      //添加转换器：可以自动数据转换
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
          WanAndroidJSON json = retrofit2.create(WanAndroidJSON.class);
          //调用接口login2()方法
          Response<BaseResponse> response = json.login2("顾北辰", "admin123456").execute();
          System.out.println(response.body());//不需要.string
/*
BaseResponse{data=Data{admin=false, chapterTops=[], coinCount=21, collectIds=[22387, 8857], email='', icon='', id=129645, nickname='顾北辰', password='', publicName='顾北辰', token='', type=0, username='顾北辰'}, errorCode=0, errorMsg=''}
 */
      }

      /* 第三个：
       在转换器的基础上添加【Rxjava3适配器】：【登录+获取文章第一页】
       */
      Map<String,List<Cookie>> cookies = new HashMap<>();
      @Test
    public void  testAdapter(){
          Retrofit retrofit3 = new Retrofit.Builder().baseUrl("https://www.wanandroid.com/")
                  //定制自己的OKP
                  .callFactory(new OkHttpClient.Builder()
                          .cookieJar(new CookieJar() {
                              @Override
                              public void saveFromResponse(HttpUrl url, List<Cookie> list) {
                                  cookies.put(url.host(),list);
                              }
                              @Override
                              public List<Cookie> loadForRequest(HttpUrl url) {
                                  List<Cookie> cookies = TestWandroidJSON.this.cookies.get(url.host());//get(Obj)
                                  //特殊返回处理：避免空值为null
                                  return cookies == null ? new ArrayList() : cookies;
                              }
                          })
                          .build())
                  .addConverterFactory(GsonConverterFactory.create())//添加转换器
                  .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) //添加适配器
                  .build();
          final WanAndroidJSON json3 = retrofit3.create(WanAndroidJSON.class);
          json3.login3("顾北辰", "admin123456")
                        //.flatMap()获取文章：Flowable<ResponseBody>
                  .flatMap(new Function<BaseResponse, Publisher<ResponseBody>>() {
                      @Override
                      public Publisher<ResponseBody> apply(BaseResponse baseResponse) throws Throwable {
                          return json3.getArticle(0);
                      }
                  })
                   //记住是Schedulers类 不是Scheduler对象
                  .observeOn(Schedulers.io())
                  .subscribeOn(Schedulers.newThread())
                  .subscribe(new Consumer<ResponseBody>() {
                      @Override
                      public void accept(ResponseBody responseBody) throws Throwable {

                      }
                  });

              while (true){}
      }


      /* 第四：
         文件上传
       */

      @Test
    public  void  testUpLoadFile() throws IOException {
          Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.wanandroid.com/").build();
          //创建并引入接口
          WanAndroidJSON json4 = retrofit.create(WanAndroidJSON.class);
          File file1 = new File("C:\\Users\\HP\\Desktop\\1.txt");//需要先在桌面生成1.txt文件
          // new MultipartBody.Builder().addFormDataPart(
          MultipartBody.Part part = MultipartBody.Part.createFormData("file1", "1.txt", RequestBody.create(MediaType.parse("text/plain"), file1));
          Call<ResponseBody> call = json4.upload(part);
          Response<ResponseBody> response = call.execute();
          System.out.println(response.body().string());
      }


      /*
         第五： 文件下载
       */
      Response<ResponseBody> response = null;
      @Test
    public void testDownLoad()  {
          Retrofit retrofit = new Retrofit.Builder().baseUrl("https://code.angularjs.org/").build();
          WanAndroidJSON json5 = retrofit.create(WanAndroidJSON.class);

          Call<ResponseBody> call= json5.download("https://code.angularjs.org/snapshot/One_Way.png");

          try {
              response = call.execute(); //同步
              //获取下载内容【读入】 ResponseBody ===> response.body()
              InputStream inputStream = response.body().byteStream();
              //创建字节输出流【写出】
              FileOutputStream fos = new FileOutputStream("C:\\Users\\HP\\Desktop\\a.png");//指定目录，会拒绝访问
              //读取，输入数组
              int len;
              byte[] bytes = new byte[4096];//读数组的字节，在判断长度是否为-1
              while (  (len = inputStream.read(bytes))  !=  -1){ //while(boolean)
                  fos.write(bytes,0,len);
                  System.out.println("下载完成了，看桌面");
              }
              fos.close();
              inputStream.close();
          } catch (IOException e) {
              e.printStackTrace();
          }

      }
     @Test
    public void testDownLoadRxjava(){
         Retrofit retrofit = new Retrofit.Builder().baseUrl("https://code.angularjs.org/").build();
         WanAndroidJSON json6 = retrofit.create(WanAndroidJSON.class);
         Flowable<ResponseBody> rXjava3 = json6.downloadRXjava3("https://code.angularjs.org/snapshot/One_Way.png");
         rXjava3.map(new Function<ResponseBody, File>() { //实现apply方法
             @Override
             public File apply(ResponseBody responseBody) throws Throwable {
                 InputStream inputStream = responseBody.byteStream();//获取字节流数据
                 File file = new File("C:\\Users\\HP\\Desktop\\a.png");//生成file方便返回值
                 FileOutputStream fos = new FileOutputStream(file);//传入file
                 //读取，输入数组
                 int len;
                 byte[] bytes = new byte[4096];//读数组的字节，在判断长度是否为-1
                 while (  (len = inputStream.read(bytes))  !=  -1){ //while(boolean)
                     fos.write(bytes,0,len);
                     System.out.println("下载完成了，看桌面");
                 }
                 fos.close();
                 inputStream.close();
                 return file;
             }
         }).subscribe(new Consumer<File>() { //消费者的接受方法
             @Override
             public void accept(File file) throws Throwable {
                 //

             }
         });
         while (true){}


     }

}
