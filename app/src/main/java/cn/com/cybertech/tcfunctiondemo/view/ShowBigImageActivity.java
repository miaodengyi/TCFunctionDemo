package cn.com.cybertech.tcfunctiondemo.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.ImageView;

import java.io.File;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 *
 * Created by miao on 2017/3/22.
 */

public class ShowBigImageActivity extends Activity
{
    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show_big_image);
        imageView = (ImageView) findViewById(R.id.iv_big_image);
        Intent intent = getIntent();
        String path   = intent.getStringExtra("path");
        File   f      = new File(path);
        if (!f.exists())
        {
            finish();
        }
        if (path.equals("null"))
        {
            finish();
        }
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        bitmapOptions.inSampleSize = 3;
        bitmap = BitmapFactory.decodeFile(path,bitmapOptions);
        imageView.setImageBitmap(bitmap);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (bitmap!=null && !bitmap.isRecycled())
        {
            bitmap.recycle();
        }
        finish();
        return super.onTouchEvent(event);
    }
}
