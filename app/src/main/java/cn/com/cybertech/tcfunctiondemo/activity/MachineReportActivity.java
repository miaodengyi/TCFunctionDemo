package cn.com.cybertech.tcfunctiondemo.activity;

/*
 * 互联网域管控的主界面
 * Created by miao on 2017/7/28.
 */

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.com.cybertech.tcfunctiondemo.R;

public class MachineReportActivity extends AppCompatActivity
{
    StringBuffer stringBuffer = new StringBuffer();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("验机报告");
        setContentView(R.layout.activity_machine_report);
        getPhoneInfo();
    }

    private void getPhoneInfo()
    {
        //机型
        String model = Build.MODEL;
        //CPU
        String CPU = Build.CPU_ABI;
        stringBuffer.append("机型："+model);
        stringBuffer.append("CPU："+CPU);
    }
}
