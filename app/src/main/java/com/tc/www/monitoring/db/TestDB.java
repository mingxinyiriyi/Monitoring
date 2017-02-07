package com.tc.www.monitoring.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/2.
 */

public class TestDB extends SQLiteOpenHelper {
    private Context mcontext;

    //创建用户表
    private static final String CREATE_USER="create table user(" +
            "userId integer primary key autoincrement," +
            "username text," +
            "password text" +
            ")";

    public TestDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        System.out.println("TestDB构造方法");
        mcontext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("创建用户表");
        sqLiteDatabase.execSQL(CREATE_USER);
        Toast.makeText(mcontext,"创建用户表成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
