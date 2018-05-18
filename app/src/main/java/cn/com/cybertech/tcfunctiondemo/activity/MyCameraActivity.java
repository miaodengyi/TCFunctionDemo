package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.view.MyCamera;

/**
 * Created by 20201002 on 2017/7/18.
 */

public class MyCameraActivity extends AppCompatActivity
{
    private MyCamera myCamera;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("拍照插件");
        setContentView(R.layout.activity_my_camera);
        linearLayout = (LinearLayout) findViewById(R.id.lly_my_camera);
        myCamera = new MyCamera(this);
        linearLayout.addView(myCamera);
        myCamera.setOnCameraClick(this);
//        myCamera = (MyCamera) findViewById(R.id.my_camera);
//        myCamera.setOnCameraClick(this);
    }
}
