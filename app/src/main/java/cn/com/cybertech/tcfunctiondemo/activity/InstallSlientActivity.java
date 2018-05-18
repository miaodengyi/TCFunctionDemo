package cn.com.cybertech.tcfunctiondemo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 常规的静默安装
 * Created by miao on 2017/8/8.
 */

public class InstallSlientActivity extends AppCompatActivity
{

    private Button btnInstall,btnUninstall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("常规静默安装");
        setContentView(R.layout.activity_install_slient);
        btnInstall = (Button) findViewById(R.id.btn_install);
        btnInstall.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                install(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"wenzhou"+File.separator+"ping.apk")));
            }
        });
        btnUninstall = (Button) findViewById(R.id.btn_uninstall);
        btnUninstall.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                unInstall("cn.com.cybertech.zhejiang_law_boos");
            }
        });
    }

    //安装应用
    void install(Uri uri)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //加这句话，不然可能会报错
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(intent);
    }

    //卸载应用
    void unInstall(String pkgName)
    {
        String name = "package:"+pkgName;
        Uri packageURI = Uri.parse(name);
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        startActivity(uninstallIntent);
    }
}
