package cn.com.cybertech.tcfunctiondemo.util;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.com.cybertech.tcfunctiondemo.activity.PrintLogActivity;

/**
 * Created by 20201002 on 2017/5/17.
 */

public class LogObserverService extends Service implements Runnable
{
    private String       TAG           = "LogObserverService";
    private boolean      isObserverLog = false;
    private StringBuffer logContent    = null;
    private Bundle       mBundle       = null;
    private Intent       mIntent       = null;

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.i(TAG, "onCreate");
        mIntent = new Intent();
        mBundle = new Bundle();
        logContent = new StringBuffer();
        startLogObserver();
    }

    /**
     * 开启检测日志
     */
    public void startLogObserver()
    {
        Log.i(TAG, "startObserverLog");
        isObserverLog = true;
        Thread mTherad = new Thread(this);
        mTherad.start();
    }

    /**
     * 关闭检测日志
     */
    public void stopLogObserver()
    {
        isObserverLog = false;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        stopLogObserver();
    }

    /**
     * 发送log内容
     *
     * @param logContent
     */
    private void sendLogContent(String logContent)
    {
        mBundle.putString("log", logContent);
        mIntent.putExtras(mBundle);
        mIntent.setAction(PrintLogActivity.LOG_ACTION);
        sendBroadcast(mIntent);
    }


    @Override
    public void run()
    {
        Process pro = null;
        try
        {
            //            Runtime.getRuntime().exec("logcat -c").waitFor();

            //            pro = Runtime.getRuntime().exec("logcat");
//            pro = Runtime.getRuntime().exec(new String[]{"logcat", "PrintLogActivity:I *:S"});

            pro = Runtime.getRuntime().exec("logcat");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        BufferedReader dis  = new BufferedReader(new InputStreamReader(pro.getInputStream()));
        String         line = null;
        while (isObserverLog)
        {
            try
            {
                while ((line = dis.readLine()) != null)
                {
                    String temp = logContent.toString();
                    logContent.delete(0, logContent.length());
                    logContent.append(line);
                    logContent.append("\n");
                    logContent.append(temp);
                    //发送log内容
                    sendLogContent(logContent.toString());
                    Thread.yield();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
