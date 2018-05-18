package cn.com.cybertech.tcfunctiondemo.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import cn.com.cybertech.tcfunctiondemo.R;

import static cn.com.cybertech.tcfunctiondemo.util.BackgroundLayout.dpToPixel;


public class LoadingProgressHelper
{
    private ProgressDialog mProgressDialog;
    private float          mDimAmount;
    private int            mWindowColor;
    private float          mCornerRadius;
    private Context        mContext;
    private int            mAnimateSpeed;

    public LoadingProgressHelper(Context context)
    {
        mContext = context;
        mProgressDialog = new ProgressDialog(context);
        mDimAmount = 0;
        mWindowColor = Color.parseColor("#b1000000");
        mAnimateSpeed = 1;
        mCornerRadius = 10;

        View view = new SpinView(mContext);
        mProgressDialog.setView(view);
    }

    public static LoadingProgressHelper create(Context context)
    {
        return new LoadingProgressHelper(context);
    }

    public LoadingProgressHelper setDimAmount(float dimAmount)
    {
        if (dimAmount >= 0 && dimAmount <= 1)
        {
            mDimAmount = dimAmount;
        }
        return this;
    }

    public LoadingProgressHelper setSize(int width, int height)
    {
        mProgressDialog.setSize(width, height);
        return this;
    }

    public LoadingProgressHelper setWindowColor(int color)
    {
        mWindowColor = color;
        return this;
    }

    public LoadingProgressHelper setCornerRadius(float radius)
    {
        mCornerRadius = radius;
        return this;
    }

    public LoadingProgressHelper setAnimationSpeed(int scale)
    {
        mAnimateSpeed = scale;
        return this;
    }

    public LoadingProgressHelper setLabel(String label)
    {
        mProgressDialog.setLabel(label);
        return this;
    }

    public LoadingProgressHelper setDetailsLabel(String detailsLabel)
    {
        mProgressDialog.setDetailsLabel(detailsLabel);
        return this;
    }

    public LoadingProgressHelper setCustomView(View view)
    {
        if (view != null)
        {
            mProgressDialog.setView(view);
        }
        else
        {
            throw new RuntimeException("Custom view must not be null!");
        }
        return this;
    }

    public LoadingProgressHelper setCancellable(boolean isCancellable)
    {
        mProgressDialog.setCancelable(isCancellable);
        return this;
    }

    public LoadingProgressHelper show()
    {
        if (!isShowing())
        {
            mProgressDialog.show();
        }
        return this;
    }

    public boolean isShowing()
    {
        return mProgressDialog != null && mProgressDialog.isShowing();
    }

    public ProgressDialog getProgressDialog()
    {
        return mProgressDialog;
    }

    public void dismiss()
    {
        if (mProgressDialog != null && mProgressDialog.isShowing())
        {
            mProgressDialog.dismiss();
        }
    }

    public interface Indeterminate
    {
        void setAnimationSpeed(float scale);
    }

    private class SpinView extends android.support.v7.widget.AppCompatImageView implements Indeterminate
    {
        private float    mRotateDegrees;
        private int      mFrameTime;
        private boolean  mNeedToUpdateView;
        private Runnable mUpdateViewRunnable;

        public SpinView(Context context)
        {
            super(context);
            init();
        }

        public SpinView(Context context, AttributeSet attrs)
        {
            super(context, attrs);
            init();
        }

        private void init()
        {
            setImageResource(R.drawable.loading);
            mFrameTime = 1000 / 12;
            mUpdateViewRunnable = new Runnable()
            {
                @Override
                public void run()
                {
                    mRotateDegrees += 30;
                    mRotateDegrees = mRotateDegrees < 360 ? mRotateDegrees : mRotateDegrees - 360;
                    invalidate();
                    if (mNeedToUpdateView)
                    {
                        postDelayed(this, mFrameTime);
                    }
                }
            };
        }

        @Override
        public void setAnimationSpeed(float scale)
        {
            mFrameTime = (int) (1000 / 12 / scale);
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            canvas.rotate(mRotateDegrees, getWidth() / 2, getHeight() / 2);
            super.onDraw(canvas);
        }

