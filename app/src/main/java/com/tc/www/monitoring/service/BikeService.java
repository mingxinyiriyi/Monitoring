package com.tc.www.monitoring.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.tc.www.monitoring.R;

import java.util.Timer;
import java.util.TimerTask;

public class BikeService extends Service {
    //定时启动时间
    private long setTime = 28*60*1000;
    //定时重复时间
    private long recyTime = 1*60*1000;
    public BikeService() {
        System.out.println("输出服务");
        timer.schedule(timerTask,setTime,recyTime);
    }
    /*定时器*/
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            playMusic();
            System.out.println("输出定时器为10分钟");
            System.out.println("");
        }
    };
    private void playMusic(){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sunshine);
        mediaPlayer.start();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
