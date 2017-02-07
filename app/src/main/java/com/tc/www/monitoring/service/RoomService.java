package com.tc.www.monitoring.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.tc.www.monitoring.R;
import com.tc.www.monitoring.activity.LoginActivity;

public class RoomService extends Service {
    public RoomService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("服务创建的 时候：：：：");
//        getNotifacation();
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }
    private void getNotifacation(){
        android.support.v4.app.NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.red)
                        .setContentTitle("我的通知")
                        .setContentText("你好世界!");
        Intent intents = new Intent(this, LoginActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(LoginActivity.class);
        stackBuilder.addNextIntent(intents);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
        System.out.println("服务的的启动：：：：：：：：");
    }

    //执行start方式执行的任务
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        System.out.println("intent在service中的值"+intent.getData());
        System.out.println("service的值startId的值是"+startId);
        getNotifacation();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("我要销毁");
    }
}
