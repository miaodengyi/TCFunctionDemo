package com.example.record.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.record.R;
import com.example.record.bean.VoiceBean;

import java.util.List;


/**
 * voiceList的适配器
 * Created by miao on 2018/5/17.
 */

public class VoiceAdapter extends RecyclerView.Adapter<VoiceAdapter.MyViewHolder>
{
    private Context mContext;
    private List<VoiceBean> mDates;
    private LayoutInflater mInflater;

    public VoiceAdapter(Context context, List<VoiceBean> mDates)
    {
        mContext = context;
        this.mDates = mDates;
        mInflater = LayoutInflater.from(mContext);
    }

    public interface OnItemClickListener
    {
        public void onClick(View parent, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder myViewHolder =
                new MyViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_voice_list, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        holder.tvName.setText(mDates.get(position).getName());
        holder.tvSize.setText(mDates.get(position).getSize());
        holder.tvTime.setText(mDates.get(position).getTime());
        // 如果设置了回调，则设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (onItemClickListener!=null)
                {
                    onItemClickListener.onClick(v,position);
                }
                holder.tvName.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
            }
        });
    }


    @Override
    public int getItemCount()
    {
        return mDates.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView iv;
        public ImageView ivPlaying;
        public TextView tvName;
        public TextView tvSize;
        public TextView tvTime;
        public View itemView;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.itemView = itemView;
            iv = itemView.findViewById(R.id.item_iv_voice);
            ivPlaying = itemView.findViewById(R.id.item_iv_playing);
            tvName= itemView.findViewById(R.id.item_tv_voice_name);
            tvSize = itemView.findViewById(R.id.item_tv_voice_size);
            tvTime = itemView.findViewById(R.id.item_tv_voice_time);
        }
    }

}
