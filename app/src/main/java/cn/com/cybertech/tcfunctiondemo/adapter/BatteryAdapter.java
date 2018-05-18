package cn.com.cybertech.tcfunctiondemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.bean.AppInfo;
import cn.com.cybertech.tcfunctiondemo.bean.BatteryBean;

/**
 * 耗电量的适配器
 * Created by miao on 2017/9/6.
 */

public class BatteryAdapter extends RecyclerView.Adapter<BatteryAdapter.MyViewHolder>
{

    Context           mContext;
    List<BatteryBean> mDates;
    LayoutInflater    mInflater;

    public BatteryAdapter(Context context, List<BatteryBean> mDates)
    {
        mContext = context;
        this.mDates = mDates;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_battery, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position)
    {
        if (mDates!=null&&mDates.size()>0)
        {
            holder.iv.setImageDrawable(mDates.get(position).getIcon());
            holder.tvName.setText(mDates.get(position).getName());
            holder.tvBattery.setText(""+mDates.get(position).getBattery());
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
        public TextView  tvName;
        public TextView  tvBattery;
        public View      itemView;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.itemView = itemView;
            iv = (ImageView) itemView.findViewById(R.id.item_iv_icon);
            tvName = (TextView) itemView.findViewById(R.id.item_tv_name);
            tvBattery = (TextView) itemView.findViewById(R.id.item_tv_battery);
        }
    }
}
