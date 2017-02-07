package com.tc.www.monitoring.dao.tool;

import com.tc.www.monitoring.application.MyApplication;
import com.tc.www.monitoring.dao.gen.DaoMaster;
import com.tc.www.monitoring.dao.gen.DaoSession;

/**
 * Created by Administrator on 2016/11/3.
 */

public class GreenDaoTool {
    private static GreenDaoTool mgreenTool;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private String DATA_NAME = "monitoring";

    public GreenDaoTool() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getContent(), DATA_NAME, null);
        DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public static GreenDaoTool getInstance() {
        if (mgreenTool == null) {
            mgreenTool = new GreenDaoTool();
        }

        return mgreenTool;
    }

    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
