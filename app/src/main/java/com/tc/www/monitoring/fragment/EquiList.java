package com.tc.www.monitoring.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tc.www.monitoring.R;
import com.tc.www.monitoring.activity.LoginActivity;
import com.tc.www.monitoring.adapt.EquiAdapt;
import com.tc.www.monitoring.model.Equi;
import com.tc.www.monitoring.thread.EquiThead;

import java.util.ArrayList;
import java.util.List;


public class EquiList extends Fragment {
    private ListView listView;
    private List<Equi> equiList = new ArrayList<Equi>();

    //片段管理器
    private FragmentManager fm;
    //片段事务管理
    private FragmentTransaction ft;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equi_list,null);
        init(view);
        return view;
    }
    public  void init(View view){
        EquiThead equiThead = new EquiThead(equiList);
        equiThead.start();
        listView = (ListView)view.findViewById(R.id.equilist);
        listView.setAdapter(new EquiAdapt(getActivity(),equiList));
        //添加点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    //设备机房
                    case 0:
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.mainframe,new RoomList());
                        //返回前一页
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.addToBackStack(null);
                        ft.commit();
                        break;
                    //设备基础信息管理
                    case 1:
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        break;
                    //前置机管理
                    case 2:
                        break;
                    //串口服务器管理
                    case 3:
                        break;
                    //数字里设备管理
                    case 4:
                        break;
                    //IO设备管理
                    case 5:
                        break;

                }

            }
        });
    }
}
