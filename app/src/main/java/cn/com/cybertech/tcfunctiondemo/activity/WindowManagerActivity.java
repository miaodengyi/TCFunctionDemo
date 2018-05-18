package cn.com.cybertech.tcfunctiondemo.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.service.WindowService;

/**
 * windowmanager的悬浮框
 * Created by miao on 2017/8/11.
 */

public class WindowManagerActivity extends AppCompatActivity
{

    private Button btnShowWindow;//显示悬浮窗
    private Button btnDismissWindow;//隐藏悬浮窗
    private Button btnHideBar;//隐藏虚拟菜单和statusbar
    private Button btnFullScreen;//全屏显示

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("WindowManager");
        setContentView(R.layout.activity_window_manager);
        requestDrawOverLays();
        //显示悬浮框
        btnShowWindow = (Button) findViewById(R.id.btn_show_window);
        btnShowWindow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(WindowManagerActivity.this, WindowService.class);
                startService(intent);
            }
        });
        //隐藏悬浮框
        btnDismissWindow = (Button) findViewById(R.id.btn_dismiss_window);
        btnDismissWindow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(WindowManagerActivity.this, WindowService.class);
                stopService(intent);
            }
        });
        //隐藏虚拟菜单栏和顶层的状态栏
        btnHideBar = (Button) findViewById(R.id.btn_hide_bar);
        btnHideBar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hideNavigationBar();
            }
        });
        //全屏显示
        btnFullScreen = (Button) findViewById(R.id.btn_full_screen);
        btnFullScreen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hideTopStatusBar();
                hideBottomUIMenu();
            }
        });
    }

    /**
     * 隐藏顶层的状态栏
     */
    private void hideTopStatusBar()
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 隐藏虚拟菜单栏
     */
    private void hideBottomUIMenu()
    {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19)
        { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        }
        else if (Build.VERSION.SDK_INT >= 19)
        {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int  uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY /*| View.SYSTEM_UI_FLAG_FULLSCREEN*/;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    /**
     * 隐藏虚拟菜单栏和顶层的状态栏
     * 用view遮盖，使菜单栏和状态栏无效
     */
    private void hideNavigationBar()
    {
        int flags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION//隐藏Navigation Bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;//防止Navigation Bar在覆盖view的情况下上弹
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View           view     = inflater.inflate(R.layout.my_view, null);
        view.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                Log.e("rocky", "Hi....");
                return false;
            }
        });
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        int           width         = windowManager.getDefaultDisplay().getWidth();
        int           height        = windowManager.getDefaultDisplay().getHeight();
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(width, height, WindowManager.LayoutParams.TYPE_SYSTEM_ERROR, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, //让window占满整个手机屏幕，不留任何边界（border）
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.y = 0;
        params.x = 0;
        windowManager.addView(view, params);
        view.setSystemUiVisibility(flags);
    }

    /**
     * 动态申请悬浮框权限
     */
    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;

    @TargetApi(Build.VERSION_CODES.M)
    public void requestDrawOverLays()
    {
        if (!Settings.canDrawOverlays(WindowManagerActivity.this))
        {
            Toast.makeText(this, "can not DrawOverlays", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + WindowManagerActivity.this.getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
        }
        else
        {
            // Already hold the SYSTEM_ALERT_WINDOW permission, do addview or something.
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE)
        {
            if (!Settings.canDrawOverlays(this))
            {
                // SYSTEM_ALERT_WINDOW permission not granted...
                Toast.makeText(this, "Permission Denieddd by user.Please Check it in Settings", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Permission Allowed", Toast.LENGTH_SHORT).show();
                // Already hold the SYSTEM_ALERT_WINDOW permission, do addview or something.
            }
        }
    }


}
