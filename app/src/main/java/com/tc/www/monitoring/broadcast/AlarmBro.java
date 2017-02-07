package com.tc.www.monitoring.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import com.tc.www.monitoring.R;
import com.tc.www.monitoring.activity.AlarmActivity;
import com.tc.www.monitoring.application.MyApplication;


/**
 * Created by Administrator on 2017/2/7.
 */

public class AlarmBro extends BroadcastReceiver {
    static MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action == AlarmActivity.INTENT_ALARM_LOG){
            Toast.makeText(MyApplication.getContent(),"出来",Toast.LENGTH_SHORT).show();
            playMusic();
            Log.d("AlarmBro","日志");
        }
    }
    public static void playMusic(){
//        Uri myUri;
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        mediaPlayer.setDataSource(MyApplication.getContent(),myUri);
//        try {
//            mediaPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
///        mediaPlayer.start();
        mediaPlayer = MediaPlayer.create(MyApplication.getContent(), R.raw.sunshine);
        mediaPlayer.start();
    }
    public static void stopMusic(){
        mediaPlayer.stop();
    }

}
