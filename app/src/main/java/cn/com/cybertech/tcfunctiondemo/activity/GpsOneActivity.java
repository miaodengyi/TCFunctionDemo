package cn.com.cybertech.tcfunctiondemo.activity;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.bean.GPSProviderStatus;
import cn.com.cybertech.tcfunctiondemo.inter.GPSLocationListener;
import cn.com.cybertech.tcfunctiondemo.util.GPSLocationManager;

/**
 * gps方法1
 * Created by miao on 2017/9/1.
 */

public class GpsOneActivity extends AppCompatActivity
{
    private TextView           tvGps;
    private GPSLocationManager gpsLocationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("GPS方法1");
        setContentView(R.layout.activity_gps);
        tvGps = (TextView) findViewById(R.id.tv_gps);

        gpsLocationManager = GPSLocationManager.getInstances(GpsOneActivity.this);
        //开启定位
        gpsLocationManager.start(new MyListener());
    }
    class MyListener implements GPSLocationListener
    {

        @Override
        public void UpdateLocation(Location location) {
            if (location != null) {
                tvGps.setText("经度：" + location.getLongitude() + "\n纬度：" + location.getLatitude());
                //                Toast.makeText(MainActivity.this,"经度：" + location.getLongitude() + "\n纬度：" + location.getLatitude(),Toast.LENGTH_LONG).show();
            }
            else
            {
                tvGps.setText("获取不到GPS数据");
            }
        }

        @Override
        public void UpdateStatus(String provider, int status, Bundle extras) {
            if ("gps" == provider) {
                Toast.makeText(GpsOneActivity.this, "定位类型：" + provider, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void UpdateGPSProviderStatus(int gpsStatus) {
            switch (gpsStatus) {
                case GPSProviderStatus.GPS_ENABLED:
                    Toast.makeText(GpsOneActivity.this, "GPS开启", Toast.LENGTH_SHORT).show();
                    break;
                case GPSProviderStatus.GPS_DISABLED:
                    Toast.makeText(GpsOneActivity.this, "GPS关闭", Toast.LENGTH_SHORT).show();
                    break;
                case GPSProviderStatus.GPS_OUT_OF_SERVICE:
                    Toast.makeText(GpsOneActivity.this, "GPS不可用", Toast.LENGTH_SHORT).show();
                    break;
                case GPSProviderStatus.GPS_TEMPORARILY_UNAVAILABLE:
                    Toast.makeText(GpsOneActivity.this, "GPS暂时不可用", Toast.LENGTH_SHORT).show();
                    break;
                case GPSProviderStatus.GPS_AVAILABLE:
                    Toast.makeText(GpsOneActivity.this, "GPS可用啦", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在onPause()方法终止定位
        gpsLocationManager.stop();
    }

}
