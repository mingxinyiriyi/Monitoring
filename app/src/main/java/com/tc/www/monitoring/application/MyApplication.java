package com.tc.www.monitoring.application;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.tc.www.monitoring.dao.tool.GreenDaoTool;

/**
 * Created by Administrator on 2016/11/3.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    private static Context mcontext;

    public static Context getContent(){
        return  mcontext;
    }

    public static MyApplication getInstance(){
        return instance;
    }

    public static boolean hasNetWork(){
        return instance.checkIfHasNetwork();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mcontext = getApplicationContext();
        GreenDaoTool.getInstance();
    }

    private boolean checkIfHasNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Service.CONNECTIVITY_SERVICE);
        NetworkInfo net = cm.getActiveNetworkInfo();
        return net != null && net.isConnected();
    }
}
