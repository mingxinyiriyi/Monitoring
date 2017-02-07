package com.tc.www.monitoring.thread;


import com.tc.www.monitoring.model.Equi;

import java.util.List;

/**
 * Created by ming on 2016-10-28.
 */

public class EquiThead extends Thread{
    private List<Equi> equiList ;

    public EquiThead(List<Equi> equiList) {
        this.equiList = equiList;
    }
    //网络请求
    @Override
    public void run() {
        if(equiList.size() == 0) {
            equiList.add(new Equi(1, "设备机房"));
            equiList.add(new Equi(2, "设备基础信息管理"));
            equiList.add(new Equi(3, "前置机管理"));
            equiList.add(new Equi(4, "串口服务器管理"));
            equiList.add(new Equi(5, "数字量设备管理"));
            equiList.add(new Equi(6, "IO设备管理"));
        }
    }
}
