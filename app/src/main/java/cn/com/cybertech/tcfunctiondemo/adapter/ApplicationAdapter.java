package cn.com.cybertech.tcfunctiondemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.bean.ApplicationBean;
import cn.com.cybertech.tcfunctiondemo.bean.HardwareBean;

/**
 * 应用信息的适配器
 * Created by miao on 2017/9/6.
 */

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.MyViewHolder>
{

    Context               mContext;
    List<ApplicationBean> mDates;
    LayoutInflater        mInflater;

    public ApplicationAdapter(Context context, List<ApplicationBean> mDates)
    {
        mContext = context;
        this.mDates = mDates;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_application, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position)
    {
        if (mDates!=null&&mDates.size()>0)
        {
            holder.iv.setImageResource(mDates.get(position).getIcon());
            holder.tvName.setText(mDates.get(position).getName());
            holder.tvVersion.setText(mDates.get(position).getVersion());
            holder.ibtnOpen.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(mContext,"点击了："+position+"行的打开",Toast.LENGTH_SHORT).show();
                }
            });
            holder.ibtnUninstall.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(mContext,"点击了："+position+"行的卸载",Toast.LENGTH_SHORT).show();
                }
            });
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
        public ImageButton ibtnOpen;
        public ImageButton ibtnUninstall;
        public View      itemView;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.itemView = itemView;
            iv = (ImageView) itemView.findViewById(R.id.item_iv_icon);
            tvName = (TextView) itemView.findViewById(R.id.item_tv_name);
            tvVersion = (TextView) itemView.findViewById(R.id.item_tv_version);
            ibtnOpen = (ImageButton) itemView.findViewById(R.id.item_ibtn_open);
            ibtnUninstall = (ImageButton) itemView.findViewById(R.id.item_ibtn_uninstall);
        }
    }
}
