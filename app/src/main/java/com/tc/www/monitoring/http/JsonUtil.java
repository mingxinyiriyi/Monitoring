package com.tc.www.monitoring.http;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/11/2.
 */

public class JsonUtil {
    private  static  Gson gson = new Gson();

    //json装为实体类
    public static <T> T fromJson(String json,Class<T> targetclass){
        return gson.fromJson(json,targetclass);
    }

    //json转为List
    public static <T> T fromJson(String json,Type type){
        return gson.fromJson(json, type);
    }

    //实体类转为json
    public static String toJson(Object object){
        return gson.toJson(object);
    }
    //读取输入流（比如读取txt的文件）
    public static <T> T fromInputJsonToEntity(InputStream in,Class<T> tClass){
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(in,"UTF-8"));
            T object = gson.fromJson(reader,tClass);
            reader.close();
            return object;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
