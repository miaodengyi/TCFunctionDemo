package cn.com.cybertech.tcfunctiondemo.bean;

/**
 * 管控新首界面的应用信息
 * Created by miao on 2017/9/6.
 */

public class ApplicationBean
{
    private int icon;
    private String name;
    private String version;

    public int getIcon()
    {
        return icon;
    }

    public void setIcon(int icon)
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

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }
}
