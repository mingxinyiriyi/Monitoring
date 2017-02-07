package com.tc.www.monitoring.http;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by Administrator on 2016/11/17.
 */

public class PollingUtils {
    //开启轮询服务

    /**
     *
     * @param context
     * @param seconds 设定时间 单位是 秒
     * @param cls     指定的类名
     * @param action 完整的service的类名
     */
    public static void startPollingService(Context context,int seconds,Class<?> cls,String action) {
        //获取AlarmManage系统服务
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //包装需要执行的service和Intent
        Intent intent = new Intent(context,cls);
//        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        //触发服务的起始时间
        long startTime = SystemClock.elapsedRealtime();

        //使用AlarmMagage的setRepeating的方法，定时执行
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME,startTime,seconds*1000,pendingIntent);
    }
    // 关闭轮询服务
    public static void stopPollingService(Context context,Class<?> cls,String action){
        //获取AlarmManage系统服务
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //包装需要执行的service和Intent
        Intent intent = new Intent(context,cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        //使用AlarmMagage的cancel取消轮询服务
        manager.cancel(pendingIntent);
    }

}
