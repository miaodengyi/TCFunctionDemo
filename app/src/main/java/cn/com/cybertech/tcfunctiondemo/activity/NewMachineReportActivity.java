package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.adapter.FragAdapter;
import cn.com.cybertech.tcfunctiondemo.fragment.ApplicationFragment;
import cn.com.cybertech.tcfunctiondemo.fragment.HardwareFragment;
import cn.com.cybertech.tcfunctiondemo.fragment.SystemInfoFragment;

/**
 * 新的管控主页面
 * Created by miao on 2017/9/5.
 */

public class NewMachineReportActivity extends AppCompatActivity
{
    //viewpage和tabs部分
    private TabLayout tabs;
    private List<String> titles = new ArrayList<>();
    private ViewPager      vp;
    private FragAdapter    adapter;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        initToolbar();
        setTitle("北京交管局终端管控平台");
        setContentView(R.layout.activity_new_machine_report);
        tabs = (TabLayout) findViewById(R.id.tabs);
        titles.add("硬件模块");
        titles.add("系统信息");
        titles.add("应用");
        //构造适配器
        fragments = new ArrayList<>();
        fragments.add(new HardwareFragment());
        fragments.add(new SystemInfoFragment());
        fragments.add(new ApplicationFragment());
        adapter = new FragAdapter(getSupportFragmentManager(), titles, fragments);
        //设定适配器
        vp = (ViewPager) findViewById(R.id.vp_jf_detail);
        vp.setAdapter(adapter);
        //为TabLayout设置ViewPager
        tabs.setupWithViewPager(vp);
        tabs.setTabsFromPagerAdapter(adapter);
    }

}
