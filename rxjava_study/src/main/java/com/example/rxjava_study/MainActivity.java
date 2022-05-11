package com.example.rxjava_study;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private final  String TAG = MainActivity.class.getSimpleName();
    private final static  String PATH = "http://picl.win4000.com/wallpaper/c/53cdd1f7c1f21.jpg";
    private ProgressDialog progressDialog;//加载框
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.img);
    }


    public void load(View view) {
        //观察者设计模式
        //TODO 第二步执行：关调用观察者模式的jjust方法 //起点注意导包： import io.reactivex.Observable; 先添加rxjava依赖
        Observable.just(PATH)
                //TODO 第三步执行：执行中间需求，图片下载 PATH-->Bitmap
                .map( new Function<String, Bitmap>()  {
                    @NonNull //非空注解
                    @Override
                    public Bitmap apply(@NonNull String path) throws Exception { //String path ==PATH
                        try {
                            //1.向服务器发起请求
                            URL url = new URL(path);//把地址拿去请求
                            //2.采用Http类型连接,打开服务连接
                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                            urlConnection.setConnectTimeout(5000);//设置超时时间
                            //3.获取响应状态码
                            int code = urlConnection.getResponseCode();
                            //4.判断是否响应成功
                            if (code == urlConnection.HTTP_OK) { //HTTP_OK==200
                                //5.获取字节输入流
                                InputStream inputStream = urlConnection.getInputStream();
                                //6.将字节转换为Bitmap
                                //inputStream. 不能作为变量引用，就只能拿来做参数
                                //通过BitmapFactory源码类调用里面的解码流方法
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                return bitmap;
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    } //图片加载徐娅Bitmap ;重写apply方法
                })
                //分配异步线程，进行图片下载操作://(类名.方法)
                .subscribeOn(Schedulers.io())
                //分配UI主线程:
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        progressDialog = new ProgressDialog(MainActivity.this);//MainActivity.this == context == this
                        progressDialog.setTitle("Rxjava图片加载中");//设置标题
                        progressDialog.show();//调用本方法


                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);//设置图片的数据
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (progressDialog != null ) {
                            progressDialog.dismiss();
                        }
                    }
                });

      }
}
                  //TODO 第一步执行：关联订阅
//                .subscribe(
//                        new Observer<Bitmap>() {
//                        @Override
//                      public void onSubscribe(Disposable d) {
//                           //订阅成功:activity销毁后并没有处理Dialog的退出。
//                          progressDialog = new ProgressDialog(MainActivity.this);//MainActivity.this == context == this
//                          progressDialog.setTitle("Rxjava图片加载中");//设置标题
//                          progressDialog.show();//调用本方法
//
//                      }
//                        //TODO 第四步：
//                      @Override
//                      public void onNext(Bitmap bitmap) {
//                          //上一层给的响应和下一层需要执行类型
//                          imageView.setImageBitmap(bitmap);//设置图片的数据
//
//                      }
//
//                      @Override
//                      public void onError(Throwable e) {
//
//
//                      }
//                    //TODO 第五步 整个链条全部结束:只需要在activity销毁时关闭Dialog就好。
//                      @Override //原因是 activity 和dialog同时显示 要先dialog.dismiss() 后activity.finish() 如果直接finish() 会出错 但不影响功能。
//                      public void onComplete() {
//                                    if(progressDialog!=null ){
//                                        progressDialog.dismiss();
//                                        //隐藏加载框
//                                    }
//                  }
//
//
//             });
/*
dialog和Activity之间是异步。因为在dialog调用dismiss或show之前，需要确定Activity没有被finish；
换言之，不能先调用finish再调用dismiss。否则会导致该错："Activity has leaked window com.android.internal.policy.impl.PhoneWindow$Deco...."
 */



    



/*UndeliverableException: +CalledFromWrongThreadException +
.MainActivity has leaked window +

安卓看法ViewRootImpl$CalledFromErrorThreadException：
只有创建视图层次结构的原始线程才能接触其视图。

Activity com.example.rxjava_study.MainActivity has leaked window com.android.internal.policy.impl.PhoneWindow$DecorView
解析：MainActivity泄露了Windows com
 */