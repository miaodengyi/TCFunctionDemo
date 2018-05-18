package cn.com.cybertech.tcfunctiondemo.activity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.TrafficStats;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.adapter.TrafficAppAdapter;
import cn.com.cybertech.tcfunctiondemo.bean.AppInfo;

/**
 * 单个应用使用流量和wifi的情况
 * Created by miao on 2017/9/8.
 */

public class TrafficAppActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private PackageManager pm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("应用流量使用统计");
        setContentView(R.layout.activity_traffic_app);
        pm = this.getPackageManager();
        recyclerView = (RecyclerView) findViewById(R.id.rc_traffic_app);
        recyclerView.setLayoutManager(new LinearLayoutManager(TrafficAppActivity.this));
        List<AppInfo> list = getAppList();
        TrafficAppAdapter appAdapter = new TrafficAppAdapter(TrafficAppActivity.this,list);
        recyclerView.setAdapter(appAdapter);
    }

    public List<AppInfo> getAppList()
    {
        List<AppInfo>     appInfos     = new ArrayList<>();
        List<PackageInfo> packageInfos = this.getPackageManager().getInstalledPackages(0);

        if (packageInfos != null && packageInfos.size() > 0)
        {
            for (PackageInfo packageInfo : packageInfos)
            {
                AppInfo info = new AppInfo();
                String pkgName = packageInfo.applicationInfo.packageName;
                info.setPackageName(pkgName);
                info.setLabel((String) this.getPackageManager().getApplicationLabel(packageInfo.applicationInfo));
                info.setVersionCode(packageInfo.versionCode);
                try
                {
                    ApplicationInfo inFo     = pm.getApplicationInfo(pkgName, 0);
                    Drawable        drawable = inFo.loadIcon(pm);
                    info.setIcon(drawable);
                }
                catch (PackageManager.NameNotFoundException e)
                {
                    e.printStackTrace();
                }
                ////获取每个应用程序在操作系统内的进程id
                int uid = packageInfo.applicationInfo.uid;
                //如果返回-1，代表不支持使用该方法，注意必须是2.2以上的
                long rx= TrafficStats.getUidRxBytes(uid);
                //如果返回-1，代表不支持使用该方法，注意必须是2.2以上的
                long tx=TrafficStats.getUidTxBytes(uid);
                info.setTrafficData(rx+tx);

                if (filterApp(packageInfo.applicationInfo))
                {
                    appInfos.add(info);
                }
            }
        }
        return appInfos;
    }

    // 判断应用程序是否是用户程序
    public boolean filterApp(ApplicationInfo info)
    {
        if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0)
        {
            // 原来是系统应用，用户手动升级
            return true;
        }
        else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
        {
            // 用户自己安装的应用程序
            return true;
        }
        return false;
    }
}
