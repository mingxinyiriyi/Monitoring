package com.tc.www.monitoring.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class HelloBikeReceiver extends BroadcastReceiver {
    public HelloBikeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
         if ("android.alarm.demo.action".equals(intent.getAction())) {
            //第1步中设置的闹铃时间到，这里可以弹出闹铃提示并播放响铃
            //可以继续设置下一次闹铃时间;
             System.out.println("输出的闹铃-----------");
            return;
        }
    }

}
