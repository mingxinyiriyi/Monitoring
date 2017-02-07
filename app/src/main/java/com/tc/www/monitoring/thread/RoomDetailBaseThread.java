package com.tc.www.monitoring.thread;

import android.os.Handler;
import android.os.Message;

import com.tc.www.monitoring.R;


/**
 * Created by Administrator on 2016/11/7.
 */

public class RoomDetailBaseThread extends Thread{
    private Handler handler;
    private int id;

    public RoomDetailBaseThread(int id,Handler handler) {
        this.id = id;
        this.handler = handler;
    }

    @Override
    public void run() {
        switch (id){
            case R.id.ups:
                Message msg1 = new Message();
                msg1.what = R.id.ups;
                handler.sendMessage(msg1);
                break;
            case R.id.air:
                Message msg2 = new Message();
                msg2.what = R.id.air;
                handler.sendMessage(msg2);
                break;
            case R.id.tem:
                Message msg3 = new Message();
                msg3.what = R.id.tem;
                handler.sendMessage(msg3);
                break;
            case R.id.openclose:
                Message msg4 = new Message();
                msg4.what = R.id.openclose;
                handler.sendMessage(msg4);
                break;

        }

    }
}