        @Override
        protected void onAttachedToWindow()
        {
            super.onAttachedToWindow();
            mNeedToUpdateView = true;
            post(mUpdateViewRunnable);
        }

        @Override
        protected void onDetachedFromWindow()
        {
            mNeedToUpdateView = false;
            super.onDetachedFromWindow();
        }
    }

    private class ProgressDialog extends Dialog
    {
        private Indeterminate    mIndeterminateView;
        private View             mView;
        private TextView         mLabelText;
        private TextView         mDetailsText;
        private String           mLabel;
        private String           mDetailsLabel;
        private FrameLayout      mCustomViewContainer;
        private BackgroundLayout mBackgroundLayout;
        private int              mWidth, mHeight;

        public ProgressDialog(Context context)
        {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog_loading);

            Window window = getWindow();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.dimAmount = mDimAmount;
            layoutParams.gravity = Gravity.CENTER;
            window.setAttributes(layoutParams);

            setCanceledOnTouchOutside(false);

            initViews();
        }

        private void initViews()
        {
            mBackgroundLayout = (BackgroundLayout) findViewById(R.id.background);
            mBackgroundLayout.setBaseColor(mWindowColor);
            mBackgroundLayout.setCornerRadius(mCornerRadius);
            if (mWidth != 0)
            {
                updateBackgroundSize();
            }

            mCustomViewContainer = (FrameLayout) findViewById(R.id.container);
            addViewToFrame(mView);

            if (mIndeterminateView != null)
            {
                mIndeterminateView.setAnimationSpeed(mAnimateSpeed);
            }

            mLabelText = (TextView) findViewById(R.id.label);
            if (mLabel != null)
            {
                mLabelText.setText(mLabel);
                mLabelText.setVisibility(View.VISIBLE);
            }
            else
            {
                mLabelText.setVisibility(View.GONE);
            }
            mDetailsText = (TextView) findViewById(R.id.details_label);
            if (mDetailsLabel != null)
            {
                mDetailsText.setText(mDetailsLabel);
                mDetailsText.setVisibility(View.VISIBLE);
            }
            else
            {
                mDetailsText.setVisibility(View.GONE);
            }
        }

        private void addViewToFrame(View view)
        {
            if (view == null) return;
            int                    wrapParam = ViewGroup.LayoutParams.WRAP_CONTENT;
            ViewGroup.LayoutParams params    = new ViewGroup.LayoutParams(wrapParam, wrapParam);
            mCustomViewContainer.addView(view, params);
        }

        private void updateBackgroundSize()
        {
            ViewGroup.LayoutParams params = mBackgroundLayout.getLayoutParams();
            params.width = dpToPixel(mWidth, getContext());
            params.height = dpToPixel(mHeight, getContext());
            mBackgroundLayout.setLayoutParams(params);
        }

        public void setView(View view)
        {
            if (view != null)
            {
                if (view instanceof Indeterminate)
                {
                    mIndeterminateView = (Indeterminate) view;
                }
                mView = view;
                if (isShowing())
                {
                    mCustomViewContainer.removeAllViews();
                    addViewToFrame(view);
                }
            }
        }

        public void setLabel(String label)
        {
            mLabel = label;
            if (mLabelText != null)
            {
                if (label != null)
                {
                    mLabelText.setText(label);
                    mLabelText.setVisibility(View.VISIBLE);
                }
                else
                {
                    mLabelText.setVisibility(View.GONE);
                }
            }
        }

        public void setDetailsLabel(String detailsLabel)
        {
            mDetailsLabel = detailsLabel;
            if (mDetailsText != null)
            {
                if (detailsLabel != null)
                {
                    mDetailsText.setText(detailsLabel);
                    mDetailsText.setVisibility(View.VISIBLE);
                }
                else
                {
                    mDetailsText.setVisibility(View.GONE);
                }
            }
        }

        public void setSize(int width, int height)
        {
            mWidth = width;
            mHeight = height;
            if (mBackgroundLayout != null)
            {
                updateBackgroundSize();
            }
        }
    }
}
