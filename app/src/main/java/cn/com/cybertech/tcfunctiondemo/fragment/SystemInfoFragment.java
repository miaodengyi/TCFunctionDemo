package cn.com.cybertech.tcfunctiondemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 系统信息
 * Created by miao on 2017/9/5.
 */

public class SystemInfoFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_system_info,container,false);
        //所有的操作都在这其间完成
        return view;
    }
}
