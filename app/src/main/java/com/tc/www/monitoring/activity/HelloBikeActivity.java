package com.tc.www.monitoring.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tc.www.monitoring.R;

public class HelloBikeActivity extends AppCompatActivity {

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
                android.os.Process.killProcess(android.os.Process.myPid()) ;
            }
        });
    }
}
