package com.tc.www.monitoring.interceptors;

import com.tc.www.monitoring.application.MyApplication;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/23.
 */

public class PriovideOfflineCacheIntercetor implements Interceptor {
    //判断是否有网络拦截器
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if(!MyApplication.hasNetWork()){
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build();
            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();
        }
        return chain.proceed(request);
    }
}
