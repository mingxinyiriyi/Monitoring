package com.tc.www.monitoring.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.tc.www.monitoring.R;

public class HelloBikeService extends Service {
    public HelloBikeService() {
    }

    private void playMusic(){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sunshine);
        mediaPlayer.start();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("输出服务");
        playMusic();
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
