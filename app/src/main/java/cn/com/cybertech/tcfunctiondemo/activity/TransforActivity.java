package cn.com.cybertech.tcfunctiondemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.record.activity.*;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 第三方库调用界面
 * Created by miao on 2018/5/17.
 */

public class TransforActivity extends AppCompatActivity
{
    //录音
    private Button btnRecord;
    //录音返回结果
    private TextView tvRecordResult;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("第三方库调用");
        setContentView(R.layout.activity_transfor);
        init();
    }

    private void init()
    {
        tvRecordResult = (TextView) findViewById(R.id.tv_record_result);
        btnRecord = (Button) findViewById(R.id.btn_record);
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransforActivity.this, RecordActivity.class));
            }
        });
    }
}
