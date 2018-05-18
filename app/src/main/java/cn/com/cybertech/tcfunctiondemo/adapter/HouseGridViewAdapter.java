package cn.com.cybertech.tcfunctiondemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.bean.HouseBean;

/**
 * 居民楼视图的适配器
 * Created by miao on 2017/7/20.
 */

public class HouseGridViewAdapter extends BaseAdapter
{
    private Context              context;
    private ArrayList<HouseBean> list;

    public HouseGridViewAdapter(Context context, ArrayList<HouseBean> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        MyViewHolder holder;
        if (convertView == null)
        {
            holder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_house, null);
            holder.tvNum = (TextView) convertView.findViewById(R.id.item_tv_room_id);
            holder.iv1 = (ImageView) convertView.findViewById(R.id.item_iv_icon_1);
            holder.iv2 = (ImageView) convertView.findViewById(R.id.item_iv_icon_2);
            holder.iv3 = (ImageView) convertView.findViewById(R.id.item_iv_icon_3);
            holder.iv4 = (ImageView) convertView.findViewById(R.id.item_iv_icon_4);
            holder.iv5 = (ImageView) convertView.findViewById(R.id.item_iv_icon_5);
            holder.iv6 = (ImageView) convertView.findViewById(R.id.item_iv_icon_6);
            convertView.setTag(holder);
        }
        else
        {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.tvNum.setText(String.valueOf(list.get(position).getNum()));
        ArrayList<String> tags = list.get(position).getList().get(0).getTags();
        if (tags != null)
        {
            if (tags.size() > 0)
            {
                holder.iv1.setVisibility(View.VISIBLE);
                holder.iv2.setVisibility(View.VISIBLE);
                holder.iv3.setVisibility(View.VISIBLE);
                holder.iv4.setVisibility(View.VISIBLE);
                holder.iv5.setVisibility(View.VISIBLE);
                holder.iv6.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            holder.iv1.setVisibility(View.INVISIBLE);
            holder.iv2.setVisibility(View.INVISIBLE);
            holder.iv3.setVisibility(View.INVISIBLE);
            holder.iv4.setVisibility(View.INVISIBLE);
            holder.iv5.setVisibility(View.INVISIBLE);
            holder.iv6.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    private class MyViewHolder
    {
        private TextView  tvNum;
        private ImageView iv1;
        private ImageView iv2;
        private ImageView iv3;
        private ImageView iv4;
        private ImageView iv5;
        private ImageView iv6;
    }

}
