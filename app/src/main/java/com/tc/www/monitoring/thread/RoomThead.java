package com.tc.www.monitoring.thread;


import android.os.Handler;
import android.os.Message;

import com.tc.www.monitoring.model.Room;

import java.util.List;

/**
 * Created by ming on 2016-10-28.
 */

public class RoomThead extends Thread{
    private List<Room> equiListData;
    private Handler handler;

    public RoomThead(List equiListData,Handler handler) {

        this.equiListData = equiListData;
        this.handler = handler;
    }
    //网络请求
    @Override
    public void run() {
        try {
            /**
            Room room1 = new Room();
            room1.setId("1");
            room1.setName("南京机房");
            Room room2 = new Room();
            room2.setId("2");
            room2.setName("长乐供电机房旧大楼B#2楼");
            Room room3 = new Room();
            room3.setId("3");
            room3.setName("长乐供电机房新大楼A#15楼");

            equiListData.add(room1);
            equiListData.add(room2);
            equiListData.add(room3);
             **/
            for (int i = 0; i<=20; i++ ){
                Room room = new Room();
                room.setId("1"+i);
                room.setName("list"+i);
                equiListData.add(room);
            }

            Message message = new Message();
            message.obj = equiListData;
            handler.sendMessage(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
