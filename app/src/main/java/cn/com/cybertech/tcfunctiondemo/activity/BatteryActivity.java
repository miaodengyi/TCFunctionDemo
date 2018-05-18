package cn.com.cybertech.tcfunctiondemo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * Created by 20201002 on 2017/5/10.
 */

public class BatteryActivity extends AppCompatActivity
{
    private TextView tvBattery;
    private Button btnHardware,btnSoftware;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("手机电池");
        setContentView(R.layout.activity_battery);
        tvBattery = (TextView) findViewById(R.id.tv_battery);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("电池容量1： "+String.valueOf(getBatteryCapacity1())+"\n");
        stringBuffer.append("电池容量2： "+String.valueOf(getBatteryCapacity2(this))+"\n");
        getBatteryCapcity3();
        tvBattery.setText(stringBuffer);
        btnHardware = (Button) findViewById(R.id.btn_hardware_battery);
        btnHardware.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(BatteryActivity.this,BatteryHardwareActivity.class));
            }
        });
        btnSoftware = (Button) findViewById(R.id.btn_software_battery);
        btnSoftware.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //跳转到系统电池的耗电排行
//                Intent      powerUsageIntent = new Intent(Intent.ACTION_POWER_USAGE_SUMMARY);
//                ResolveInfo resolveInfo      = getPackageManager().resolveActivity(powerUsageIntent, 0);
//                if(resolveInfo != null){
//                    startActivity(powerUsageIntent);
//                }
                startActivity(new Intent(BatteryActivity.this,BatterySoftwareActivity.class));
            }
        });
    }

    public double getBatteryCapacity1()
    {
        Object mPowerProfile_ = null;

        final String POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile";

        try
        {
            mPowerProfile_ = Class.forName(POWER_PROFILE_CLASS).getConstructor(Context.class).newInstance(this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            double batteryCapacity = (Double) Class.forName(POWER_PROFILE_CLASS).getMethod("getAveragePower", java.lang.String.class).invoke(mPowerProfile_, "battery.capacity");
//            Toast.makeText(MainActivity.this, batteryCapacity + " mah", Toast.LENGTH_LONG).show();
            return batteryCapacity;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public long getBatteryCapacity2(Context ctx)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            BatteryManager mBatteryManager = (BatteryManager) ctx.getSystemService(Context.BATTERY_SERVICE);
            Long           chargeCounter   = mBatteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
            Long           capacity        = mBatteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

            if (chargeCounter != null && capacity != null)
            {
                long value = (long) (((float) chargeCounter / (float) capacity) * 100f);
                return value;
            }
        }

        return 0;
    }

    public void getBatteryCapcity3()
    {
        BatteryManager batteryManager=(BatteryManager)getSystemService(BATTERY_SERVICE);
        System.out.println("-------"+batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER));
        System.out.println("-------"+batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE));
        System.out.println("-------"+batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW));
    }


}
