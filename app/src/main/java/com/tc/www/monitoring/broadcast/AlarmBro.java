package com.tc.www.monitoring.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

import com.tc.www.monitoring.R;
import com.tc.www.monitoring.activity.AlarmActivity;
import com.tc.www.monitoring.application.MyApplication;


/**
 * Created by Administrator on 2017/2/7.
 */

public class AlarmBro extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action == AlarmActivity.INTENT_ALARM_LOG){
            Log.d("AlarmBro","日志");
        }
    }
    public void playMusic(){
//        Uri myUri;
//        MediaPlayer mediaPlayer = new MediaPlayer();
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        mediaPlayer.setDataSource(MyApplication.getContent(),myUri);
//        try {
//            mediaPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
///        mediaPlayer.start();
        MediaPlayer.create(MyApplication.getContent(), R.raw.light);
    }
}
