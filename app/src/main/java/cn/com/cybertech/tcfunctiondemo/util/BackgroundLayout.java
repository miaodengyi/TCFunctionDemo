package cn.com.cybertech.tcfunctiondemo.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class BackgroundLayout extends LinearLayout
{
    private float mCornerRadius;
    private int   mBackgroundColor;

    public BackgroundLayout(Context context)
    {
        super(context);
        init();
    }

    public BackgroundLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public BackgroundLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        initBackground(Color.parseColor("#b1000000"), mCornerRadius);
    }

    private void initBackground(int color, float cornerRadius)
    {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(color);
        drawable.setCornerRadius(cornerRadius);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        {
            setBackground(drawable);
        }
        else
        {
            //noinspection deprecation
            setBackgroundDrawable(drawable);
        }
    }

    public void setCornerRadius(float radius)
    {
        mCornerRadius = dpToPixel(radius, getContext());
        initBackground(mBackgroundColor, mCornerRadius);
    }

    private static float scale;

    public static int dpToPixel(float dp, Context context)
    {
        if (scale == 0)
        {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (dp * scale);
    }

    public void setBaseColor(int color)
    {
        mBackgroundColor = color;
        initBackground(mBackgroundColor, mCornerRadius);
    }
}
