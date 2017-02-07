package com.tc.www.monitoring.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.tc.www.monitoring.http.Constant;

public class WebActivity extends AppCompatActivity {
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        initOpenBrower();
        initWebView();
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_web);
    }

    //弹出浏览器
    private void initOpenBrower() {
        url = Constant.httpUrl+"login/loginValid";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
    //页面上显示web页面
    private void initWebView() {
       url =Constant.httpUrl+"login/home";
        WebView w = new WebView(this);
       w.getSettings().setJavaScriptEnabled(true);
        w.loadUrl(url);
        setContentView(w);

    }

}
