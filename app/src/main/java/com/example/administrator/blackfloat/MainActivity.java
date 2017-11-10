package com.example.administrator.blackfloat;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //判断是否安卓6.0
        if (Build.VERSION.SDK_INT >= 23)
        {
            //判断是否已经授权
            if (Settings.canDrawOverlays(MainActivity.this))
            {
                startService(new Intent(MainActivity.this,Floatservice.class));
            } else
            {
                //打开系统悬浮窗设置页面
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(intent);
            }
        } else
        {
            startService(new Intent(MainActivity.this,Floatservice.class));
        }
    }
}
