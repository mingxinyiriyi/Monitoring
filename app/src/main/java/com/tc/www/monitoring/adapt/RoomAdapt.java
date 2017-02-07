package com.tc.www.monitoring.adapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tc.www.monitoring.R;
import com.tc.www.monitoring.model.Room;

import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */

public class RoomAdapt extends BaseAdapter {
    private List<Room> list;
    private Room room;
    //寻找布局文件
    private LayoutInflater inflater;

    public RoomAdapt(Context context) {
        //初始化布局文件xml
        inflater = LayoutInflater.from(context);
    }

    public void bindData(List<Room> list){
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        room = (Room)getItem(i);
//        //找到对应页面
//        view = inflater.inflate(R.layout.fragment_room_detail,null);
//        //设置内容页面
//        TextView textView = (TextView)view.findViewById(R.id.roomdetail);
//        if(room!= null){
//            textView.setText(room.getName());
//        }

        ViewHolder viewHolder;
        if(view == null){
            room = (Room)getItem(i);
            view = inflater.inflate(R.layout.fragment_room_detail,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView)view.findViewById(R.id.roomdetail);
            view.setTag(viewHolder);
            if(room!= null){
                viewHolder.textView.setText(room.getName());
            }
        }else {
            viewHolder = (ViewHolder) view.getTag();

        }

        return view;
    }
    private final class ViewHolder {
        TextView textView;
    }
}
