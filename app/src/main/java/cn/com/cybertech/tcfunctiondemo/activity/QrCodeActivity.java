package cn.com.cybertech.tcfunctiondemo.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 扫描二维码
 * Created by miao on 2017/10/16.
 */

public class QrCodeActivity extends AppCompatActivity
{
    private Button btnSweepQrcode,btnLocalQrcode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("二维码");
        setContentView(R.layout.activity_qrcode);
        btnSweepQrcode = (Button) findViewById(R.id.btn_sweep_qrcode);
        btnSweepQrcode.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
        btnLocalQrcode = (Button) findViewById(R.id.btn_local_qrcode);
        btnLocalQrcode.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }
}
