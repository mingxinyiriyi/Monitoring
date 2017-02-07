package com.tc.www.monitoring.adapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tc.www.monitoring.R;
import com.tc.www.monitoring.model.Equi;

import java.util.List;

/**
 * Created by ming on 2016-10-28.
 */

public class EquiAdapt extends BaseAdapter {

    private List<Equi> elist ;
    private LayoutInflater inflater;


    public EquiAdapt(Context context, List<Equi> list) {
        elist = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return elist.size();
    }

    @Override
    public Object getItem(int i) {
        return elist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_equi_detail,null);
        }
        TextView textView = (TextView)view.findViewById(R.id.equidetail);
        Equi equi = elist.get(i);
        equi.setName(equi.getName());
        textView.setText(equi.getName());

        return view;
    }
}
