package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 验证Environment获取路径的几种方式
 * Created by miao on 2017/6/2.
 */

public class PathActivity extends AppCompatActivity
{

    private TextView tvPath;
    private StringBuffer stringBuffer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("获取系统路径");
        setContentView(R.layout.activity_path);
        tvPath = (TextView) findViewById(R.id.tv_path);
        stringBuffer = new StringBuffer();
        getPath();
        tvPath.setText(stringBuffer);
    }

    private void getPath()
    {
        String path1 = Environment.getExternalStorageState();
        stringBuffer.append("getExternalStorageState:  "+"\n");
        stringBuffer.append(path1+"\n\n");
        String path2 = Environment.getDataDirectory().toString();
        stringBuffer.append("getDataDirectory:  "+"\n");
        stringBuffer.append(path2+"\n\n");
        String path3 = Environment.getDownloadCacheDirectory().toString();
        stringBuffer.append("getDownloadCacheDirectory:  "+"\n");
        stringBuffer.append(path3+"\n\n");
        String path4 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).toString();
        stringBuffer.append("getExternalStoragePublicDirectory--DIRECTORY_MUSIC:  "+"\n");
        stringBuffer.append(path4+"\n\n");
        String path5 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS).toString();
        stringBuffer.append("getExternalStoragePublicDirectory--DIRECTORY_PODCASTS:  "+"\n");
        stringBuffer.append(path5+"\n\n");
        String path6 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).toString();
        stringBuffer.append("getExternalStoragePublicDirectory--DIRECTORY_RINGTONES:  "+"\n");
        stringBuffer.append(path6+"\n\n");
        String path7 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS).toString();
        stringBuffer.append("getExternalStoragePublicDirectory--DIRECTORY_ALARMS:  "+"\n");
        stringBuffer.append(path7+"\n\n");
        String path8 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS).toString();
        stringBuffer.append("getExternalStoragePublicDirectory--DIRECTORY_NOTIFICATIONS:  "+"\n");
        stringBuffer.append(path8+"\n\n");
        String path9 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        stringBuffer.append("getExternalStoragePublicDirectory--DIRECTORY_PICTURES:  "+"\n");
        stringBuffer.append(path9+"\n\n");
        String path10 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).toString();
        stringBuffer.append("getExternalStoragePublicDirectory--DIRECTORY_MOVIES:  "+"\n");
        stringBuffer.append(path10+"\n\n");
        String path11 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        stringBuffer.append("getExternalStoragePublicDirectory--DIRECTORY_DOWNLOADS:  "+"\n");
        stringBuffer.append(path11+"\n\n");
        String path12 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString();
        stringBuffer.append("getExternalStoragePublicDirectory--DIRECTORY_DCIM:  "+"\n");
        stringBuffer.append(path12+"\n\n");
        String path13 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString();
        stringBuffer.append("getExternalStoragePublicDirectory--DIRECTORY_DOCUMENTS:  "+"\n");
        stringBuffer.append(path13+"\n\n");
        String path14 = PathActivity.this.getExternalFilesDir(null).getPath();
        stringBuffer.append("应用生成文件夹路径:  "+"\n");
        stringBuffer.append(path14+"\n\n");

        String path = Environment.getExternalStorageDirectory()+"/VideoRecorderTest/aaa.mp4";
        File file = new File(path);
        if (file!=null)
        {
            String path15 = file.getAbsolutePath();
            stringBuffer.append("文件或目录的绝对路径:  "+"\n");
            stringBuffer.append(path15+"\n\n");

            String path16 = file.getParent();
            stringBuffer.append("父文件或目录的路径:  "+"\n");
            stringBuffer.append(path16+"\n\n");
        }

    }
}
