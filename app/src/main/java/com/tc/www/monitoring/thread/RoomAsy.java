package com.tc.www.monitoring.thread;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.tc.www.monitoring.adapt.RoomAdapt;
import com.tc.www.monitoring.caches.NetCache;
import com.tc.www.monitoring.http.Constant;
import com.tc.www.monitoring.http.HttpUtils;
import com.tc.www.monitoring.http.JsonUtil;
import com.tc.www.monitoring.interceptors.LogInterceptor;
import com.tc.www.monitoring.interceptors.PriovideOfflineCacheIntercetor;
import com.tc.www.monitoring.model.Room;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/10.
 */

public class RoomAsy extends AsyncTask<String,Void,List<Room>> {
    private final String Tag = "RoomAsy";
    //请求得到的机房数据
    private List<Room> roomsListData = new ArrayList<>();
    //机房的总数据
    private List<Room> totalData = new ArrayList<>();
    private ListView listView;
    private RoomAdapt adapt;
    //页码
    private int pageNo;

    public RoomAsy(RoomAdapt adapt, ListView listView,List<Room> roomsListData,int pageNo) {
        this.adapt = adapt;
        this.listView = listView;
        this.roomsListData = roomsListData;
        this.pageNo = pageNo;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Room> doInBackground(String... strings) {
//        Retrofit s = Injector.provideRetrofit(Constant.httpUrl+"test/getRoomListById/");
//        System.out.println("输出的请求是："+s);
//        okHttp3Intercepetor();
        String id = "1";
        String param = strings[0];
        Log.e(Tag,param);
        try {
//            String json = HttpUtils.getStringByUrl(Constant.httpUrl+"test/getRoomListById?id="+id);
            String json = HttpUtils.okHttp3Intercepetor();
            List<Room> roomlist = new ArrayList<Room>();
            if(roomlist != null) {
                Type types = new TypeToken<ArrayList<Room>>(){}.getType();
                roomlist = JsonUtil.fromJson(json, types);
                for (Room room : roomlist) {
                    roomsListData.add(room);
                }
            }else {
                roomsListData = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomsListData;
    }

    @Override
    protected void onPostExecute(List<Room> rooms) {
        super.onPostExecute(rooms);
        if (rooms != null){
            //处理数据
            totalData.addAll(rooms);
            adapt.bindData(totalData);
            if (pageNo == 1) {
                listView.setAdapter(adapt);
            }
            adapt.notifyDataSetChanged();
            pageNo++;
        }else {

        }
    }
    public String okHttp3Intercepetor(){
        String responseStr="";
        Cache cache =new NetCache().getCache();
        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new LogInterceptor())
                    .addInterceptor(new PriovideOfflineCacheIntercetor())
                    .cache(cache)
                    .build();

            Request request = new Request.Builder()
                    .url(Constant.httpUrl+"test/getRoomListById")
                    .header("User-Agent", "OkHttp Example")
                    .build();
            Response response = client.newCall(request).execute();
            responseStr = response.body().string();
            System.out.println("返回的信息："+responseStr);
            response.body().close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseStr;
    }
}
