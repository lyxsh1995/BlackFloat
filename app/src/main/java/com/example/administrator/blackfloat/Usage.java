package com.example.administrator.blackfloat;

import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Build;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/1/5.
 */

public class Usage {

    /**
     *     检测用户是否对本app开启了“Apps with usage access”权限
     */
    public static boolean hasPermission(Context context) {
        AppOpsManager appOps = (AppOpsManager)
                context.getSystemService(Context.APP_OPS_SERVICE);
        int mode = 0;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                    android.os.Process.myUid(), "com.example.administrator.blackfloat");
        }
        return mode == AppOpsManager.MODE_ALLOWED;
    }

    /**
     * 5.0以上判断栈顶应用
     * @param context
     * @param hashMap
     * @return
     */
    public static boolean isRunningForeground5(Context context, HashMap hashMap) {
        long ts = System.currentTimeMillis();
        UsageStatsManager usageStatsManager = (UsageStatsManager)
                context.getApplicationContext().getSystemService(Context.USAGE_STATS_SERVICE);
        List<UsageStats> queryUsageStats = usageStatsManager.queryUsageStats(
                UsageStatsManager.INTERVAL_BEST, ts - 2000, ts);
        if (queryUsageStats == null || queryUsageStats.isEmpty()) {
            return false;
        }
        UsageStats recentStats = null;
        for (UsageStats usageStats : queryUsageStats) {
            if (recentStats == null ||
                    recentStats.getLastTimeUsed() < usageStats.getLastTimeUsed()){
                recentStats = usageStats;
            }
        }
        if(hashMap.containsValue(recentStats.getPackageName())){
            return true;
        }
        return false;
    }
}
