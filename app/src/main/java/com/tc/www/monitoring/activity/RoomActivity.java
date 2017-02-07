package com.tc.www.monitoring.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tc.www.monitoring.R;
import com.tc.www.monitoring.broadcast.AlarmReceiver;
import com.tc.www.monitoring.fragment.RoomList;
import com.tc.www.monitoring.service.RoombindService;

public class RoomActivity extends AppCompatActivity {
    //片段管理器
    private FragmentManager fm;
    //片段事务管理
    private FragmentTransaction ft;
    //使用bind的方式使用服务
    private RoombindService roombindService = new RoombindService();
    //是否绑定
    private boolean mIsBound;

    //创建广播接收器
    AlarmReceiver alarmReceiver = new AlarmReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("机房监控");
        setSupportActionBar(toolbar);
        getIntent().getExtras();
        //开启服务
        doBindService();
//        PollingUtils.startPollingService(this,1,RoombindService.class,RoombindService.ACTION);
//        alarmReceiver.setAlarm(this);
        initView();
    }
    private void initView() {

        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.roomframe,new RoomList());
        ft.commit();

    }
    private ServiceConnection conn = new ServiceConnection() {
        //获取服务对象时候进行操作
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            roombindService = ((RoombindService.RoombindBinder) service).getRoombindService();
            System.out.println("服务开始连接");

        }
        //无法获取服务的时候进行操作
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            roombindService = null;

        }
    };
    //绑定服务
    private void doBindService(){
        //开启服务
        bindService(new Intent(this,RoombindService.class),conn, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }
    //解绑服务
    private void doUnBindService(){
        if(mIsBound){
            unbindService(conn);
            System.out.println("解绑成功");
        }
        mIsBound = false;
    }

    @Override
    protected void onDestroy() {
//        PollingUtils.stopPollingService(this,RoombindService.class, RoombindService.ACTION);
        doUnBindService();
        super.onDestroy();
    }
}
