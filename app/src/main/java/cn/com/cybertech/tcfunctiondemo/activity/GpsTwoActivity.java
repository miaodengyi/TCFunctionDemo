package cn.com.cybertech.tcfunctiondemo.activity;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.util.GpsManager;

/**
 * gps方法2
 * Created by miao on 2017/9/1.
 */

public class GpsTwoActivity extends AppCompatActivity
{
    private TextView tvGps;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("GPS方法2");
        setContentView(R.layout.activity_gps);
        tvGps = (TextView) findViewById(R.id.tv_gps);
        location();
    }

    //陆总的GPSmanager获取的
    void location()
    {
        GpsManager.getInstance().init(this, 6000, 100, new GpsManager.LocationChangedListener()
        {
            @Override
            public void onLocationChanged(Location location)
            {
                if (location != null)
                {
                    tvGps.setText("经度："+GpsManager.getInstance().getLocation().getLongitude()+"\n"
                            +"纬度"+GpsManager.getInstance().getLocation().getLatitude());
                }
            }
        });
        if (GpsManager.getInstance().getLocation() != null)
        {
            tvGps.setText("经度："+GpsManager.getInstance().getLocation().getLongitude()+"\n"
                    +"纬度"+GpsManager.getInstance().getLocation().getLatitude());
        }
    }

}
