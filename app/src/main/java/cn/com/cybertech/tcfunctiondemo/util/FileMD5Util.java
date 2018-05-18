package cn.com.cybertech.tcfunctiondemo.util;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取文件MD5
 * Created by miao on 2016/11/2.
 */

public class FileMD5Util
{
    //获取单个文件的MD5的值
    public static String getFileMD5(File file)
    {
        if (!file.isFile())
        {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try
        {
            digest = MessageDigest.getInstance("MD5");
            in  = new FileInputStream(file);
            while ((len = in.read(buffer,0,1024)) != -1)
            {
                digest.update(buffer,0,len);
            }
            in.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        //        BigInteger bigInt = new BigInteger(1,digest.digest());
        //        return bigInt.toString(16);
        return bytesToHexString(digest.digest());
    }

    //获取文件夹中文件的MD5的值
    public static Map<String,String> getDirMD5(File file,boolean listChild)
    {
        if (!file.isDirectory())
        {
            return null;
        }
        Map<String , String> map = new HashMap<>();
        String md5;
        File files[] = file.listFiles();
        for (int i = 0 ; i < files.length ; i++)
        {
            File f = files[i];
            if (f.isDirectory()&&listChild)
            {
                map.putAll(getDirMD5(f,listChild));
            }
            else
            {
                md5 = getFileMD5(f);
                if (md5 != null)
                {
                    map.put(f.getName(),md5);
                }
            }
        }
        return map;
    }

    private static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
