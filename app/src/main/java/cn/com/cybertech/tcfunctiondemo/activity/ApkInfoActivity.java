package cn.com.cybertech.tcfunctiondemo.activity;

import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * Created by miao on 2017/5/9.
 */

public class ApkInfoActivity extends AppCompatActivity
{
    private Button btnSearch;
    private EditText etApkName;
    private TextView tvApkInfo;

    private PackageManager pm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("获取apk的信息");
        setContentView(R.layout.activity_apk_info);
        pm = getPackageManager();
        etApkName = (EditText) findViewById(R.id.et_apk_name);
        tvApkInfo = (TextView) findViewById(R.id.tv_apk_info);
        btnSearch = (Button) findViewById(R.id.btn_apk_search);
        btnSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String apkName = etApkName.getText().toString();
                if (!TextUtils.isEmpty(apkName))
                {
                    File file = new File(Environment.getExternalStorageDirectory()+"/"+apkName+".apk");
                    if (file.exists())
                    {
                        String apkPath = file.getAbsolutePath();
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("package name is : " + getPackageName(apkPath)+"\n");
                        stringBuffer.append("version name is : " + getVersionName(apkPath)+"\n");
                        stringBuffer.append("version code is : " + getVersionCode(apkPath)+"\n");
                        tvApkInfo.setText(stringBuffer);
                    }
                    else
                    {
                        Toast.makeText(ApkInfoActivity.this,"文件不存在！",Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(ApkInfoActivity.this,"应用名称不能为空！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 获取APK的包名
     *
     * @param apkPath
     * @return
     */
    private String getPackageName(String apkPath) {
        PackageInfo pi = pm.getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES);
        String packageName = null;
        if (pi != null) {
            packageName = pi.packageName;
        }
        return packageName;
    }

    /**
     * 获取APK版本名称(versionName)
     *
     * @param apkPath
     * @return
     */
    private String getVersionName(String apkPath) {
        PackageInfo pi = pm.getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES);
        String versionName = null;
        if (pi != null) {
            versionName = pi.versionName;
        }
        return versionName;
    }

    /**
     * 获取APK版本号(versionCode)
     *
     * @param apkPath
     * @return
     */
    private int getVersionCode(String apkPath) {
        PackageInfo pi = pm.getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES);
        int versionCode = 1;
        if (pi != null) {
            versionCode = pi.versionCode;
        }
        return versionCode;
    }
    /**
     * 获取APK的所有activity的name
     *
     * @param apkPath
     * @return
     */
    private ArrayList<String> getActivitiesName(String apkPath) {
        PackageInfo pi = pm.getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES);
        ArrayList<String> list = null;
        if (pi != null) {
            list = new ArrayList<String>();
            ActivityInfo[] ais = pi.activities;
            if (ais != null) {
                for (ActivityInfo ai : ais) {
                    String name = ai.name;
                    if (name != null && !"".equals(name)) {
                        list.add(name);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 获取应用程序图片Drawable
     *
     * @param apkPath
     * @return
     */
    private Drawable getApkIcon(String apkPath) {
        PackageInfo pi = pm.getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES);
        if (pi != null) {
            ApplicationInfo appInfo = pi.applicationInfo;
            appInfo.sourceDir = apkPath;
            appInfo.publicSourceDir = apkPath;
            try {
                return appInfo.loadIcon(pm);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
