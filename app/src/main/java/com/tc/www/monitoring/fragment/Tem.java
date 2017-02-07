package com.tc.www.monitoring.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tc.www.monitoring.R;

public class Tem extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("温湿度设备打印");
        return inflater.inflate(R.layout.fragment_tem, container, false);
    }


}
