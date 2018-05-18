package cn.com.cybertech.tcfunctiondemo.activity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.bean.GPSProviderStatus;
import cn.com.cybertech.tcfunctiondemo.inter.GPSLocationListener;
import cn.com.cybertech.tcfunctiondemo.util.GPSLocationManager;

/**
 * gps
 * Created by miao on 2017/9/1.
 */

public class GpsActivity extends AppCompatActivity
{
    private Button btnGps1,btnGps2,btnGps3,btnBaseStation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("GPS");
        setContentView(R.layout.activity_gps_all);
        btnGps1 = (Button) findViewById(R.id.btn_gps_1);
        btnGps1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(GpsActivity.this,GpsOneActivity.class));
            }
        });
        btnGps2 = (Button) findViewById(R.id.btn_gps_2);
        btnGps2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(GpsActivity.this,GpsTwoActivity.class));
            }
        });
        btnGps3 = (Button) findViewById(R.id.btn_gps_3);
        btnGps3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(GpsActivity.this,GpsThreeActivity.class));
            }
        });
        btnBaseStation = (Button) findViewById(R.id.btn_gps_base_station);
        btnBaseStation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(GpsActivity.this,GpsBaseStationActivity.class));
            }
        });
    }

}
