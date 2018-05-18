package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 以时间命名文件方式
 * Created by miao on 2018/1/17.
 */

public class TimeFileActivity extends AppCompatActivity
{
    private TextView tvTimeFile;
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("时间命名文件");
        setContentView(R.layout.activity_time_file);
        tvTimeFile = (TextView) findViewById(R.id.tv_time_file);
        String s = formatter.format(new Date());
        tvTimeFile.setText(s);
    }


}
