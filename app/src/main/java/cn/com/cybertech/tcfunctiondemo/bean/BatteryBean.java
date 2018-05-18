package cn.com.cybertech.tcfunctiondemo.bean;

import android.graphics.drawable.Drawable;

/**
 * 各个硬件和软件的耗电量
 * Created by miao on 2017/9/8.
 */

public class BatteryBean
{
    private Drawable icon;
    private String name;
    private String battery;

    public Drawable getIcon()
    {
        return icon;
    }

    public void setIcon(Drawable icon)
    {
        this.icon = icon;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getBattery()
    {
        return battery;
    }

    public void setBattery(String battery)
    {
        this.battery = battery;
    }
}
