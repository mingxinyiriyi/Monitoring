package com.tc.www.monitoring.broadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.tc.www.monitoring.service.RoombindService;

import java.util.Calendar;

/**
 * 时钟广播接收器
 */
public class AlarmReceiver extends WakefulBroadcastReceiver {
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        try{

            Intent service = new Intent(context, RoombindService.class);
            startWakefulService(context,service);
        }catch (UnsupportedOperationException e){
            throw new UnsupportedOperationException("接受后台服务时钟异常");
        }
    }
    public void setAlarm(Context context){
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context,0,intent,0);
        //设置时间
        Calendar calendar = Calendar.getInstance();
        calendar.getTimeInMillis();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 30);
        long startTime = SystemClock.currentThreadTimeMillis();
        //使用AlarmMagage的setRepeating的方法，定时执行
        alarmManager.setRepeating(AlarmManager.RTC,calendar.getTimeInMillis(),1*1000,alarmIntent);

    }
}
