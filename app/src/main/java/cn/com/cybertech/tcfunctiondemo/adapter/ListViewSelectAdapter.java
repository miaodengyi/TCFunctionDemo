package cn.com.cybertech.tcfunctiondemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.inter.SelectAllChangeListener;

/**
 * listview嵌套checkbox的adapter
 * Created by miao on 2017/7/21.
 */

public class ListViewSelectAdapter extends BaseAdapter
{
    private Context           context;
    private ArrayList<String> list;

    private HashMap<Integer, Boolean> map = new HashMap<>();

    //全选状态的标记
    private boolean isSelectAll = false;

    //当全选状态下，有item被点击了，告诉activity，全选的checkbox不显示被全选
    private SelectAllChangeListener listener;

    public ListViewSelectAdapter(Context context, ArrayList<String> list)
    {
        this.context = context;
        this.list = list;
        for (int i = 0; i < list.size(); i++)
        {
            map.put(i, false);
        }
    }

    /**
     * 点击确认有效时候返回给activitymap里面的值
     *
     * @return
     */
    public HashMap<Integer, Boolean> getCheckBoxStatus()
    {
        return map;
    }

    //全选
    public void setAllSelect()
    {
        isSelectAll = true;
        for (int i = 0; i < list.size(); i++)
        {
            map.put(i, true);
        }
        notifyDataSetChanged();
    }

    //取消全选
    public void setUnAllSelect()
    {

        isSelectAll = false;
        for (int i = 0; i < list.size(); i++)
        {
            map.put(i, false);
        }
        notifyDataSetChanged();
    }

    //提醒全选状态下，有item改变了状态
    public void selectAllItemChanged(SelectAllChangeListener listener)
    {
        this.listener = listener;
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
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final MyViewHolder holder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_select, null);
            holder = new MyViewHolder();
            holder.cb = (CheckBox) convertView.findViewById(R.id.item_cb);
            holder.tv = (TextView) convertView.findViewById(R.id.item_tv);
            convertView.setTag(holder);
        }
        else
        {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.tv.setText(list.get(position));
        holder.cb.setChecked(map.get(position));
        holder.cb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //解决滑动checkbox状态混乱问题
                if (map != null && map.containsKey(position))
                {
                    if (map.get(position))
                    {
                        holder.cb.setChecked(false);
                        map.put(position, false);
                    }
                    else
                    {
                        holder.cb.setChecked(true);
                        map.put(position, true);
                    }
                }
                //当在全选状态时，告诉activity，item的checkbox改变了
                if (isSelectAll)
                {
                    listener.statusChange();
                    isSelectAll = false;
                }
            }
        });

        return convertView;
    }

    private class MyViewHolder
    {
        private CheckBox cb;
        private TextView tv;
    }
}
