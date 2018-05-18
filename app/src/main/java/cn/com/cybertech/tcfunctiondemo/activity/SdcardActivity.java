package cn.com.cybertech.tcfunctiondemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Method;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * Created by 20201002 on 2017/5/12.
 */

public class SdcardActivity extends AppCompatActivity
{
    private TextView tvSdcard;

    private String sdcardPath;

    private StringBuffer stringBuffer = new StringBuffer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("sd信息");
        setContentView(R.layout.activity_sdcard);
        tvSdcard = (TextView) findViewById(R.id.tv_sdcard);
        String[] strings = getExtSDCardPath();
        if (strings.length > 1)
        {
            sdcardPath = strings[0];
            getSdcardSize(sdcardPath);
            sdcardPath = strings[1];
            getSdcardSize(sdcardPath);
        }
        tvSdcard.setText(stringBuffer);

    }

    //获取内存的路径  0是本地内存  1是sd卡内存
    private String[] getExtSDCardPath()
    {
        if (android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            StorageManager storageManager = (StorageManager) getSystemService(Context.STORAGE_SERVICE);
            try
            {
                Class<?>[] paramClasses         = {};
                Method     getVolumePathsMethod = StorageManager.class.getMethod("getVolumePaths", paramClasses);
                getVolumePathsMethod.setAccessible(true);
                Object[] params = {};
                Object   invoke = getVolumePathsMethod.invoke(storageManager, params);
                return (String[]) invoke;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            tvSdcard.setText("sdcard不存在");
        }
        return null;
    }

    //path进去后获取sd卡大小
    void getSdcardSize(String path)
    {
        StatFs f = new StatFs(path);

        long blockSize       = f.getBlockSizeLong();
        long totalSize       = f.getBlockCountLong();
        long availableBlocks = f.getAvailableBlocksLong();

        double sd1 = (blockSize * totalSize * 1.0) / 1024 / 1024 / 1024;
        double sd2 = (blockSize * availableBlocks * 1.0) / 1024 / 1024 / 1024;

        String totalSize1 = String.valueOf(sd1);
        String str1       = totalSize1.substring(0, 5);

        String availableBlocks1 = String.valueOf(sd2);
        String str2             = availableBlocks1.substring(0, 5);

        stringBuffer.append("Available:" + str2 + "GB" + "\n\n" + "Total space:" + str1 + "GB"+"\n\n");
//        tvSdcard.setText("Available:" + str2 + "GB" + "\n\n" + "Total space:" + str1 + "GB");
//        tvSdcard.setText("Available:" + totalSize1 + "GB" + "\n\n" + "Total space:" + availableBlocks1 + "GB");
    }

}
