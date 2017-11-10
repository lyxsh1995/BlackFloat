package com.example.administrator.blackfloat;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/11/10.
 */

public class Floatservice extends Service {
    public static Floatservice floatservicethis;
    public WindowManager windowManager;
    public WindowManager.LayoutParams params = new WindowManager.LayoutParams();
    FloatView floatView;
    private Timer timer;
    private TimerTask task;

    Handler handler = new Handler();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        floatservicethis = this;
        startfloat();
        new Thread(runnable).start();

        floatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatView.setVisibility(View.GONE);
            }
        });
    }

    private void startfloat() {
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        floatView = new FloatView(getApplicationContext());
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
//        params.format = PixelFormat.RGBA_8888;//设置背景透明
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.MATCH_PARENT;
        params.x = 100;
        params.y = 100;
        windowManager.addView(floatView, params);
    }

    private Runnable runnable  = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (IsHome.isHome(getApplicationContext())) {
                    handler.post(gonerunn);
                    continue;
                }
                if (floatView.getVisibility() == View.GONE) {
                    handler.post(visiblerunn);
                }
            }
        }
    };

    Runnable visiblerunn = new Runnable() {
        @Override
        public void run() {
            floatView.setVisibility(View.VISIBLE);
        }
    };
    Runnable gonerunn = new Runnable() {
        @Override
        public void run() {
            floatView.setVisibility(View.GONE);
        }
    };
}
