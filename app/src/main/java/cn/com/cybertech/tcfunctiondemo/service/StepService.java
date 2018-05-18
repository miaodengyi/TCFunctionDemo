package cn.com.cybertech.tcfunctiondemo.service;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import cn.com.cybertech.tcfunctiondemo.inter.StepValuePassListener;
import cn.com.cybertech.tcfunctiondemo.util.DateUtils;
import cn.com.cybertech.tcfunctiondemo.util.SharedPreferenceUtils;
import cn.com.cybertech.tcfunctiondemo.util.StepCount;
import cn.com.cybertech.tcfunctiondemo.util.StepSensorUtil;

/**
 * 计步的服务
 * Created by miao on 2017/6/26.
 */

public class StepService extends Service
{

    private static final String TAG = "StepService";
    private SensorManager sensorManager;
    /**
     * 加速度传感器中获取的步数
     */
    private StepCount     mStepCount;
    /**
     * 当前所走的步数
     */
    private int CURRENT_STEP = 0;
    //数据库里面之前的步数
    private int FORMERSTEPS = 0;
    //数据传递
    private StepValuePassListener listener;
    private StepSensorUtil        stepSensorUtil;

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return new MyBinder();
    }

    /**
     * 获取传感器实例
     */
    public void startStepDetector(StepValuePassListener listener)
    {
        this.listener = listener;
        if (sensorManager != null)
        {
            sensorManager = null;
        }
        // 获取传感器管理器的实例
        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        //android4.4以后可以使用计步传感器
        int VERSION_CODES = Build.VERSION.SDK_INT;
        saveSteps(true, DateUtils.getInstance().getDate(), 0);
        if (VERSION_CODES >= 19)
        {
            //            addCountStepListener();
            addBasePedometerListener();
        }
        else
        {
            addBasePedometerListener();
        }
    }

    //保存步数
    private void saveSteps(boolean isFirstTime, String date, int steps)
    {
        //当今天的数据SharedPreference里面有的时候，先取出来
        if (SharedPreferenceUtils.contains(this, date))
        {
            if (isFirstTime)
            {
                Object o = SharedPreferenceUtils.get(this, date, 0);
                if (o != null)
                {
                    FORMERSTEPS = (int) o;
                }
            }
            else
            {
                //将最新的数据保存
                SharedPreferenceUtils.put(this, date, steps);
            }
        }
        else
        {
            //将最新的数据保存
            SharedPreferenceUtils.put(this, date, steps);
        }
    }

    /**
     * 添加传感器监听
     */
    private void addCountStepListener()
    {
        if (stepSensorUtil == null)
        {
            stepSensorUtil = new StepSensorUtil();
            stepSensorUtil.initSensor(this, new StepValuePassListener()
            {
                @Override
                public void stepChanged(int steps)
                {
                    if (listener != null)
                    {
                        listener.stepChanged(steps);
                    }
                }
            });
        }
        else
        {
            stepSensorUtil.initSensor(this, new StepValuePassListener()
            {
                @Override
                public void stepChanged(int steps)
                {
                    if (listener != null)
                    {
                        listener.stepChanged(steps);
                    }
                }
            });
        }
    }


    /**
     * 通过加速度传感器来记步
     */
    private void addBasePedometerListener()
    {
        mStepCount = new StepCount();
        mStepCount.setSteps(CURRENT_STEP);
        // 获得传感器的类型，这里获得的类型是加速度传感器
        // 此方法用来注册，只有注册过才会生效，参数：SensorEventListener的实例，Sensor的实例，更新速率
        Sensor  sensor      = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        boolean isAvailable = sensorManager.registerListener(mStepCount.getStepDetector(), sensor, SensorManager.SENSOR_DELAY_UI);
        mStepCount.initListener(new StepValuePassListener()
        {
            @Override
            public void stepChanged(int steps)
            {
                CURRENT_STEP = steps;
                if (listener != null)
                {
                    listener.stepChanged(CURRENT_STEP+FORMERSTEPS);
                }
            }
        });
        if (isAvailable)
        {
            Log.v(TAG, "加速度传感器可以使用");
        }
        else
        {
            Log.v(TAG, "加速度传感器无法使用");
        }
    }

    //service被销毁的时候取消sensor注册
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        saveSteps(false,DateUtils.getInstance().getDate(), CURRENT_STEP+ FORMERSTEPS);
        if (stepSensorUtil != null)
        {
            stepSensorUtil.unrigisterSensor();
        }
    }

    //service结束绑定的时候取消sensor注册
    @Override
    public boolean onUnbind(Intent intent)
    {
        if (stepSensorUtil != null)
        {
            stepSensorUtil.unrigisterSensor();
        }
        saveSteps(false,DateUtils.getInstance().getDate(), CURRENT_STEP+ FORMERSTEPS);
        return super.onUnbind(intent);
    }

    //用于service与activity通信用的内部类Binder
    public class MyBinder extends Binder
    {
        public StepService startStepService()
        {
            return StepService.this;
        }
    }

}
