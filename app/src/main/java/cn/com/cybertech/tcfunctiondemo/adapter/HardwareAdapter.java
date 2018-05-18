package cn.com.cybertech.tcfunctiondemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.bean.HardwareBean;

/**
 * 硬件模块的适配器
 * Created by miao on 2017/9/6.
 */

public class HardwareAdapter extends RecyclerView.Adapter<HardwareAdapter.MyViewHolder>
{

    Context            mContext;
    List<HardwareBean> mDates;
    LayoutInflater     mInflater;

    public HardwareAdapter(Context context, List<HardwareBean> mDates)
    {
        mContext = context;
        this.mDates = mDates;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_hardware, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        if (mDates!=null&&mDates.size()>0)
        {
            holder.iv.setImageResource(mDates.get(position).getImage());
            holder.tv.setText(mDates.get(position).getModel());
            holder.cb.setChecked(mDates.get(position).isStatus());
        }
    }

    @Override
    public int getItemCount()
    {
        return mDates.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView iv;
        public TextView  tv;
        public CheckBox  cb;
        public View      itemView;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.itemView = itemView;
            iv = (ImageView) itemView.findViewById(R.id.item_iv_hardware);
            tv = (TextView) itemView.findViewById(R.id.item_tv_hardware);
            cb = (CheckBox) itemView.findViewById(R.id.item_cb_hardware);
        }
    }
}
