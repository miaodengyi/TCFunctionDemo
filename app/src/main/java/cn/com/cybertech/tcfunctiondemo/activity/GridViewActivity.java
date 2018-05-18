package cn.com.cybertech.tcfunctiondemo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.adapter.HouseGridViewAdapter;
import cn.com.cybertech.tcfunctiondemo.bean.HouseBean;
import cn.com.cybertech.tcfunctiondemo.util.TestData;

/**
 * 实现gridview的功能给房总房屋用
 * Created by miao on 2017/7/20.
 */

public class GridViewActivity extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("Gridview功能");
        setContentView(R.layout.activity_gridview);
        GridView gridView = (GridView) findViewById(R.id.gridview);

        final ArrayList<HouseBean> list;
        list = TestData.getInstance().getHouseData();
        HouseGridViewAdapter  adapter = new HouseGridViewAdapter(this, list);
        //设置水平横向滑动的参数
        //        // item宽度
        int itemWidth = dip2px(this, 100);
        //        // item之间的间隔
        int itemPaddingH = dip2px(this, 1);
        //默认10张
        int size = 9;
        //        // 计算GridView宽度
        int gridviewWidth = size * (itemWidth + itemPaddingH);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        gridView.setLayoutParams(params);
        gridView.setColumnWidth(itemWidth);
        gridView.setHorizontalSpacing(itemPaddingH);
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(GridViewActivity.this, "点击了：" + list.get(position).getNum(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    public static int dip2px(Context context, float dpValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
