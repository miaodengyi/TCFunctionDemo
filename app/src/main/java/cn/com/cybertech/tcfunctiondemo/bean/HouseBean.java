package cn.com.cybertech.tcfunctiondemo.bean;

import java.util.ArrayList;

/**
 * 房屋信息
 * Created by miao on 2017/7/20.
 */

public class HouseBean
{
    private int num;//门牌号
    private ArrayList<PersonBean> list;//租客信息

    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public ArrayList<PersonBean> getList()
    {
        return list;
    }

    public void setList(ArrayList<PersonBean> list)
    {
        this.list = list;
    }
}
