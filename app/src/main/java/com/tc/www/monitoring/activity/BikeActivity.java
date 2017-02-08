package com.tc.www.monitoring.activity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tc.www.monitoring.R;
import com.tc.www.monitoring.http.OpenAppUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

public class BikeActivity extends AppCompatActivity {
    private Button button;
    private Button openGPSButton;
    private Button mobileButton;
    private Button gpsbigButton;

    private LocationManager locationManager;
    private LocationListener locationListener;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike);
        button = (Button) findViewById(R.id.bluetooths);
        openGPSButton = (Button) findViewById(R.id.openGps);
        mobileButton = (Button) findViewById(R.id.mobileNet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBluetooth();
            }
        });
        openGPSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                turnGPSOn();
                 isOnline();

            }
        });
        mobileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMobileDataEnabled();
                try {
                    setMobileDataEnabled(true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        gpsbigButton = (Button) findViewById(R.id.gpsbig);
        gpsLocation();
    }

    private void startBluetooth() {
        BluetoothAdapter mBluetooth = BluetoothAdapter.getDefaultAdapter();
        if (mBluetooth == null) {
            Context context = getApplicationContext();
            CharSequence text = "没有蓝牙模块!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            if (!mBluetooth.isEnabled()) {
                mBluetooth.enable();
            } else {
                Context context = getApplicationContext();
                CharSequence text = "蓝牙已经启动!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            OpenAppUtil.openApp("com.ibike.publicbicycle.activity", "com.ibike.publicbicycle.activity.GuidePageAct",BikeActivity.this);
            openClock();
        }
    }


    private void closeBluetooth() {
        BluetoothAdapter mBluetooth = BluetoothAdapter.getDefaultAdapter();
        if (mBluetooth == null) {
            Context context = getApplicationContext();
            CharSequence text = "没有蓝牙模块!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            if (mBluetooth.isEnabled()) {
                mBluetooth.disable();
            } else {
                Context context = getApplicationContext();
                CharSequence text = "蓝牙已经关闭了!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }

    /**
     * 移动网络开关
     *
     * @param context
     * @param enabled true 开 false 关
     */
    private void toggleMobileData(Context context, boolean enabled) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        Class<?> conMgrClass = null; // ConnectivityManager类
        Field iConMgrField = null; // ConnectivityManager类中的字段
        Object iConMgr = null; // IConnectivityManager类的引用
        Class<?> iConMgrClass = null; // IConnectivityManager类
        Method setMobileDataEnabledMethod = null; // setMobileDataEnabled方法
        try {
            // 取得ConnectivityManager类
            conMgrClass = Class.forName(conMgr.getClass().getName());
            // 取得ConnectivityManager类中的对象mService
            iConMgrField = conMgrClass.getDeclaredField("mService");
            // 设置mService可访问
            iConMgrField.setAccessible(true);
            // 取得mService的实例化类IConnectivityManager
            iConMgr = iConMgrField.get(conMgr);
            // 取得IConnectivityManager类
            iConMgrClass = Class.forName(iConMgr.getClass().getName());
            // 取得IConnectivityManager类中的setMobileDataEnabled(boolean)方法
            setMobileDataEnabledMethod = iConMgrClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            // 设置setMobileDataEnabled方法可访问
            setMobileDataEnabledMethod.setAccessible(true);
            // 调用setMobileDataEnabled方法
            setMobileDataEnabledMethod.invoke(iConMgr, enabled);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public boolean setMobileDataEnabled() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        boolean flag = networkInfo != null && networkInfo.isConnected();
        Toast.makeText(this, "网络状态" + flag, Toast.LENGTH_SHORT).show();
        return flag;
    }

    public void gpsEnable() {
        LocationManager alm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (alm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPS打开了", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "GPS关闭中.", Toast.LENGTH_SHORT).show();
        }
    }


    public void openLocationSettings() {
        Intent intent = new Intent(Intent.ACTION_LOCALE_CHANGED);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void gpsLocation() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                System.out.println("输出location为："+location);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                System.out.println("输出onStatusChanged为："+s+"啊啊啊"+i);
            }

            @Override
            public void onProviderEnabled(String s) {
                System.out.println("输出onProviderEnabled为："+s);
            }

            @Override
            public void onProviderDisabled(String s) {
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivity(intent);
                System.out.println("输出onProviderDisabled为："+s);
            }
        };


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{
//                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
//
//            }, 10);
            locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);

            return;
        } else {
            locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
            configButton();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    configButton();
                }
        }
    }

    public void configButton() {
        gpsbigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(BikeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(BikeActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);

            }
        });
    }
    public void turnGPSOn()
    {
        boolean gpsEnabled = Settings.Secure.isLocationProviderEnabled( getContentResolver(), LocationManager.GPS_PROVIDER );
        if(gpsEnabled)
        {
            //关闭GPS
            Settings.Secure.setLocationProviderEnabled( getContentResolver(), LocationManager.GPS_PROVIDER, false );
            Toast.makeText(this,"GPS关闭....",Toast.LENGTH_SHORT).show();
        } else {
            //打开GPS
            Settings.Secure.setLocationProviderEnabled( getContentResolver(), LocationManager.GPS_PROVIDER, true);
            Toast.makeText(this,"GPS打开....",Toast.LENGTH_SHORT).show();

        }
    }
    public void setMobileDataEnabled(boolean enabled) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        Class ownerClass = cm.getClass();
//        Class[] argsClass = new Class[1];
//        argsClass[0] = String.class;
//        argsClass[1] = boolean.class;

        Method method = ownerClass.getMethod("setDataEnabled",boolean.class);
        method.invoke(cm,enabled);
//        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//        Method setMobileDataEnabledMethod  = telephonyManager.getClass().getDeclaredMethod("setDataEnabled",boolean.class);
//        if(setMobileDataEnabledMethod!=null){
//            setMobileDataEnabledMethod.invoke(telephonyManager,enabled);
//        }
    }
    //判断手机网络类型
    public void getNetworkType(){
        //连接管理
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifi = networkInfo.isConnected();
        NetworkInfo networkInfoMo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMo = networkInfoMo.isConnected();
        Toast.makeText(this,"网络Wifi类型"+isWifi,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"网络手机类型"+isMo,Toast.LENGTH_SHORT).show();
    }
    //判断手机是否有网的状态
    public boolean isOnline(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null){
            if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(this,"手机网络",Toast.LENGTH_SHORT).show();
            }else if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
                Toast.makeText(this,"WIFI网络",Toast.LENGTH_SHORT).show();
            }
        }
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * 打开闹钟
     */
    private void openClock() {
        int hour = 1;
        int minute = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,0);
        Intent intent = new Intent();
    }
    @Override
    protected void onDestroy() {
        closeBluetooth();
        super.onDestroy();
    }
}
