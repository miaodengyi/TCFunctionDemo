package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.util.SharedPreferenceUtils;

/**
 * 倒计时
 * Created by miao on 2017/7/13.
 */

public class CountDownTimerActivity extends AppCompatActivity
{
    private TextView tvCountDownTimer;

    //时间为一分钟
    private static final int totalTime = 3600000;
    //间隔为一秒
    private static final int spaceTime = 1000;
    //剩余时间
    private              int lastTime  = 0;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("倒计时器");
        setContentView(R.layout.activity_count_down_timer);
        tvCountDownTimer = (TextView) findViewById(R.id.tv_count_down_timer);
        int initTime;
        if (SharedPreferenceUtils.contains(CountDownTimerActivity.this, "lastTime"))
        {
            int time = (int) SharedPreferenceUtils.get(CountDownTimerActivity.this, "lastTime", 60000);
            if (time == 0)
            {
                initTime = totalTime;
            }
            else
            {
                initTime = time;
            }
        }
        else
        {
            initTime = totalTime;
        }
        /*
         * 第一个是总时间  60000是一分钟
         * 第二个是间隔时间  1000是一秒
         */
        countDownTimer = new CountDownTimer(initTime, spaceTime)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                tvCountDownTimer.setText(millisUntilFinished / 1000 + "s");
                lastTime = (int) millisUntilFinished;
            }

            @Override
            public void onFinish()
            {
                tvCountDownTimer.setText("结束");
                countDownTimer.start();
            }
        };
        countDownTimer.start();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (lastTime != 0)
        {
            SharedPreferenceUtils.put(CountDownTimerActivity.this, "lastTime", lastTime);
        }
    }
}
