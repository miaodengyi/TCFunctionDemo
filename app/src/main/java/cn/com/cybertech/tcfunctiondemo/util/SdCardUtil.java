package cn.com.cybertech.tcfunctiondemo.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

/**
 * Created by m on 2017/9/11
 */

public class SdCardUtil
{
    private static final String str = "/sys/block/mmcblk1/device/";

    public static String getName()
    {
        Object localOb;
        try
        {
            localOb = new FileReader(str + "name"); // 厂商
            String sd_name = new BufferedReader((Reader) localOb).readLine();
            return sd_name;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return "";
        }
    }

    public static String getCid()
    {
        Object localOb;
        try
        {
            localOb = new FileReader(str + "cid"); // SD Card ID
            String sd_cid = new BufferedReader((Reader) localOb).readLine();
            System.out.println("cid: " + sd_cid);
            return sd_cid;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static String getCsd()
    {
        Object localOb;
        try
        {
            localOb = new FileReader(str + "csd");
            String sd_csd = new BufferedReader((Reader) localOb).readLine();
            System.out.println("csd: " + sd_csd);
            return sd_csd;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static String getFwrev()
    {
        Object localOb;
        try
        {
            localOb = new FileReader(str + "fwrev"); // 固件编号
            String sd_fwrev = new BufferedReader((Reader) localOb).readLine();
            System.out.println("fwrev: " + sd_fwrev);
            return sd_fwrev;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static String getHwrev()
    {
        Object localOb;
        try
        {
            localOb = new FileReader(str + "hwrev"); // 硬件版本
            String sd_hwrev = new BufferedReader((Reader) localOb).readLine();
            System.out.println("hwrev: " + sd_hwrev);
            return sd_hwrev;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static String getManfid()
    {
        Object localOb;
        try
        {
            localOb = new FileReader(str + "manfid"); // manufacture 制造
            String sd_manfid = new BufferedReader((Reader) localOb).readLine();
            System.out.println("manfid: " + sd_manfid);
            return sd_manfid;
        }
        catch (Exception e1)
        {
            System.out.println(e1.getMessage());
            return "";
        }
    }

    public static String getOemid()
    {
        Object localOb;
        try
        {
            localOb = new FileReader(str + "oemid"); // 原始设备制造商
            String sd_oemid = new BufferedReader((Reader) localOb).readLine();
            System.out.println("oemid: " + sd_oemid);
            return sd_oemid;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static String getScr()
    {
        Object localOb;
        try
        {
            localOb = new FileReader(str + "scr");
            String sd_scr = new BufferedReader((Reader) localOb).readLine();
            System.out.println("scr: " + sd_scr);
            return sd_scr;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static String getSerial()
    {
        Object localOb;
        try
        {
            localOb = new FileReader(str + "serial"); // 串号/序列号
            String sd_serial = new BufferedReader((Reader) localOb).readLine();
            System.out.println("serial: " + sd_serial);
            return sd_serial;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static String getDate()
    {
        Object localOb;
        try
        {
            localOb = new FileReader(str + "date"); // 生产日期
            String sd_date = new BufferedReader((Reader) localOb).readLine();
            System.out.println("date: " + sd_date);
            return sd_date;
        }
        catch (Exception e1)
        {
            System.out.println(e1.getMessage());
            return "";
        }
    }
}
