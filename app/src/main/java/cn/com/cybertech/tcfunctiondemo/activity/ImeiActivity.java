package cn.com.cybertech.tcfunctiondemo.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import java.lang.reflect.Method;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 获取imei，meid，iccid
 * Created by miao on 2017/8/16.
 */

public class ImeiActivity extends AppCompatActivity
{
    private TextView tvImei;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("获取imei，meid，iccid");
        setContentView(R.layout.activity_imei);
        tvImei = (TextView) findViewById(R.id.tv_imei);
        TelephonyManager tm           = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        StringBuffer     stringBuffer = new StringBuffer();
        stringBuffer.append("IMEI:" + tm.getDeviceId() + "\n");
        stringBuffer.append("ICCID:" + tm.getSimSerialNumber() + "\n");
        //操作系统版本号
        String mhaVersion = Build.DISPLAY;
        stringBuffer.append("操作系统版本号："+mhaVersion);


        try
        {
            stringBuffer.append("IMEI-1:"+getOperatorBySlot(this, "getDeviceIdGemini", 0)+ "\n");
            stringBuffer.append("IMEI-2:"+getOperatorBySlot(this, "getDeviceIdGemini", 1)+ "\n");
            stringBuffer.append("ICCID-1:"+getOperatorBySlot(this, "getSimOperatorGemini", 0)+ "\n");
            stringBuffer.append("ICCID-2:"+getOperatorBySlot(this, "getSimOperatorGemini", 1)+ "\n");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        tvImei.setText(stringBuffer);
    }

    private static String getOperatorBySlot(Context context, String predictedMethodName, int slotID) throws GeminiMethodNotFoundException
    {
        String           inumeric  = null;
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try
        {
            Class<?>   telephonyClass = Class.forName(telephony.getClass().getName());
            Class<?>[] parameter      = new Class[1];
            parameter[0] = int.class;
            Method   getSimID    = telephonyClass.getMethod(predictedMethodName, parameter);
            Object[] obParameter = new Object[1];
            obParameter[0] = slotID;
            Object ob_phone = getSimID.invoke(telephony, obParameter);
            if (ob_phone != null)
            {
                inumeric = ob_phone.toString();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new GeminiMethodNotFoundException(predictedMethodName);
        }
        return inumeric;
    }
//
//    private static boolean getSIMStateBySlot(Context context, String predictedMethodName, int slotID) throws GeminiMethodNotFoundException
//    {
//        boolean          isReady   = false;
//        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        try
//        {
//            Class<?>   telephonyClass = Class.forName(telephony.getClass().getName());
//            Class<?>[] parameter      = new Class[1];
//            parameter[0] = int.class;
//            Method   getSimStateGemini = telephonyClass.getMethod(predictedMethodName, parameter);
//            Object[] obParameter       = new Object[1];
//            obParameter[0] = slotID;
//            Object ob_phone = getSimStateGemini.invoke(telephony, obParameter);
//            if (ob_phone != null)
//            {
//                int simState = Integer.parseInt(ob_phone.toString());
//                if (simState == TelephonyManager.SIM_STATE_READY)
//                {
//                    isReady = true;
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            throw new GeminiMethodNotFoundException(predictedMethodName);
//        }
//        return isReady;
//    }

    private static class GeminiMethodNotFoundException extends Exception
    {
        /**
         *
         */
        private static final long serialVersionUID = -3241033488141442594L;

        public GeminiMethodNotFoundException(String info)
        {
            super(info);
        }
    }
}
