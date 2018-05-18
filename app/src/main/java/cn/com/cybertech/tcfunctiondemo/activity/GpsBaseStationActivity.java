package cn.com.cybertech.tcfunctiondemo.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 通过地磁传感器定位
 * Created by miao on 2017/10/20.
 */

public class GpsBaseStationActivity extends AppCompatActivity implements SensorEventListener
{
    private TextView      tv;
    //声明传感器管理对象
    private SensorManager sensorManger;
    //声明传感器对象
    private Sensor        magnetic_field_sensor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("地磁传感器定位");
        setContentView(R.layout.activity_gps);
        tv = (TextView) findViewById(R.id.tv_gps);
        //从context中获得传感器管理对象
        sensorManger = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        //获得磁场传感器对象
        magnetic_field_sensor = sensorManger.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        //注册传感器对象，此方法主要用在onResume中，此处直接在创建时使用
        sensorManger.registerListener(this, magnetic_field_sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // 传感器精度发生变化时，调用此方法
        Toast.makeText(this, "精度发生改变", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSensorChanged(SensorEvent arg0) {
        // 传感器得到的数值
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("得到的地磁数值："+"\n");
        stringBuffer.append("X:"+arg0.values[0]+"\n");
        stringBuffer.append("Y:"+arg0.values[1]+"\n");
        stringBuffer.append("Z:"+arg0.values[2]+"");
    }


}
