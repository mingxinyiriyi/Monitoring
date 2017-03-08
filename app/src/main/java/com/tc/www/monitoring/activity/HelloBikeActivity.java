package com.tc.www.monitoring.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tc.www.monitoring.R;
import com.tc.www.monitoring.application.MyApplication;
import com.tc.www.monitoring.broadcast.HelloBikeReceiver;
import com.tc.www.monitoring.http.OpenAppUtil;

import java.util.Date;

public class HelloBikeActivity extends AppCompatActivity {
    private long wakeUpTime=1000*60;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_bike);
        Button button = (Button) findViewById(R.id.helloBike);
//        Intent intent = new Intent(this, BikeService.class);
//        startService(intent);
        setTime(MyApplication.getContent(),wakeUpTime);
        OpenAppUtil.openApp("com.jingyao.easybike", "com.jingyao.easybike.presentation.ui.activity.SplashActivity",HelloBikeActivity.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                System.exit(0);
//                android.os.Process.killProcess(android.os.Process.myPid()) ;
//                android.os.Process.killProcess(android.os.Process.myPid()) ;
                System.out.println();
                setTime(MyApplication.getContent(),wakeUpTime);
            }
        });
    }
    public void setTime(Context context,long timeInMiillis){
        Date date=new Date();
        long startTime=date.getTime()+28*60*1000;
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("android.alarm.demo.action");
        PendingIntent sendIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP,startTime,timeInMiillis,sendIntent);
    }
    private void playMusic(){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sunshine);
        mediaPlayer.start();
    }

    public void oneTime(Context context){
         alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
         Intent intent = new Intent(context, HelloBikeReceiver.class);
         alarmIntent = PendingIntent.getBroadcast(context,0,intent,0);
         alarmManager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+60*60*1000,alarmIntent);
    }

}
