/**
  * Copyright 2022 bejson.com 
  */
package com.example.retrofit.bean;

/*
  根据谷歌扩展程序Talend API ，设置url , post请求 ，设置参数，等到JSON数据

   把json数据放到bejson工具进行转换，添加所需类名和包名！！！
   生成JAVABean实体类

   再进行下载解压，复制过来，覆盖替换

 */
public class BaseResponse {

    private Data data;
    private int errorCode;
    private String errorMsg;
    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }

    public void setErrorCode(int errorCode) {
         this.errorCode = errorCode;
     }
     public int getErrorCode() {
         return errorCode;
     }

    public void setErrorMsg(String errorMsg) {
         this.errorMsg = errorMsg;
     }
     public String getErrorMsg() {
         return errorMsg;
     }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}