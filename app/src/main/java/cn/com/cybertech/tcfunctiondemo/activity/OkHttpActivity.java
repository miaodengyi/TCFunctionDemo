package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 测试自己的okhttp
 * Created by miao on 2017/6/21.
 */

public class OkHttpActivity extends AppCompatActivity
{
    private Button btnGet;
    private TextView tvGet;

    private static final String urlGet = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("测试OKHTTP");
        setContentView(R.layout.activity_okhttp);
        tvGet = (TextView) findViewById(R.id.tv_get_request);
        btnGet = (Button) findViewById(R.id.btn_okhttp);
        btnGet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }
}
