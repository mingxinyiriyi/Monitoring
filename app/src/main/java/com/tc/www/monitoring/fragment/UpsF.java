package com.tc.www.monitoring.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tc.www.monitoring.R;
import com.tc.www.monitoring.model.Room;
import com.tc.www.monitoring.model.Ups;
import com.tc.www.monitoring.thread.UpsThread;


public class UpsF extends Fragment {
    private Room room;
    private Ups ups;
    private View upsView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        init();
        this.getRoom();
        upsView = inflater.inflate(R.layout.fragment_ups, container, false);
        return upsView;
    }

    private void  init(){
        room = getRoom();
        final Handler handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                ups = (Ups) msg.obj;
                Style();
            }
        };
        UpsThread upsThread = new UpsThread(room,handler);
        upsThread.start();

    }
    //传过来的Room
    private Room getRoom() {
        Bundle bundle = getArguments();
        Room room = (Room) bundle.getSerializable("room");
        if(room != null){
            return room;
        }
        return null;
    }
    //页面的变化
    private void Style() {
        TextView v = (TextView) upsView.findViewById(R.id.tem);
        v.setText(ups.getTem());
        ImageView imageView = (ImageView) upsView.findViewById(R.id.buzzerOpen);
        if(ups.getBuzzerOpen()==1){
            imageView.setBackgroundResource(R.drawable.green);
        }else {
            imageView.setBackgroundResource(R.drawable.red);
        }
    }
}
