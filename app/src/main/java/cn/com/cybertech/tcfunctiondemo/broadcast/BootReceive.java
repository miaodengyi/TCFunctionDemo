package cn.com.cybertech.tcfunctiondemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.com.cybertech.tcfunctiondemo.activity.RestartActivity;

/**
 * 监听开机广播
 * Created by miao on 2017/8/8.
 */
public class BootReceive extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
            Intent intent1 = new Intent(context, RestartActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }
    }
}
