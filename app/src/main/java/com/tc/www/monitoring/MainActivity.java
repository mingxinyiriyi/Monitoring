package com.tc.www.monitoring;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tc.www.monitoring.fragment.EquiList;

public class MainActivity extends AppCompatActivity {

    //片段管理器
    private FragmentManager fm;
    //片段事务管理
    private FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.mainframe,new EquiList());
        ft.commit();

    }
}
