package com.tc.www.monitoring.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tc.www.monitoring.R;
import com.tc.www.monitoring.adapt.RoomAdapt;
import com.tc.www.monitoring.model.Room;
import com.tc.www.monitoring.thread.RoomAsy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ming on 2016-10-28.
 */

public class RoomList extends Fragment {
    private String tag = "RoomList";
    private List<Room> roomsListData = new ArrayList<Room>();
    private ListView listView;
    //片段管理器
    private FragmentManager fm;
    //片段事务管理
    private FragmentTransaction ft;
    //是否分页
    private boolean isPage;
    //是否刷新
    private boolean isFresh;
    //页码
    private int pageNo=1;



    private int lastVisibleItemPosition;// 标记上次滑动位置

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_list,null);
        init(view);
        return view;
    }
    public void init(View view){
        listView = (ListView)view.findViewById(R.id.roomlist);
        final RoomAdapt roomAdapt= new RoomAdapt(getActivity());
        new RoomAsy(roomAdapt,listView, roomsListData,pageNo).execute("大家好");
        //添加点击事件

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /**使用start的方式启动服务
                Intent intent = new Intent(getActivity(), RoomService.class);
                intent.putExtra("dd",1);
                getActivity().startService(intent);
                **/
//                getActivity().bindService(intent,conn, Context.BIND_AUTO_CREATE);\
                //绑定方式

                Room room = roomsListData.get(i);
                //bundle  fragment之间的传值
                Bundle bundle = new Bundle();
                bundle.putSerializable("room",room);
                bundle.putString("id",room.getId());
                Fragment roomDetailBase = new RoomDetailBase();
                roomDetailBase.setArguments(bundle);
                //结束

                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.roomframe,roomDetailBase);
                //返回前一页
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                ft.addToBackStack(null);
                ft.commit();

            }
        });

        //listview添加滚动事件
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                //判断最后一页和松开状态
                if(isPage && i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    new RoomAsy(roomAdapt,listView, roomsListData,pageNo).execute("大家好");
                }else if(isFresh && i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    Toast.makeText(getActivity(),"下拉",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int preshow, int curshow, int totalshow) {
                //判断到最后一行
                isPage = (preshow+curshow==totalshow);
                //判断顶部
                isFresh =(preshow ==0);
            }
        });
    }

}
