package com.tc.www.monitoring.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tc.www.monitoring.R;

public class IntentActivity extends AppCompatActivity {
    private String textMsg="haha";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        sendMusicIntent();
        sendIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
    }
    //文字
    private void sendIntent() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,textMsg);
        sendIntent.setType("text/plain");
        if(sendIntent.resolveActivity(getPackageManager())!= null){
            startActivity(sendIntent);
        }
    }
    //音乐
    private void sendMusicIntent() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("audio/*");
        if(sendIntent.resolveActivity(getPackageManager())!= null){
            startActivity(sendIntent);
        }
    }
}
