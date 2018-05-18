package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.adapter.ListViewSelectAdapter;
import cn.com.cybertech.tcfunctiondemo.inter.SelectAllChangeListener;
import cn.com.cybertech.tcfunctiondemo.util.TestData;

/**
 * listview全选实现
 * Created by miao on 2017/7/21.
 */

public class ListViewSelectActivity extends AppCompatActivity
{
    public CheckBox cbSelectAll;
    private ListView lv;
    private Button   btnSure, btnCancel;

    private StringBuffer stringBuffer;
    private HashMap<Integer, Boolean> map = new HashMap<>();

    //全选状态的标记
    private boolean isSelectAll = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("LISTVIEW全选");
        setContentView(R.layout.activity_listview_select);
        cbSelectAll = (CheckBox) findViewById(R.id.cb_select_all);
        lv = (ListView) findViewById(R.id.lv_select);
        btnSure = (Button) findViewById(R.id.btn_sure);
        btnCancel = (Button) findViewById(R.id.btn_cancel);

        /**
         * 初始化数据
         * map的所有value都是false
         */
        ArrayList<String>           list    = TestData.getInstance().getListViewSelectData();
        final ListViewSelectAdapter adapter = new ListViewSelectAdapter(this, list);
        lv.setAdapter(adapter);
        //全选的checkbox
        cbSelectAll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*
                 * isSelectAll == true;>>>>现在是被全选状态，点击取消全选
                 * isSelectAll = fallse;>>>>点击全选
                 */
                if (isSelectAll)
                {
                    cbSelectAll.setChecked(false);
                    isSelectAll = false;
                    adapter.setUnAllSelect();
                }
                else
                {
                    cbSelectAll.setChecked(true);
                    isSelectAll = true;
                    adapter.setAllSelect();
                }
            }
        });
        adapter.selectAllItemChanged(new SelectAllChangeListener()
        {
            @Override
            public void statusChange()
            {
                cbSelectAll.setChecked(false);
                isSelectAll = false;
                System.out.println();
            }
        });
        btnSure.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                stringBuffer = null;
                stringBuffer = new StringBuffer();
                map = adapter.getCheckBoxStatus();
                for (Iterator iter = map.entrySet().iterator(); iter.hasNext(); )
                {
                    Map.Entry entry = (Map.Entry) iter.next();
                    Integer key = (Integer) entry.getKey();
                    Boolean val = (Boolean) entry.getValue();
                    stringBuffer.append("key>>>"+String.valueOf(key)+"-----value>>>"+String.valueOf(val)+"\n");
                }
                System.out.println(stringBuffer);
                Toast.makeText(ListViewSelectActivity.this,stringBuffer,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
