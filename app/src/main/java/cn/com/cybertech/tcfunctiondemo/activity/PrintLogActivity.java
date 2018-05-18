package cn.com.cybertech.tcfunctiondemo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.util.LogObserverService;
import cn.com.cybertech.tcfunctiondemo.util.LogToFile;

/**
 * Created by 20201002 on 2017/5/15.
 */

public class PrintLogActivity extends AppCompatActivity
{
    private TextView tvPrintLog, logContent;
    private StringBuffer stringBuffer = new StringBuffer();

    private       String               TAG                   = "PrintLogActivity";
    public static String               LOG_ACTION            = "cn.com.cybertech.tcfunctiondemo.LOG_ACTION";
    private       Intent               logObserverIntent     = null;
    private       LogBroadcastReceiver mLogBroadcastReceiver = null;

    private boolean isWrite = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("打印日志");
        setContentView(R.layout.activity_print_log);
        tvPrintLog = (TextView) findViewById(R.id.tv_print_log);
        logContent = (TextView) findViewById(R.id.tv_print_log_2);
        registerLogBroadcastReceiver();

        //
        Log.v(TAG, "VERBOSE");
        stringBuffer.append(TAG + "VERBOSE" + "\n");
        //        LogToFile.getInstance().v(TAG,"VERBOSE");
        //
        Log.d(TAG, "DEBUG");
        stringBuffer.append(TAG + "DEBUG" + "\n");
        //        LogToFile.getInstance().d(TAG,"DEBUG");
        //
        Log.i(TAG, "INFO");
        stringBuffer.append(TAG + "INFO" + "\n");
        //        LogToFile.getInstance().i(TAG,"INFO");
        Log.e(TAG, "ERROR");
        startLogObserverService();
        //
        Log.e(TAG, "ERROR");
        stringBuffer.append(TAG + "ERROR" + "\n");
        //        LogToFile.getInstance().e(TAG,"ERROR");
        //
        Log.w(TAG, "WARN");
        stringBuffer.append(TAG + "WARN" + "\n");
        //        LogToFile.getInstance().w(TAG,"WARN");
        //
        tvPrintLog.setText(stringBuffer);
//        try
//        {
//            Object object = null;
//            object.toString();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            Log.e(TAG, e.toString());
//        }
    }

    private void startLogObserverService()
    {
        logObserverIntent = new Intent(this, LogObserverService.class);
        startService(logObserverIntent);
    }

    /**
     * 注册log广播接收者
     */
    private void registerLogBroadcastReceiver()
    {
        mLogBroadcastReceiver = new LogBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(LOG_ACTION);
        registerReceiver(mLogBroadcastReceiver, filter);
    }

    /**
     * log 广播接收者
     *
     * @author zhangyg
     */
    private class LogBroadcastReceiver extends BroadcastReceiver
    {
        private String action  = null;
        private Bundle mBundle = null;

        @Override
        public void onReceive(Context context, Intent intent)
        {
            action = intent.getAction();
            if (LOG_ACTION.equals(action))
            {
                mBundle = intent.getExtras();
                String s = mBundle.getString("log");
                logContent.setText(s);
//                if (!isWrite)
//                {
                    LogToFile.getInstance().all(TAG,s);
//                    isWrite = true;
//                }
            }
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        stopService(logObserverIntent);
        unregisterReceiver(mLogBroadcastReceiver);
    }

}
