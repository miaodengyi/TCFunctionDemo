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
import cn.com.cybertech.tcfunctiondemo.adapter.HardwareAdapter;
import cn.com.cybertech.tcfunctiondemo.bean.HardwareBean;

/**
 * 硬件模块
 * Created by miao on 2017/9/5.
 */

public class HardwareFragment extends Fragment
{
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_hardware_model,container,false);
        //所有的操作都在这其间完成
        ArrayList<HardwareBean> list = getData();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_hardware);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HardwareAdapter adapter = new HardwareAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private ArrayList<HardwareBean> getData()
    {
        ArrayList<HardwareBean> list = new ArrayList<>();
        HardwareBean bean1 = new HardwareBean();
        bean1.setImage(R.drawable.ic_bluetooth);
        bean1.setModel("蓝牙");
        bean1.setStatus(true);
        list.add(bean1);
        HardwareBean bean2 = new HardwareBean();
        bean2.setImage(R.drawable.ic_wifi);
        bean2.setModel("WIFI");
        bean2.setStatus(false);
        list.add(bean2);
        HardwareBean bean3 = new HardwareBean();
        bean3.setImage(R.drawable.ic_gprs);
        bean3.setModel("数据流量");
        bean3.setStatus(false);
        list.add(bean3);
        HardwareBean bean4 = new HardwareBean();
        bean4.setImage(R.drawable.ic_gps);
        bean4.setModel("GPS");
        bean4.setStatus(true);
        list.add(bean4);
        HardwareBean bean5 = new HardwareBean();
        bean5.setImage(R.drawable.ic_usb);
        bean5.setModel("USB");
        bean5.setStatus(false);
        list.add(bean5);
        HardwareBean bean6 = new HardwareBean();
        bean6.setImage(R.drawable.ic_camera);
        bean6.setModel("摄像头");
        bean6.setStatus(false);
        list.add(bean6);
        HardwareBean bean7 = new HardwareBean();
        bean7.setImage(R.drawable.ic_nfc);
        bean7.setModel("NFC");
        bean7.setStatus(false);
        list.add(bean7);
        return list;
    }
}
