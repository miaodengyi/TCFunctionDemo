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

/**
 * 应用信息的适配器
 * Created by miao on 2017/9/6.
 */

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.MyViewHolder>
{

    Context               mContext;
    List<AppInfo> mDates;
    LayoutInflater        mInflater;

    public AppAdapter(Context context, List<AppInfo> mDates)
    {
        mContext = context;
        this.mDates = mDates;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_app, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position)
    {
        if (mDates!=null&&mDates.size()>0)
        {
            holder.iv.setImageDrawable(mDates.get(position).getIcon());
            holder.tvName.setText(mDates.get(position).getPackageName());
            holder.tvVersion.setText("版本号："+mDates.get(position).getVersionCode());
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
        public TextView  tvVersion;
        public View      itemView;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.itemView = itemView;
            iv = (ImageView) itemView.findViewById(R.id.item_iv_icon);
            tvName = (TextView) itemView.findViewById(R.id.item_tv_name);
            tvVersion = (TextView) itemView.findViewById(R.id.item_tv_version);
        }
    }
}
