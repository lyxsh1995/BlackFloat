package com.example.administrator.blackfloat;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 */

public class Pack{
    Context context;
    PackageManager packageManager;
    private List<PackageInfo> apps;

    Pack(Context context) {
        this.context = context;
        packageManager = context.getPackageManager();
    }

    public List<PackageInfo> getAllApps() {
        apps = new ArrayList<PackageInfo>();
        // 获取手机内所有应用
        List<PackageInfo> packlist = packageManager.getInstalledPackages(0);
        for (int i = 0; i < packlist.size(); i++) {
            PackageInfo pak = (PackageInfo) packlist.get(i);

            // 判断是否为非系统预装的应用程序
            // 这里还可以添加系统自带的，这里就先不添加了，如果有需要可以自己添加
            // if()里的值如果<=0则为自己装的程序，否则为系统工程自带
            if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) {
                // 添加自己已经安装的应用程序
                apps.add(pak);
            }
        }
        return apps;
    }
}
