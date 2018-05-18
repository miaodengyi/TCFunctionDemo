package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * Created by 20201002 on 2017/7/4.
 */

public class PassDataActivity extends AppCompatActivity
{
    private EditText etPassData;
    private Button   btnPassData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("传输数据到电脑");
        setContentView(R.layout.activity_pass_data);
        etPassData = (EditText) findViewById(R.id.et_pass_data);
        btnPassData = (Button) findViewById(R.id.btn_pass_data);
        btnPassData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String s = etPassData.getText().toString();
                if (!TextUtils.isEmpty(s))
                {
                    Toast.makeText(PassDataActivity.this,s,Toast.LENGTH_SHORT).show();
                    System.out.println("-----"+s+"-----");
                    Log.d("PassData","-----"+s+"-----");
                    Log.i("PassData","-----"+s+"-----");
                }
            }
        });
    }
}
