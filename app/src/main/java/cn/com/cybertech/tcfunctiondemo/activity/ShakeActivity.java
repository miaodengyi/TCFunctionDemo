package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.util.VirateUtil;

/**
 * 震动
 * Created by miao on 2017/12/22.
 */

public class ShakeActivity extends AppCompatActivity
{
    private Button btnShake,btnLongShake,btnStopShake;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("震动");
        setContentView(R.layout.activity_shake);
        btnShake = (Button) findViewById(R.id.btn_shake);
        btnShake.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /**
                 * 震动十秒
                 */
                VirateUtil.vibrate(ShakeActivity.this,10000);
            }
        });
        btnLongShake = (Button) findViewById(R.id.btn_long_shake);
        btnLongShake.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /**
                 * 第一个1000是等待一秒
                 * 第二个1000是震动一秒
                 * 第三个1000是等待一秒
                 * 第四个1000是震动一秒
                 * 0---从第三个到第四个循环，一直重复下去
                 * -1--不重复
                 */
                VirateUtil.vibrate(ShakeActivity.this,new long[]{1000, 1000, 1000, 1000}, 0);
            }
        });
        btnStopShake = (Button) findViewById(R.id.btn_stop_shake);
        btnStopShake.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /**
                 * 停止震动
                 */
                VirateUtil.virateCancle(ShakeActivity.this);
            }
        });
    }
}
