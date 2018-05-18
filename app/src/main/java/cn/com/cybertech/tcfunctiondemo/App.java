package cn.com.cybertech.tcfunctiondemo;

import android.app.Application;
import android.content.Context;

import cn.com.cybertech.tcfunctiondemo.util.LogToFile;

/**
 * Created by 20201002 on 2017/5/5.
 */

public class App extends Application
{
    public static Context sContext;//全局的Context对象
    @Override
    public void onCreate()
    {
        super.onCreate();
        LogToFile.getInstance().init(this);
        sContext = this;
    }
}
