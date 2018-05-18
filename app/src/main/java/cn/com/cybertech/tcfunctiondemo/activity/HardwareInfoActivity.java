package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 手机硬件信息
 * Created by miao on 2017/10/24.
 */

public class HardwareInfoActivity extends AppCompatActivity
{
    private TextView tvHardwareInfo;
    private StringBuffer stringBuffer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("硬件信息");
        setContentView(R.layout.activity_hardware_info);
        tvHardwareInfo = (TextView) findViewById(R.id.tv_hardware_info);
        stringBuffer = new StringBuffer();
        stringBuffer.append("MODEL:"+Build.MODEL+"\n");
        stringBuffer.append("DISPLAY:"+Build.DISPLAY);
        tvHardwareInfo.setText(stringBuffer);
    }
}
