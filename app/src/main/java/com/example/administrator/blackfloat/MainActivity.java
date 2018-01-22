package com.example.administrator.blackfloat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.graphics.PixelFormat;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static MainActivity mainActivitythis;
    private static final int MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS = 1101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivitythis =this;
        //判断是否安卓6.0
        if (Build.VERSION.SDK_INT >= 23) {
            //判断是否已经授权
            if (Settings.canDrawOverlays(MainActivity.this)) {
                startService(new Intent(MainActivity.this, Floatservice.class));
            } else {
                //打开系统悬浮窗设置页面
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(intent);
            }
        } else {
            startService(new Intent(MainActivity.this, Floatservice.class));
        }

        if (Build.VERSION.SDK_INT > 21) {
            //若用户未开启权限，则引导用户开启“Apps with usage access”权限
            if (!Usage.hasPermission(MainActivity.this)) {
                startActivityForResult(
                        new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS),
                        MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS);
            }
        }

        Pack pack = new Pack(MainActivity.this);
        MyAdapet adapter = new MyAdapet(MainActivity.this, pack.getAllApps());
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
}
