package com.example.administrator.blackfloat;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * Created by lyxsh on 2016/11/18.
 */
public class FloatView extends LinearLayout
{
    Context context;

    private float mTouchX;
    private float mTouchY;
    private float x;
    private float y;
    private float mStartX;
    private float mStartY;
    private OnClickListener mClickListener;
    private OnLongClickListener mLongClickListener;
    WindowManager windowManager;
    WindowManager.LayoutParams windowManagerParams;
    long touchTime = 0;

    private TextView wendu_shuju;
    private TextView shidu_shuju;


    public FloatView(Context context)
    {
        super(context);
            this.context = context;
            //获取浮动窗口视图所在布局
            View view = LayoutInflater.from(context).inflate(R.layout.xuanfuchaung, null);
            windowManager = Floatservice.floatservicethis.windowManager;
            // 此windowManagerParams变量为获取的全局变量，用以保存悬浮窗口的属性
            windowManagerParams = Floatservice.floatservicethis.params;
            this.addView(view);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        //获取到状态栏的高度
        Rect frame = new Rect();
        getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
// 获取相对屏幕的坐标，即以屏幕左上角为原点
        x = event.getRawX();
        y = event.getRawY() - statusBarHeight; // statusBarHeight是系统状态栏的高度
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN: // 捕获手指触摸按下动作
// 获取相对View的坐标，即以此View左上角为原点
                mTouchX = event.getX();
                mTouchY = event.getY();
                mStartX = x;
                mStartY = y;
                break;
            case MotionEvent.ACTION_MOVE: // 捕获手指触摸移动动作
                updateViewPosition();
                break;
            case MotionEvent.ACTION_UP: // 捕获手指触摸离开动作
                updateViewPosition();
                mTouchX = mTouchY = 0;
                if ((x - mStartX) < 5 && (y - mStartY) < 5)
                {
//                    //双击
//                    if ((System.currentTimeMillis() - touchTime) < 1000)
//                    {
//                        //销毁定时器
//                        FloatView.timer.cancel();
//                        //停止服务
//                        FloatWindowService.floatWindowServicethis.stopSelf();
//                        try
//                        {
//                            if (MainActivity.mainActivitythis.kaiguan != null)
//                            {
//                                MainActivity.mainActivitythis.kaiguan.setChecked(false);
//                            }
//                        }catch (Exception e)
//                        {}
//                    }
                    if (mClickListener != null)
                    {
                        mClickListener.onClick(this);
                        //获取触摸时间
                        touchTime = System.currentTimeMillis();
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void setOnClickListener(OnClickListener l)
    {
        this.mClickListener = l;
    }

    @Override
    public void setOnLongClickListener(OnLongClickListener l)
    {
        this.mLongClickListener = l;
    }

    private void updateViewPosition()
    {
// 更新浮动窗口位置参数
        windowManagerParams.x = (int) (x - mTouchX);
        windowManagerParams.y = (int) (y - mTouchY);
        windowManager.updateViewLayout(this, windowManagerParams); // 刷新显示
    }
}