package com.example.administrator.blackfloat;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/10.
 */

public class IsHome {

    private static List<String> names;

    /**
     * 获得属于桌面的应用的应用包名称
     *
     * @return 返回包含所有包名的字符串列表
     */
    private static List<String> getHomes(Context context) {
        names = new ArrayList<String>();
        PackageManager packageManager = context.getPackageManager();
        //属性
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo ri : resolveInfo) {
            names.add(ri.activityInfo.packageName);
            System.out.println(ri.activityInfo.packageName);
        }
        return names;
    }

    /**
     * 判断当前界面是否是桌面
     */
    public static boolean isHome(Context context) {
        getHomes(context);
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
        return names.contains(rti.get(0).topActivity.getPackageName());
    }
}
