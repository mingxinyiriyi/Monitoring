package com.tc.www.monitoring.thread;

import android.os.Handler;
import android.os.Message;

import com.tc.www.monitoring.model.Room;
import com.tc.www.monitoring.model.Ups;

/**
 * Created by Administrator on 2016/11/8.
 */
public class UpsThread extends Thread {
    private Room room;
    private Ups ups = new Ups();
    private Handler handler;

    public UpsThread(Room room,Handler handler) {
        this.room = room;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            ups.setId("1");
            ups.setTem("29.3C");
            ups.setBanormal(2);
            ups.setBuzzerOpen(1);
            Message message = new Message();
            message.obj=ups;
            handler.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
