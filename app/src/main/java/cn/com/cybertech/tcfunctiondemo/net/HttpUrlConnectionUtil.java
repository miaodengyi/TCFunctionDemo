package cn.com.cybertech.tcfunctiondemo.net;

import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import cn.com.cybertech.tcfunctiondemo.App;
import cn.com.cybertech.tcfunctiondemo.util.DateUtils;
import cn.com.cybertech.tcfunctiondemo.util.IntentOpenFilesUtil;

/**
 * 邮箱下载
 * Created by miao on 2017/7/6.
 */

public class HttpUrlConnectionUtil extends AsyncTask<String, Void, String>
{
    private String fileName = null;

    @Override
    protected String doInBackground(String... params)
    {
        String url       = params[0]; // 要访问的链接
        String cookieStr = params[1];//cookie

        if (TextUtils.isEmpty(url))
        {
            return null;
        }

        // 下载文件
        try
        {
            URL               myFileUrl = new URL(url);
            HttpURLConnection conn      = (HttpURLConnection) myFileUrl.openConnection();
            // 携带cookie请求，要写在请求开始之前
            conn.addRequestProperty("Cookie", cookieStr);
            conn.setDoInput(true);
            conn.connect();

            //获取http的头文件
            Map<String, List<String>> map = conn.getHeaderFields();
            String                    s1  = conn.getResponseMessage();

            //获取文件名
            String temp  = new String((conn.getHeaderField("content-disposition")).getBytes("ISO-8859-1"), "utf8");
            String fName = "";
            String fType = "";
            if (!TextUtils.isEmpty(temp))
            {
                fName = DateUtils.getInstance().getDetailTime();
                fType = temp.substring(temp.lastIndexOf("."), temp.length() - 1);
                fileName = fName + fType;
            }

            if (fileName != null)
            {
                File directory = App.sContext.getExternalFilesDir(null);
                File file      = new File(directory, fileName);
                if (file.exists())
                {
                    file.delete();
                }
            }

            InputStream is = conn.getInputStream();
            // 我们把文件保存在sd卡根目录
            FileOutputStream out    = new FileOutputStream(App.sContext.getExternalFilesDir(null).getPath() + "/" + fileName);
            byte[]           buffer = new byte[1024];
            int              r;
            while ((r = is.read(buffer)) > 0)
            {
                out.write(buffer, 0, r);
                out.flush();
            }
            is.close();
            out.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return fileName;
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        // 这里可以对下载好的文件进行打开等操作
        System.out.println();
        //打开下载好的文件
        if (!TextUtils.isEmpty(fileName))
        {
            Intent intent = IntentOpenFilesUtil.getInstance().getWordFileIntent(App.sContext.getExternalFilesDir(null).getPath() + "/" + fileName);
            App.sContext.startActivity(intent);
        }
    }
}
