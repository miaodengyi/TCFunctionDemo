package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.BatteryManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 软件耗电量
 * Created by miao on 2017/9/11.
 */

public class BatterySoftwareActivity extends AppCompatActivity
{
//    private BatteryStatsHelper helper;
//    private PowerProfile profile;
    private TextView tvBattery;
    private StringBuffer stringBuffer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("软件耗电量");
        setContentView(R.layout.activity_battery_software);
//        helper = new BatteryStatsHelper(this);
//        profile  = new PowerProfile(this);
        stringBuffer = new StringBuffer();
        tvBattery = (TextView) findViewById(R.id.tv_battery_software);
//        stringBuffer.append("wifi："+profile.getAveragePower(PowerProfile.POWER_WIFI_ON));

    }

}
