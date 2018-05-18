package cn.com.cybertech.tcfunctiondemo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 主页面和计步的service里面要用到的日期，作为判断
 * Created by miao on 2017/6/29.
 */

public class DateUtils
{
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static DateUtils instance = null;
    public static DateUtils getInstance(){
        if (instance == null){
            instance = new DateUtils();
        }
        return instance;
    }

    //获取日期
    public String getDate()
    {
        Date date = new Date();
        return sdf.format(date);
    }

    //获得更细致的时分秒
    public String getDetailTime()
    {
        Date date = new Date();
        return sdf1.format(date);
    }
}
