package com.example.mypro1;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.BaseRequestOptions;

/*
 避免频繁调用
 */
@GlideExtension  //Glide扩展注解
public class MyAppExtension {
    //RequestOptionsExtensions must be public, with private constructors and only static methods.
    private MyAppExtension() { //必须私有构造
    }
    @GlideOption //Glide选项
    public static BaseRequestOptions<?> defaultImg( BaseRequestOptions<?> options){
        return options
                .placeholder(R.drawable.hold)
                .error(R.drawable.error)
                .fallback(R.drawable.empty);
    }
}
