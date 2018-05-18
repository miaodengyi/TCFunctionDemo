package cn.com.cybertech.tcfunctiondemo.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.util.AudioRecoderUtils;

/**
 * 录音的界面
 * Created by miao on 2018/5/17.
 */

public class VoiceActivity extends AppCompatActivity
{
    //录音文件列表，录音准备，录音中
    private ImageView ivList, ivReady, ivRecording;
    //录音时长
    private TextView tvTime;
    //录音状态
    private boolean isRecording = false;
    //录音工具类
    private AudioRecoderUtils audioRecoderUtils;
    //录音文件保存路径
    private String voicePath;
    //录音时长
    private String voiceTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
        //权限申请
        if (shouldAskPermissions())
        {
            askPermissions();
        }
        init();
        audioRecoderUtils = new AudioRecoderUtils(this);
        //录音回调
        audioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener()
        {
            //录音中
            //db是分贝
            //time为时长
            @Override
            public void onUpdate(double db, long time)
            {
                tvTime.setText(getTime(time));
            }

            //录音结束
            //filePath是保存的路径
            @Override
            public void onStop(String filePath)
            {
                voicePath = filePath;
            }
        });
    }

    //权限申请
    protected boolean shouldAskPermissions()
    {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions()
    {
        String[] permissions =
                {
                        "android.permission.RECORD_AUDIO",
                        "android.permission.WRITE_EXTERNAL_STORAGE",
                        "android.permission.WAKE_LOCK"
                };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }


    public static String getTime(long time)
    {
        String str = "";
        time = time / 1000;
        int s = (int) (time % 60);
        int m = (int) (time / 60 % 60);
        int h = (int) (time / 3600);
        if (s < 10 && m < 10 && h < 10)
        {
            str = "0" + h + ":0" + m + ":0" + s;
        } else if (m < 10 && h < 10)
        {
            str = "0" + h + ":0" + m + ":" + s;
        } else if (h < 10)
        {
            str = "0" + h + ":" + m + ":" + s;
        } else
        {
            str = h + ":" + m + ":" + s;
        }
        return str;
    }

    private void init()
    {
        tvTime = (TextView) findViewById(R.id.tv_voice_time);
        ivList = (ImageView) findViewById(R.id.iv_voice_list);
        ivReady = (ImageView) findViewById(R.id.iv_voice_ready);
        ivRecording = (ImageView) findViewById(R.id.iv_voice_recording);
        ivList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(VoiceActivity.this,VoiceListActivity.class));
            }
        });
        ivReady.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                audioRecoderUtils.startRecord();
                isRecording = true;
                ivReady.setVisibility(View.INVISIBLE);
                ivRecording.setVisibility(View.VISIBLE);
            }
        });
        ivRecording.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                audioRecoderUtils.stopRecord();
                isRecording = false;
                ivReady.setVisibility(View.VISIBLE);
                ivRecording.setVisibility(View.INVISIBLE);
                voiceTime = tvTime.getText().toString();
                tvTime.setText("00:00:00");
                Toast.makeText(VoiceActivity.this, "录音文件地址：" + voicePath + "\n" + "时长：" + voiceTime, Toast.LENGTH_LONG).show();
            }
        });
    }
}
