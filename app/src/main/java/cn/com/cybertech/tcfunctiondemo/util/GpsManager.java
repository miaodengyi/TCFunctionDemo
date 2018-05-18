package cn.com.cybertech.tcfunctiondemo.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.Map;

final public class GpsManager implements LocationListener
{
    private static GpsManager              instance;
    private        LocationChangedListener listener;
    private        Context                 context;
    private        LocationManager         locationManager;
    private        Location                location;
    private boolean isInit      = false;
    private long    minTime     = 1000;
    private float   minDistance = 5;

    private GpsManager()
    {
    }

    public static GpsManager getInstance()
    {
        if (instance == null)
        {
            instance = new GpsManager();
        }
        return instance;
    }

    public void init(Context context, long minTime, float minDistance, LocationChangedListener listener)
    {
        if (isInit) return;
        isInit = true;

        this.context = context;
        this.minTime = minTime;
        this.minDistance = minDistance;
        this.listener = listener;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        requestLocationUpdates();
    }

    public void addParams(Map<String, String> params)
    {
        if (params == null || instance == null || instance.getLocation() == null) return;
        params.put("gps_jd", String.valueOf(instance.getLocation().getLongitude()));
        params.put("gps_wd", String.valueOf(instance.getLocation().getLatitude()));
    }

    public Location getLocation()
    {
        return location;
    }

    public void requestLocationUpdates()
    {
        if (context == null) return;

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, this);
    }

    public void setOnLocationChangedListener(LocationChangedListener listener)
    {
        this.listener = listener;
    }

    public void removeUpdates()
    {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location)
    {
        updateLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
    }

    @Override
    public void onProviderEnabled(String provider)
    {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        updateLocation(locationManager.getLastKnownLocation(provider));
    }

    @Override
    public void onProviderDisabled(String provider)
    {
    }

    public void updateLocation(Location location)
    {
        if (location == null) return;
        this.location = location;

        if (listener != null)
        {
            listener.onLocationChanged(location);
        }

        StringBuffer sb = new StringBuffer();
        sb.append("实时的位置信息：\n经度：");
        sb.append(location.getLongitude());
        sb.append("\n纬度：");
        sb.append(location.getLatitude());
        sb.append("\n高度：");
        sb.append(location.getAltitude());
        sb.append("\n速度：");
        sb.append(location.getSpeed());
        sb.append("\n方向：");
        sb.append(location.getBearing());
        sb.append("\n精度：");
        sb.append(location.getAccuracy());
    }

    public interface LocationChangedListener
    {
        void onLocationChanged(Location location);
    }
}
