package com.tc.www.monitoring.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tc.www.monitoring.R;
import com.tc.www.monitoring.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEdit;
    private EditText passwordEdit;
    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //登陆按钮的绑定
        Button loginbutton = (Button) findViewById(R.id.login);
        Button canclebutton = (Button) findViewById(R.id.cancel);
        //用户名和密码
         usernameEdit = (EditText) findViewById(R.id.username);
         passwordEdit = (EditText) findViewById(R.id.password);


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                user.setUsername(username);
                user.setPassword(password);
                /**
                 * 测试
                  */
                //json转为实体类
//                String  json = "{'username':'hello','password':'ha'}";
//                User user = JsonUtil.fromJson(json, User.class);
//                System.out.println("用户名："+user.getUsername());
                //实体类转为json
//                /data/data/com.tc.www.monitoring.db/databases/
//                User user = new User();
//                user.setUsername("李强");
//                user.setPassword("123456");
//                String s = JsonUtil.toJson(user);
//                System.out.println("输出的json的字符串是：：：：："+s);

                //创建数据库测试
//                TestDB test = new TestDB(LoginActivity.this, "User.db", null, 1);
//                SQLiteDatabase s = test.getWritableDatabase();
//                System.out.println("数据库地址："+s.getPath());

                //插入数据库测试
//
//                TestDB test = new TestDB(LoginActivity.this, "User.db", null, 1);
//                SQLiteDatabase s = test.getWritableDatabase();
//                ContentValues contentValues = new ContentValues();
//                contentValues.put("username","小明");
//                contentValues.put("password","123456");
//                s.insert("user",null,contentValues);

                //查询数据测试
//                Cursor cursor =  s.query("user",null,null,null,null,null,null,null);
//                if(cursor.moveToFirst()){
//                    do {
//                        String name = cursor.getString(cursor.getColumnIndex("username"));
//                        String password1 = cursor.getString(cursor.getColumnIndex("password"));
//                        System.out.println("名字叫做："+name);
//                        System.out.println("密码是："+password1);
//                    }while (cursor.moveToNext());
//                }
//                System.out.println("插入数据成功");



                //网络请求测试
//                LoginThread loginThread = new LoginThread(user);
//                loginThread.start();


                /**
                 * greenDao数据库测试
                 */
//                DaoSession daoSession =
//                DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(LoginActivity.this,"Dept.db",null);
//                DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
//                DaoSession daoSession = daoMaster.newSession();
//                DeptDao deptDao = daoSession.getDeptDao();
//                Dept dept = new Dept();
//                dept.setName("张三");
//                dept.setId(555555);
//                deptDao.insert(dept);
//                List list = deptDao.queryBuilder().list();
//                for (int i = 0; i < list.size(); i++){
//                    Dept d = (Dept)list.get(i);
//                    System.out.println("用户名"+d.getName());
//                }

//               DeptDao deptDao = GreenDaoTool.getInstance().getDaoSession().getDeptDao();
//                Dept dept = new Dept();
//                dept.setName("就是");
//                deptDao.insert(dept);
//                List list = deptDao.queryBuilder().list();
//                for (int i=0; i < list.size(); i++){
//                    Dept d  = (Dept) list.get(i);
//                    System.outprintln("得到的名字"+d.getName());
//                }
                Intent intent = new Intent();
                intent.putExtra("rooom",user);
                intent.setClass(LoginActivity.this, RoomActivity.class);
                startActivity(intent);
            }
        });
        canclebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,WebActivity.class);
                startActivity(intent);
            }
        });

    }
}
