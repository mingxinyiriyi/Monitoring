package com.tc.www.monitoring.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.tc.www.monitoring.R;
import com.tc.www.monitoring.broadcast.AlarmReceiver;

import java.util.Timer;

//绑定的方式做服务
public class RoombindService extends Service {
    public static final String ACTION = "com.tc.www.monitoring.service.RoombindService";
    //通知栏管理器
    private NotificationManager mNM;
    //通知的信息
    private int NOTIFICATION = R.string.title_activity_notification;
    //绑定接口 返回本对象的实例
    public class RoombindBinder extends Binder{
        public RoombindService getRoombindService(){
            return RoombindService.this;
        }
    }
    //创建常量的的绑定类
    private final IBinder mBinder = new RoombindBinder();
    //定时器
    private Timer timer;

    //创建的时候执行，第二次进来就不执行；
    @Override
    public void onCreate() {
//        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        setTimer();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showNotification();
        AlarmReceiver.completeWakefulIntent(intent);
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return  mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
//        mNM.cancel(NOTIFICATION);
//        Toast.makeText(this,"服务停止",Toast.LENGTH_SHORT).show();
    }
    //展示通知栏
    private void showNotification() {
//        CharSequence c = getText(R.string.title_activity_notification);
//        //发送Intent到Activity对象
//        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,new Intent(this, RoomActivity.class),0);
//        Notification notification = new Notification.Builder(this)
//                .setSmallIcon(R.drawable.red)
//                .setTicker(c)
//                .setWhen(System.currentTimeMillis())
//                .setContentTitle(getText(R.string.title_activity_notification))
//                .setContentText(getText(R.string.content_activity_notification))
//                .setContentIntent(pendingIntent)
//                .build();
//        //发送通知
//        mNM.notify(NOTIFICATION,notification);
        System.out.println("出现的重复");

    }

    public void setTimer(){
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                showNotification();
//            }
//        },5000,200);
    }
}
