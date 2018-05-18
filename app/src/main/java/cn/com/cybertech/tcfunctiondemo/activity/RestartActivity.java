package cn.com.cybertech.tcfunctiondemo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.broadcast.SdcardBroadcast;

/**
 * 开机自启动和记录sdcard插拔记录
 * Created by miao on 2017/8/8.
 */

public class RestartActivity extends AppCompatActivity
{
    TextView     tvSdcardRecord;
    StringBuffer stringBuffer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("sdcard插拔记录");
        setContentView(R.layout.activity_sdcard_record);
        stringBuffer = new StringBuffer();
        tvSdcardRecord = (TextView) findViewById(R.id.tv_sdcard_record);
        //        registerSdcardReceive();
        registerMessageReceiver();
    }

    //注册监听sdcard插拔的广播
    void registerSdcardReceive()
    {
        SdcardBroadcast sdcardBroadcast = new SdcardBroadcast();
        IntentFilter    intentFilter    = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_MEDIA_EJECT);
        intentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        intentFilter.addDataScheme("file");
        registerReceiver(sdcardBroadcast, intentFilter);
    }

    //在销毁时要与广播解绑
    @Override
    protected void onDestroy()
    {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    /**
     * 动态注册广播
     */
    public void registerMessageReceiver()
    {
        mMessageReceiver = new MessageReceive();
        IntentFilter filter = new IntentFilter();

        filter.addAction(ACTION_INTENT_RECEIVER);
        registerReceiver(mMessageReceiver, filter);
    }

    public RestartActivity.MessageReceive mMessageReceiver;
    public static String ACTION_INTENT_RECEIVER = "receiverMessage";

    class MessageReceive extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {
            if (intent.getAction().equals(ACTION_INTENT_RECEIVER))
            {
                stringBuffer.append(intent.getStringExtra("message") + "\n");
                if (tvSdcardRecord != null && !TextUtils.isEmpty(stringBuffer))
                {
                    tvSdcardRecord.setText(stringBuffer);
                }
            }
        }
    }
}
