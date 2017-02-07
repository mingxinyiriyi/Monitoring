package com.tc.www.monitoring.caches;

import com.tc.www.monitoring.application.MyApplication;

import java.io.File;

import okhttp3.Cache;

/**
 * Created by Administrator on 2016/11/24.
 */

public class NetCache {
    //得到缓存
    public Cache getCache(){
        Cache cache = null;
        try{
            cache = new Cache(new File(MyApplication.getInstance().getCacheDir(),"http-cache"),10*1024*1024);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cache;

    }
}
