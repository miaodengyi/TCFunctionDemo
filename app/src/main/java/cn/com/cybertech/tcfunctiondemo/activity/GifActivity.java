package cn.com.cybertech.tcfunctiondemo.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 显示gif动画
 * Created by miao on 2017/11/1.
 */

public class GifActivity extends Activity
{
    private ImageView iv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        hideTopStatusBar();
        hideBottomUIMenu();
        iv = (ImageView) findViewById(R.id.iv);
        Glide.with(GifActivity.this).load(R.drawable.nfc)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(iv);
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
}
