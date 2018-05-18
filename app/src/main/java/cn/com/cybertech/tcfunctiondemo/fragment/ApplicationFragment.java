package cn.com.cybertech.tcfunctiondemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.adapter.ApplicationAdapter;
import cn.com.cybertech.tcfunctiondemo.bean.ApplicationBean;

/**
 * 应用
 * Created by miao on 2017/9/5.
 */

public class ApplicationFragment extends Fragment
{
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_application,container,false);
        //所有的操作都在这其间完成
        ArrayList<ApplicationBean> list = getData();
        recyclerView =(RecyclerView) view.findViewById(R.id.rv_application);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ApplicationAdapter applicationAdapter = new ApplicationAdapter(getActivity(),list);
        recyclerView.setAdapter(applicationAdapter);
        return view;
    }

    private ArrayList<ApplicationBean> getData()
    {
        ArrayList<ApplicationBean> list  = new ArrayList<>();
        ApplicationBean bean1 = new ApplicationBean();
        bean1.setIcon(R.mipmap.ic_launcher);
        bean1.setName("警灯工程");
        bean1.setVersion("V3.0.1");
        list.add(bean1);
        ApplicationBean bean2 = new ApplicationBean();
        bean2.setIcon(R.mipmap.ic_launcher);
        bean2.setName("车灯工程");
        bean2.setVersion("V2.1.1");
        list.add(bean2);
        ApplicationBean bean3 = new ApplicationBean();
        bean3.setIcon(R.mipmap.ic_launcher);
        bean3.setName("路灯工程");
        bean3.setVersion("V4.1.1");
        list.add(bean3);
        ApplicationBean bean4 = new ApplicationBean();
        bean4.setIcon(R.mipmap.ic_launcher);
        bean4.setName("电灯工程");
        bean4.setVersion("V5.1.1");
        list.add(bean4);
        return list;
    }
}
