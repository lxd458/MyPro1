package com.example.mypro1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.RoundedCorner;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.bumptech.glide.load.resource.bitmap.Rotate;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

public class MainActivity extends AppCompatActivity {
/*
   完成GLIDE图片的加载
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView img= findViewById(R.id.iv);

     //请求选项 : 检测URL地址，给出占位符的使用
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.hold)
                    .error(R.drawable.error)
                  .fallback(R.drawable.empty)
                  .override(500,500);
        //创建并设置一个可拉伸交叉衰减系数
        DrawableCrossFadeFactory.Builder factory = new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true);

        Glide.with(this)
                //加载
                .load("https://pics2.baidu.com/feed/359b033b5bb5c9ea2ee55bd37a6ab6083af3b343.jpeg?token=cf69339ae23766422344a4161be8f9e5&s=A72ED2044C6A22071EAB7AD10300E09C")
                //占位符应用
                .apply(requestOptions)
                //交叉淡入的动画效果
                .transition(DrawableTransitionOptions.withCrossFade(factory))
                //变换效果：new CircleCrop()圆角图片/new RoundedCorners(80)直角图片 / new GranularRoundedCorners(90,50,50,90)设置四个角/ new Rotate(90)旋转90度
                .transform(new Rotate(90))
                .into(img);//导入iv
        /* RuntimeException: setDataSource failed: status = 0x80000000
         Glide.with(Fragment/View/Context/Activity)
                .load(url) 选中网络图片，右键属性，复制URL地址一栏
                .into(ImageView);
         */

        //方法二，生成一个Glide API 只需创建一个API类继承AppGlideModule ,添加@GlideModule注解
       //凡是新建的类,必须先加载编译一次才会生效，再进行类和方法的调用。
       GlideApp.with(this).load("https://pics4.baidu.com/feed/3bf33a87e950352ac9cdb484ff10fbfab3118b6d.jpeg?token=a10b95ac24b47472386ff831da6629b6&s=39FD66863EA0172E3AAEE5260300F053")
               .defaultImg()
               .into(img);


    }
}
