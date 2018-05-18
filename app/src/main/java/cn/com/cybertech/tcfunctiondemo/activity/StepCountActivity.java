package cn.com.cybertech.tcfunctiondemo.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.inter.StepValuePassListener;
import cn.com.cybertech.tcfunctiondemo.service.StepService;
import cn.com.cybertech.tcfunctiondemo.util.DateUtils;
import cn.com.cybertech.tcfunctiondemo.util.SharedPreferenceUtils;

/**
 * 计步测试
 * Created by miao on 2017/6/26.
 */

public class StepCountActivity extends AppCompatActivity
{
    private TextView tvStepdetector, tvStepcounter, tvSdk, tvKind, tvStep;
    private StepService.MyBinder binder;
    private boolean isBind = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("计步测试");
        setContentView(R.layout.activity_step_count);
        //Sensor是否可用
        tvStepdetector = (TextView) findViewById(R.id.tv_stepdetector);
        tvStepcounter = (TextView) findViewById(R.id.tv_stepcounter);
        //SDK版本，是否19以上
        tvSdk = (TextView) findViewById(R.id.tv_sdk);
        //加速传感器还是计步传感器
        tvKind = (TextView) findViewById(R.id.tv_kind);
        //获取到的步数
        tvStep = (TextView) findViewById(R.id.tv_step);
        isSupportStepCountSensor(StepCountActivity.this);
        String  s = String.valueOf(SharedPreferenceUtils.get(this, DateUtils.getInstance().getDate(), 0)) ;
        if (!TextUtils.isEmpty(s))
        {
            tvStep.setText(s);
        }else
        {
            tvStep.setText("0");
        }

    }

    /**
     * 判断该设备是否支持计歩
     */
    public void isSupportStepCountSensor(Context context)
    {
        tvSdk.setText(Build.VERSION.SDK_INT + "");
        // 获取传感器管理器的实例
        SensorManager sensorManager  = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        Sensor        countSensor    = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        Sensor        detectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if (countSensor == null)
        {
            tvStepcounter.setText("不可用");
        }
        else
        {
            tvStepcounter.setText("可用");
            tvKind.setText("获取google计步器");
        }
        if (detectorSensor == null)
        {
            tvStepdetector.setText("不可用");
        }
        else
        {
            tvStepdetector.setText("可用");
        }

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        //开始绑定service
        setupService();
    }

    //销毁时候取消绑定
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (isBind)
        {
            unbindService(conn);
        }
    }

    //通过绑定service来启动
    private void setupService()
    {
        Intent intent = new Intent(this, StepService.class);
        isBind = bindService(intent, conn, Context.BIND_AUTO_CREATE);
//        startService(intent);
    }

    //连接service，跟service进行通信
    ServiceConnection conn = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            try
            {
                binder = (StepService.MyBinder) service;
                //获取实例
                StepService stepService = binder.startStepService();
                stepService.startStepDetector(new StepValuePassListener()
                {
                    @Override
                    public void stepChanged(int steps)
                    {
                        //更新UI
                        tvStep.setText(steps + "");
                    }
                });
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
        }
    };

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        if (isBind)
        {
            unbindService(conn);
            isBind = false;
        }
    }
}
