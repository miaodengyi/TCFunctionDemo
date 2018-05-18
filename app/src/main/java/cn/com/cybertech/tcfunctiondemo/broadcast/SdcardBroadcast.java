package cn.com.cybertech.tcfunctiondemo.broadcast;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import cn.com.cybertech.tcfunctiondemo.activity.SdcardRecordActivity;

/**
 * 监听sdcard拔插
 * Created by miao on 2017/5/26.
 */

public class SdcardBroadcast extends BroadcastReceiver
{
    private final static String ACTION_SIM_STATE_CHANGED = "android.intent.action.SIM_STATE_CHANGED";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_MEDIA_EJECT))
        {
            System.out.println("sdcard拔掉");
            sendMsg(context, "sdcard拔掉");
        }
        else if (action.equals(Intent.ACTION_MEDIA_MOUNTED))
        {
            System.out.println("sdcard插上");
            sendMsg(context, "sdcard插上");
        }else if (action.equals(ACTION_SIM_STATE_CHANGED))
        {
            TelephonyManager tm = (TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);
            int state = tm.getSimState();
            switch (state) {
                case TelephonyManager.SIM_STATE_READY :
                    System.out.println("sim卡插上");
                    sendMsg(context, "sim卡插上");
                    break;
                case TelephonyManager.SIM_STATE_UNKNOWN :
                case TelephonyManager.SIM_STATE_ABSENT :
                case TelephonyManager.SIM_STATE_PIN_REQUIRED :
                case TelephonyManager.SIM_STATE_PUK_REQUIRED :
                case TelephonyManager.SIM_STATE_NETWORK_LOCKED :
                default:
                    System.out.println("sim卡拔掉");
                    sendMsg(context, "sim卡不拔掉");
                    break;
            }
        }
    }

    private void sendMsg(Context context, String s)
    {
        Intent intent = new Intent(SdcardRecordActivity.ACTION_INTENT_RECEIVER);
        intent.putExtra("message", s);
        context.sendBroadcast(intent);
    }
}
