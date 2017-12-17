package com.zl.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by CBWL on 2017/5/28.
 */

public class OKHTTPUtil {
    private static int TIMEOUT = 3000;
    private static OkHttpClient okHttpClient = null;

    public static String get(String url) throws Exception {
        return get(url,null);
     }

     public static String getWithJessionid(String url,String jessionid) throws Exception {
        Map<String,String> headers = new HashMap<>();
         headers.put("Cookie","JSESSIONID=" + jessionid);
        return get(url,headers);
     }

     public static String postForm(String url,Map<String,String> params) throws Exception {
         RequestBody body = null;
          if(params == null){
             body = new FormBody.Builder().build();
         }else{
             FormBody.Builder builder = new FormBody.Builder();
             for(Map.Entry<String,String> entry : params.entrySet()){
                 builder.add(entry.getKey(),entry.getValue());
             }
             body = builder.build();
         }
         Request request = new Request.Builder().url(url).post(body).build();
         return execute(request);

     }

    private static String get(String url,Map<String,String> headers) throws Exception {
         Request request = null;
         if(headers == null){
             request = new Request.Builder().url(url).build();
         }else{
             Request.Builder builder = new Request.Builder().url(url);
             for(Map.Entry<String,String> entry : headers.entrySet()){
                 builder.addHeader(entry.getKey(),entry.getValue());
             }
             request = builder.build();
         }
         return execute(request);
     }
    private static String execute(Request request) throws Exception {
        OkHttpClient okHttpClient = createHttpClient();
        Call call = okHttpClient.newCall(request);
        Response response = null;
        String responseStr = "";
        try {
            response = call.execute();
            if(200 ==response.code()){
                responseStr = response.body().string();
                System.out.println("http请求 url:"+request.url().toString()+",响应:"+responseStr);
                return responseStr;
            }else{
                System.out.println("http请求错误 url:"+request.url().toString()+",errorcode:"+response.code()+",msg:{}"+response.message());
                throw new Exception(response.code()+":"+response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    private static OkHttpClient createHttpClient(){
        if(okHttpClient==null){
            synchronized (OKHTTPUtil.class){
                if(okHttpClient==null){
                    OkHttpClient.Builder builder = new OkHttpClient.Builder()
                            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                            .connectionPool(new ConnectionPool(10,10,TimeUnit.MINUTES))
                            .readTimeout(TIMEOUT, TimeUnit.SECONDS).retryOnConnectionFailure(true);
                    okHttpClient=builder.build();
                }
            }
        }
        return  okHttpClient;
    }
}
