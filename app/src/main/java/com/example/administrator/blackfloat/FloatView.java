package com.example.administrator.blackfloat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by lyxsh on 2016/11/18.
 */
public class FloatView extends LinearLayout {
    Context context;

    WindowManager windowManager;
    WindowManager.LayoutParams windowManagerParams;

    public FloatView(Context context) {
        super(context);
        this.context = context;
        //获取浮动窗口视图所在布局
        View view = LayoutInflater.from(context).inflate(R.layout.xuanfuchaung, null);
        windowManager = Floatservice.floatservicethis.windowManager;
        // 此windowManagerParams变量为获取的全局变量，用以保存悬浮窗口的属性
        windowManagerParams = Floatservice.floatservicethis.params;
        this.addView(view);
    }
}