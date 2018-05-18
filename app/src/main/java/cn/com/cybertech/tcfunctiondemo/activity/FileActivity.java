package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.bean.AppInfo;
import cn.com.cybertech.tcfunctiondemo.bean.FileInfo;

/**
 * Created by miao on 2017/5/5.
 */

public class FileActivity extends AppCompatActivity
{
    private TextView tvFile;
    private StringBuffer stringBuffer = new StringBuffer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("文件列表");
        setContentView(R.layout.activity_file);
        tvFile = (TextView) findViewById(R.id.tv_file);
        List<FileInfo> list = listFile(Environment.getExternalStorageDirectory().toString());
        for (FileInfo info : list)
        {
            if (info.isDirectory())
            {
                stringBuffer.append("\n" + "name: " + info.getName() + "\n" + "length: " + String.valueOf(info.getLength()) + "\n" + "lastModified: " + transformLongToTime(info.getLastModified()) + "\n" + "类型： 文件夹"+ "\n");
            }
            else
            {
                stringBuffer.append("\n" + "name: " + info.getName() + "\n" + "length: " + String.valueOf(info.getLength()) + "\n" + "lastModified: " + transformLongToTime(info.getLastModified()) + "\n" + "类型： 文件"+ "\n");
            }
        }
        if (stringBuffer != null)
        {
            tvFile.setText(stringBuffer);
        }
    }

    public List<FileInfo> listFile(String path)
    {
        if (TextUtils.isEmpty(path))
        {
            return null;
        }
        List<FileInfo> fileInfos = new ArrayList<>();
        try
        {
            File file = new File(path);
            if (!file.exists()) return null;
            File[] files = file.listFiles();
            if (files != null && files.length > 0)
            {
                try
                {
                    for (File f : files)
                    {
                        if (!f.exists()) continue;
                        FileInfo fileInfo = new FileInfo();
                        String   name     = f.getName();
                        fileInfo.setName(name);

                        if (f.isDirectory())
                        {
                            fileInfo.setDirectory(true);
                        }
                        else
                        {
                            fileInfo.setDirectory(false);
                            fileInfo.setLength(f.length());
                        }

                        fileInfo.setLastModified(String.valueOf(file.lastModified()));
                        fileInfos.add(fileInfo);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return fileInfos;
    }

    String transformLongToTime(String longTime)
    {
        long             time = Long.parseLong(longTime);
        Date             date = new Date(time);
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}
