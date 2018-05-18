package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.adapter.BatteryAdapter;
import cn.com.cybertech.tcfunctiondemo.bean.BatteryBean;

/**
 * 手机硬件耗电量
 * Created by miao on 2017/9/8.
 */

public class BatteryHardwareActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("硬件耗电量");
        setContentView(R.layout.activity_battery_hardware);
        recyclerView = (RecyclerView) findViewById(R.id.rv_battery);
        recyclerView.setLayoutManager(new LinearLayoutManager(BatteryHardwareActivity.this));
        ArrayList<BatteryBean> list    = getData();
        BatteryAdapter         adapter = new BatteryAdapter(BatteryHardwareActivity.this, list);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<BatteryBean> getData()
    {
        ArrayList<BatteryBean> list  = new ArrayList<>();

        BatteryBean            bean1 = new BatteryBean();
        bean1.setIcon(getResources().getDrawable(R.drawable.ic_gprs_green));
        bean1.setName("信号待机");
        bean1.setBattery("70%");
        list.add(bean1);

        BatteryBean bean2 = new BatteryBean();
        bean2.setIcon(getResources().getDrawable(R.drawable.ic_screen_green));
        bean2.setName("屏幕");
        bean2.setBattery("11%");
        list.add(bean2);

        BatteryBean bean3 = new BatteryBean();
        bean3.setIcon(getResources().getDrawable(R.drawable.ic_power_green));
        bean3.setName("手机待机");
        bean3.setBattery("4%");
        list.add(bean3);

        BatteryBean bean4 = new BatteryBean();
        bean4.setIcon(getResources().getDrawable(R.drawable.ic_wifi_green));
        bean4.setName("WLAN");
        bean4.setBattery("0.6%");
        list.add(bean4);

        BatteryBean bean5 = new BatteryBean();
        bean5.setIcon(getResources().getDrawable(R.drawable.ic_phone_green));
        bean5.setName("语音通话");
        bean5.setBattery("0.1%");
        list.add(bean5);

        BatteryBean bean6 = new BatteryBean();
        bean6.setIcon(getResources().getDrawable(R.drawable.ic_bluetooth_green));
        bean6.setName("蓝牙");
        bean6.setBattery("0.1%");
        list.add(bean6);

        return list;
    }
}
