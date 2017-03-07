package com.tc.www.monitoring.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tc.www.monitoring.R;
import com.tc.www.monitoring.application.MyApplication;

public class HelloBikeActivity extends AppCompatActivity {
    private long wakeUpTime=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_bike);
        Button button = (Button) findViewById(R.id.helloBike);
//        OpenAppUtil.openApp("com.jingyao.easybike", "com.jingyao.easybike.presentation.ui.activity.SplashActivity",HelloBikeActivity.this);
//        Intent intent = new Intent(this, BikeService.class);
//        startService(intent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                System.exit(0);
//                android.os.Process.killProcess(android.os.Process.myPid()) ;
                setTime(MyApplication.getContent(),wakeUpTime*6);
            }
        });
    }
    public void setTime(Context context,long timeInMiillis){
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("android.alarm.demo.action");
        PendingIntent sendIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        int interval = 60 * 1000;
        am.setRepeating(AlarmManager.RTC_WAKEUP,timeInMiillis,interval,sendIntent);
    }
    private void playMusic(){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sunshine);
        mediaPlayer.start();
    }

}
