package com.tc.www.monitoring.http;

import com.tc.www.monitoring.application.MyApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tc.www.monitoring.application.MyApplication.getContent;

/**
 * Created by Administrator on 2016/11/22.
 */

public class Injector {
    private static final String CACHE_CONTROL = "Cache-Control";
    private static final long CACHE_SIZE = 10*1024*1024l;

    public static Retrofit provideRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(provideOkhttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    //返回okHttpClient客户端
    private static OkHttpClient provideOkhttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(priovideOfflineCacheIntercetor())
                .addNetworkInterceptor(priovideCacheIntercetor())
                .cache(provideCache())
                .build();
    }


    //提供缓存
    private static Cache provideCache() {
        Cache cache = null;
        try {
            cache = new Cache(new File(getContent().getCacheDir(),"http-catch"),CACHE_SIZE);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cache;
    }
    //在线缓存拦截器
    private static Interceptor priovideOfflineCacheIntercetor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                //没有网络
                if(!MyApplication.hasNetWork()){
                    CacheControl cacheControl =  new CacheControl.Builder()
                            .maxAge(7, TimeUnit.DAYS)
                            .build();
                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }
                return chain.proceed(request);
            }
        };
    }
    //缓存拦截器
    private static Interceptor priovideCacheIntercetor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(2,TimeUnit.MINUTES)
                        .build();

                return response.newBuilder().
                        header(CACHE_CONTROL,cacheControl.toString())
                        .build();
            }
        };
    }


}
