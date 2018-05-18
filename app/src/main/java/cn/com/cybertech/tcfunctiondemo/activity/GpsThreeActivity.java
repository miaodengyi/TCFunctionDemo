package cn.com.cybertech.tcfunctiondemo.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * gps方法3
 * Created by miao on 2017/9/1.
 */

public class GpsThreeActivity extends AppCompatActivity
{
    private TextView        tvGps;
    private LocationManager locationManager;// 位置管理类
    private String          provider;// 位置提供器
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("GPS方法3");
        setContentView(R.layout.activity_gps);
        tvGps = (TextView) findViewById(R.id.tv_gps);

        // 获得LocationManager的实例
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // 获取所有可用的位置提供器
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER))
        {
            //优先使用gps
            provider = LocationManager.GPS_PROVIDER;
        }
        else if (providerList.contains(LocationManager.NETWORK_PROVIDER))
        {
            provider = LocationManager.NETWORK_PROVIDER;
        }
        else
        {
            // 没有可用的位置提供器
            Toast.makeText(GpsThreeActivity.this, "没有位置提供器可供使用", Toast.LENGTH_LONG).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            // 显示当前设备的位置信息
            String firstInfo = "第一次请求的信息";
            showLocation(location, firstInfo);
        } else {
            String info = "无法获得当前位置";
            Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
            tvGps.setText(info);
            if (provider.equals(LocationManager.GPS_PROVIDER))
            {
                provider = LocationManager.NETWORK_PROVIDER;
            }
            Location location2 = locationManager.getLastKnownLocation(provider);
            if (location2 != null)
            {
                tvGps.setText("经度："+location2.getLatitude()+"\n"+"纬度"+location2.getLongitude());
            }else {
                Toast.makeText(this,"请稍后再试",Toast.LENGTH_SHORT).show();
            }
        }

        // 更新当前位置
        locationManager.requestLocationUpdates(provider, 10 * 1000, 1,
                locationListener);

    }

    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            // 关闭程序时将监听器移除
            locationManager.removeUpdates(locationListener);
        }

    };

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onLocationChanged(Location location) {
            // 设备位置发生改变时，执行这里的代码
            String changeInfo = "隔10秒刷新的提示：\n 时间：" + sdf.format(new Date())
                    + ",\n当前的经度是：" + location.getLongitude() + ",\n 当前的纬度是："
                    + location.getLatitude();
            showLocation(location, changeInfo);
        }
    };

    /**
     * 显示当前设备的位置信息
     *
     * @param location
     */
    private void showLocation(Location location, String changeInfo) {
        // TODO Auto-generated method stub
        String currentLocation = "当前的经度是：" + location.getLongitude() + ",\n"
                + "当前的纬度是：" + location.getLatitude();
        tvGps.setText(currentLocation+"\n"+changeInfo);
    }

}
