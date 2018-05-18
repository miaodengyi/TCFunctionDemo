package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.util.FileMD5Util;

/**
 * 关于md5的
 * Created by maio on 2017/6/1.
 */

public class Md5Activity extends AppCompatActivity
{

    private TextView     tvMd5;
    private StringBuffer stringBuffer;
    private String       path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("MD5相关");
        setContentView(R.layout.activity_md5);
        tvMd5 = (TextView) findViewById(R.id.tv_md5);
        stringBuffer = new StringBuffer();
        path = Environment.getExternalStorageDirectory().toString() + "/md5";
        File file = new File(path);
        if (file != null)
        {
            Map<String, String> map     = FileMD5Util.getDirMD5(file, true);
            Set                 entries = map.entrySet();
            if (entries != null)
            {
                Iterator iterator = entries.iterator();
                while (iterator.hasNext())
                {

                    Map.Entry entry = (Map.Entry) iterator.next();
                    Object    key   = entry.getKey();
                    //                    stringBuffer.append(key.toString() + "\n");
                    Object value = entry.getValue();
                    //                    stringBuffer.append("   :" + value.toString() + "\n");
                    if (!TextUtils.isEmpty(key.toString()))
                    {
                        String key1 = key.toString();
//                        if (!isMd5File(key1))
//                        {
                            String[] strings = key1.split("\\.");
                            String   oldName = strings[0];
                            String   suffix  = strings[1];
                            renameFile(oldName,value.toString(),"0",suffix);
//                        }
//                        else
//                        {
//                            stringBuffer.append("已经重命名了"+"\n");
//                            stringBuffer.append(key1+"\n");
//                            stringBuffer.append("   :" + value.toString() + "\n");
//                        }

                    }
                }
            }
            tvMd5.setText(stringBuffer);
        }
    }

    /**
     * 按   ：文件的MD5值#序号.后缀  规范进行命名
     *
     * @param oldName 旧名称
     * @param newName 新名称
     * @param num     数字
     * @param suffix  后缀
     * @return
     */
    public boolean renameFile(String oldName, String newName, String num, String suffix)
    {
        if (!TextUtils.isEmpty(oldName) && !TextUtils.isEmpty(newName))
        {
            String oldPathName     = path + "/" + oldName + "." + suffix;
            String newPathName     = path + "/" + newName + "#" + num + "." + suffix;
            File   oldPathNameFile = new File(oldPathName);
            File   newPathNameFile = new File(newPathName);
            return oldPathNameFile.renameTo(newPathNameFile);
        }
        return false;
    }

    //文件是否符合--文件的MD5值#序号.后缀--规范
    private boolean isMd5File(String fileName)
    {
        if (!TextUtils.isEmpty(fileName))
        {
            //文件名有#
            if (fileName.contains("#")&&fileName.contains("."))
            {
                String[] strings = fileName.split("\\.");
                if (strings.length == 2)
                {
                    String suffix = strings[1];
                    if (suffix.equals("wav") || suffix.equals("mp4") || suffix.equals("jpg") || suffix.equals("amr"))
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        return false;
    }
}
