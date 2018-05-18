package cn.com.cybertech.tcfunctiondemo.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import cn.com.cybertech.tcfunctiondemo.inter.StepValuePassListener;

import static android.content.Context.SENSOR_SERVICE;

/**
 * 利用计步传感器计步
 * Created by miao on 2017/6/27.
 */

public class StepSensorUtil implements SensorEventListener
{
    //传递数据的接口
    private StepValuePassListener listener;
    private SensorManager         sensorManager;
    private Sensor                countSensor;
    private Sensor                detectorSensor;
    private int CURRENT_STEP = 0;

    //初始化
    public void initSensor(Context context, StepValuePassListener listener)
    {
        this.listener = listener;
        sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        detectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if (countSensor != null)
        {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if (detectorSensor != null)
        {
            sensorManager.registerListener(this, detectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    //获取到之后计步数据变化返回数据
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        //获取当前传感器返回的临时步数
        CURRENT_STEP = (int) event.values[0];
        if (listener!=null)
        {
            listener.stepChanged(CURRENT_STEP);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    //取消注册
    public void unrigisterSensor()
    {
        sensorManager.unregisterListener(this,countSensor);
        sensorManager.unregisterListener(this,detectorSensor);
    }
}
