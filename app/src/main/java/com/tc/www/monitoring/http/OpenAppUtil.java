package com.tc.www.monitoring.http;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/2/8.
 */

public class OpenAppUtil {
    public static void openApp(String pageName, String activityName, Context c){
        Intent mIntent = new Intent( );
        mIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        ComponentName comp = new ComponentName(pageName, activityName);
        mIntent.setComponent(comp);
        c.startActivity(mIntent);
    }

}
