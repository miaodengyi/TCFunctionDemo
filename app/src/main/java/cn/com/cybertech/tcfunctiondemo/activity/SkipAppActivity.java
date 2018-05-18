package cn.com.cybertech.tcfunctiondemo.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 跳转应用
 * Created by miao on 2017/12/15.
 */

public class SkipAppActivity extends AppCompatActivity
{
    private Button btnZhejiangQrcode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("跳转应用");
        setContentView(R.layout.activity_skip_app);
        btnZhejiangQrcode = (Button) findViewById(R.id.btn_zhejiang_qrcode);
        btnZhejiangQrcode.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    Intent intent = new Intent();
                    ComponentName componentName = new ComponentName("cn.com.cybertech.zhejiang_qrcode","com.zbar.lib.CaptureActivity");
                    intent.setComponent(componentName);
                    startActivity(intent);
                }catch (Exception e)
                {
                    Toast.makeText(SkipAppActivity.this,"请先安装应用--警综平台",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
