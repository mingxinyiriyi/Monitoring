package com.tc.www.monitoring.http;

import com.tc.www.monitoring.caches.NetCache;
import com.tc.www.monitoring.interceptors.LogInterceptor;
import com.tc.www.monitoring.interceptors.PriovideOfflineCacheIntercetor;

import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/31.
 * 网络请求工具类
 */

public class HttpUtils {
    public static final int CACHE_SIZE = 10 * 1024 * 1024;
    public static final String CACHE_DIR = "httpCache";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    //get请求获取
    public static String getStringByUrl(String url) throws Exception{
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            return response.body().string();
        }
        return null;
    }
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    //post请求获取
    public static String  postStringByUrl(String url,String json) throws Exception{
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            return response.body().string();
        }
        return null;
    }
    //有缓存的
    public static String okHttp3Intercepetor(){
        String responseStr="";
        Cache cache =new NetCache().getCache();
        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new LogInterceptor())
                    .addInterceptor(new PriovideOfflineCacheIntercetor())
                    .cache(cache)
                    .build();

            Request request = new Request.Builder()
                    .url(Constant.httpUrl+"test/getRoomListById")
                    .header("User-Agent", "OkHttp Example")
                    .build();
            Response response = client.newCall(request).execute();
            responseStr = response.body().string();
            System.out.println("返回的信息："+responseStr);
            response.body().close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseStr;
    }


}
