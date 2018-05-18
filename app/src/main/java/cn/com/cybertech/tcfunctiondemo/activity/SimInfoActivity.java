package cn.com.cybertech.tcfunctiondemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 获取sim卡信息
 * Created by miao on 2017/9/18.
 */

public class SimInfoActivity extends AppCompatActivity
{
    private TextView tvSimInfo;
    private TelephonyManager mTelephonyMgr;
    private StringBuffer stringBuffer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("Sim卡信息");
        setContentView(R.layout.activity_sim_info);
        stringBuffer = new StringBuffer();
        tvSimInfo = (TextView) findViewById(R.id.tv_sim_info);
        mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephonyMgr.getSimState()==mTelephonyMgr.SIM_STATE_READY)
        {
            stringBuffer.append("手机卡状态良好"+"\n");
        }else if (mTelephonyMgr.getSimState()==mTelephonyMgr.SIM_STATE_ABSENT)
        {
            stringBuffer.append("没有手机卡"+"\n");
        }else
        {
            stringBuffer.append("SIM被锁或处于未知状态"+"\n");
        }
        stringBuffer.append("电话状态[0 无活动/1 响铃/2 摘机]:"+mTelephonyMgr.getCallState()+"\n");
        stringBuffer.append("电话方位:"+mTelephonyMgr.getCellLocation()+"\n");
        stringBuffer.append("唯一的设备ID:" + mTelephonyMgr.getDeviceId()+"\n");
        stringBuffer.append("设备的软件版本号:" + mTelephonyMgr.getDeviceSoftwareVersion()+"\n");
        stringBuffer.append("手机号:" + mTelephonyMgr.getLine1Number()+"\n");
        stringBuffer.append("附近的电话的信息:" + mTelephonyMgr.getNeighboringCellInfo()+"\n");
        stringBuffer.append("获取ISO标准的国家码，即国际长途区号:" + mTelephonyMgr.getNetworkCountryIso()+"\n");
        stringBuffer.append("MCC+MNC:" + mTelephonyMgr.getNetworkOperator()+"\n");
        stringBuffer.append("(当前已注册的用户)的名字:" + mTelephonyMgr.getNetworkOperatorName()+"\n");
        stringBuffer.append("当前使用的网络类型:" + mTelephonyMgr.getNetworkType()+"\n");
        stringBuffer.append("手机类型:" + mTelephonyMgr.getPhoneType()+"\n");
        stringBuffer.append("SIM卡的国家码:" + mTelephonyMgr.getSimCountryIso()+"\n");
        stringBuffer.append("获取SIM卡提供的移动国家码和移动网络码.5或6位的十进制数字:" + mTelephonyMgr.getSimOperator()+"\n");
        stringBuffer.append("服务商名称:" + mTelephonyMgr.getSimOperatorName()+"\n");
        stringBuffer.append("SIM卡的序列号:" + mTelephonyMgr.getSimSerialNumber()+"\n");
        stringBuffer.append("SIM的状态信息:" + mTelephonyMgr.getSimState()+"\n");
        stringBuffer.append("唯一的用户ID:" + mTelephonyMgr.getSubscriberId()+"\n");
        stringBuffer.append("取得和语音邮件相关的标签，即为识别符:" + mTelephonyMgr.getVoiceMailAlphaTag()+"\n");
        stringBuffer.append("获取语音邮件号码:" + mTelephonyMgr.getVoiceMailNumber()+"\n");
        stringBuffer.append("ICC卡是否存在:" + mTelephonyMgr.hasIccCard()+"\n");
        stringBuffer.append("是否漫游:" + mTelephonyMgr.isNetworkRoaming()+"\n");
        stringBuffer.append("获取数据活动状态:" + mTelephonyMgr.getDataActivity()+"\n");
        stringBuffer.append("获取数据连接状态:" + mTelephonyMgr.getDataState()+"\n");
        tvSimInfo.setText(stringBuffer);
    }
}
