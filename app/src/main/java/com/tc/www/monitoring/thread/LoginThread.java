package com.tc.www.monitoring.thread;

import com.tc.www.monitoring.http.Constant;
import com.tc.www.monitoring.http.HttpUtils;
import com.tc.www.monitoring.model.User;

/**
 * Created by Administrator on 2016/11/1.
 */

public class LoginThread extends Thread {
    private User user;

    public LoginThread(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        String url = Constant.httpUrl+"/a/login?loginName="+user.getUsername()+"&password="+user.getPassword();
        try {
            HttpUtils.getStringByUrl(url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
