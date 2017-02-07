package com.tc.www.monitoring.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;

import com.tc.www.monitoring.R;
import com.tc.www.monitoring.broadcast.AlarmBro;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {
    public static final String INTENT_ALARM_LOG = "intent_alarm_log";
    private CheckBox alarmCheckBox;
    private Button alarmButton;
    private TimePicker timePicker;
    private Button alarmCancleBtn;

    private int hour;
    private int minute;
    private AlarmManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        alarmCheckBox = (CheckBox) findViewById(R.id.alarmRep);
        alarmButton = (Button) findViewById(R.id.alarmSet);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmCancleBtn = (Button) findViewById(R.id.alarmCancle);
        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= 23){
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();
                }else{
                    hour = timePicker.getCurrentHour();
                    minute = timePicker.getCurrentMinute();
                }
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,hour);
                calendar.set(Calendar.MINUTE,minute);
                calendar.set(Calendar.SECOND,0);
                Intent intent = new Intent(INTENT_ALARM_LOG);
                PendingIntent pi = PendingIntent.getBroadcast(AlarmActivity.this,0,intent,0);
                if(!alarmCheckBox.isChecked()){
                    am.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pi);
                }else{
                    long curtime = 60 * 1000;
                    am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),curtime,pi);
                }
            }
        });
        alarmCancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmBro.stopMusic();
            }
        });
    }


}
