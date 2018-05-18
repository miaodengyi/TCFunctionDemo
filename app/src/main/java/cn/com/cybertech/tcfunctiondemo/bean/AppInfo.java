package cn.com.cybertech.tcfunctiondemo.bean;

import android.graphics.drawable.Drawable;

public class AppInfo
{
    private Drawable icon;
    private int    versionCode = 0;
    private String label       = "";
    private String packageName = "";
    private String versionName = "";

    public long getTrafficData()
    {
        return trafficData;
    }

    public void setTrafficData(long trafficData)
    {
        this.trafficData = trafficData;
    }

    private long trafficData;

    public Drawable getIcon()
    {
        return icon;
    }

    public void setIcon(Drawable icon)
    {
        this.icon = icon;
    }

    public int getVersionCode()
    {
        return versionCode;
    }

    public void setVersionCode(int versionCode)
    {
        this.versionCode = versionCode;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public String getVersionName()
    {
        return versionName;
    }

    public void setVersionName(String versionName)
    {
        this.versionName = versionName;
    }
}