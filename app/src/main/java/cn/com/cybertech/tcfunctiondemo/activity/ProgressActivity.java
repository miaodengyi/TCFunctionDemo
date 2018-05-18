package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.util.LoadingProgressHelper;

/**
 * 测试一个通用好看的progress
 * Created by miao on 2017/6/7.
 */

public class ProgressActivity extends AppCompatActivity
{
    private Button btnProgress;
    private LoadingProgressHelper loadingProgressHelper;
    private static final int DISMISS = 0x00;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("进度条");
        setContentView(R.layout.activity_progress);
        btnProgress = (Button) findViewById(R.id.btn_show_progress);
        loadingProgressHelper = LoadingProgressHelper.create(ProgressActivity.this);
        btnProgress.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    if (!loadingProgressHelper.isShowing())
                    {
                        loadingProgressHelper.show();
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                thread = new Mythread();
                thread.start();
            }
        });

    }

    private class Mythread extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            if (loadingProgressHelper.isShowing())
            {
                loadingProgressHelper.dismiss();
            }
            super.run();
        }
    }

    private Mythread thread;

    @Override
    protected void onDestroy()
    {
        if (loadingProgressHelper.isShowing())
        {
            loadingProgressHelper.dismiss();
        }
        super.onDestroy();
    }
}
