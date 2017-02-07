package com.tc.www.monitoring.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tc.www.monitoring.R;
import com.tc.www.monitoring.model.Room;
import com.tc.www.monitoring.thread.RoomDetailBaseThread;

import static com.tc.www.monitoring.R.id.ups;


public class RoomDetailBase extends Fragment {
    private Button upsButton,airButton,temButton,opencloseButton;
    private View baseView;
    //片段管理器
    private FragmentManager fm;
    //片段事务管理
    private FragmentTransaction ft;
    //传递值
    public Bundle bundle = new Bundle();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
         baseView = inflater.inflate(R.layout.fragment_room_detail_base,container,false);
        init();
        return baseView;
    }

    private Room getRoom() {
        Bundle bundle = getArguments();
        Room room = (Room) bundle.getSerializable("room");
        if(room != null){
            return room;
        }
        return null;
    }

    public void init(){
        upsButton = (Button) baseView.findViewById(ups);
        airButton = (Button) baseView.findViewById(R.id.air);
        temButton = (Button) baseView.findViewById(R.id.tem);
        opencloseButton = (Button) baseView.findViewById(R.id.openclose);
        final  Handler handler = new Handler(){
             @Override
             public void handleMessage(Message msg) {
                 switch (msg.what){
                     case ups:
                         upsButton.setBackgroundColor(getResources().getColor(R.color.blue_green));
                         airButton.setBackgroundColor(getResources().getColor(R.color.white));
                         temButton.setBackgroundColor(getResources().getColor(R.color.white));
                         opencloseButton.setBackgroundColor(getResources().getColor(R.color.white));
                         break;
                     case R.id.air:
                         airButton.setBackgroundColor(getResources().getColor(R.color.blue_green));
                         upsButton.setBackgroundColor(getResources().getColor(R.color.white));
                         temButton.setBackgroundColor(getResources().getColor(R.color.white));
                         opencloseButton.setBackgroundColor(getResources().getColor(R.color.white));
                         break;
                     case R.id.tem:
                         temButton.setBackgroundColor(getResources().getColor(R.color.blue_green));
                         airButton.setBackgroundColor(getResources().getColor(R.color.white));
                         upsButton.setBackgroundColor(getResources().getColor(R.color.white));
                         opencloseButton.setBackgroundColor(getResources().getColor(R.color.white));
                         break;
                     case R.id.openclose:
                         opencloseButton.setBackgroundColor(getResources().getColor(R.color.blue_green));
                         airButton.setBackgroundColor(getResources().getColor(R.color.white));
                         temButton.setBackgroundColor(getResources().getColor(R.color.white));
                         upsButton.setBackgroundColor(getResources().getColor(R.color.white));
                         break;
                 }
             }
         };
        RoomDetailBaseThread r = new RoomDetailBaseThread(ups,handler);
        r.start();
        upsshow();
        upsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoomDetailBaseThread r = new RoomDetailBaseThread(ups,handler);
                r.start();
                upsshow();
            }
        });

        airButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoomDetailBaseThread r = new RoomDetailBaseThread(R.id.air,handler);
                r.start();
               arishow();
            }
        });
        temButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoomDetailBaseThread r = new RoomDetailBaseThread(R.id.tem,handler);
                r.start();
                temshow();
            }
        });
        opencloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoomDetailBaseThread r = new RoomDetailBaseThread(R.id.openclose,handler);
                r.start();
                opencloseshow();
            }
        });


    }
    //ups界面
    public void upsshow(){
        bundle.putSerializable("room",getRoom());
        Fragment ups = new UpsF();
        ups.setArguments(bundle);
        beginft(ups);
    }
    //空调界面
    public void arishow(){
        bundle.putSerializable("room",getRoom());
        Fragment air = new Air();
        air.setArguments(bundle);
        beginft(air);
    }
    //温湿度界面
    public void temshow(){
        bundle.putSerializable("room",getRoom());
        Fragment tem = new Tem();
        tem.setArguments(bundle);
        beginft(tem);
    }
    //开关机设备界面
    public void opencloseshow(){

        bundle.putSerializable("room",getRoom());
        Fragment openclose = new Openclose();
        openclose.setArguments(bundle);
        beginft(openclose);
    }
    public void beginft(Fragment fragment){
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.roomdetailframent,fragment);
        ft.commit();
    }

}
