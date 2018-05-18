package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * Created by miao on 2017/5/5.
 */

public class DeleteFileActivity extends AppCompatActivity
{
    private EditText etFileName;
    private Button btnFileName;
    private TextView tvDeleteResult;
    private StringBuffer stringBuffer = new StringBuffer();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("删除文件/文件夹");
        setContentView(R.layout.activity_delete_file);
        etFileName = (EditText) findViewById(R.id.et_file_name);
        tvDeleteResult = (TextView) findViewById(R.id.tv_delete_result);
        btnFileName = (Button) findViewById(R.id.btn_delete);
        btnFileName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String fileName = Environment.getExternalStorageDirectory()+"/"+etFileName.getText().toString();
                if (!TextUtils.isEmpty(fileName))
                {
                    delete(new File(fileName));
                    if (!TextUtils.isEmpty(stringBuffer))
                    {
                        tvDeleteResult.setText(stringBuffer);
                    }
                }
            }
        });
    }

    public boolean delete(File file)
    {
        if (!file.exists())
        {
            stringBuffer.append(file.getName()+":   文件不存在"+"\n");
            return false;
        }

        //如果是文件夹就遍历删除
        if (file.isDirectory())
        {
            File[] files = file.listFiles();
            if (files != null && files.length > 0)
            {
                for (File f : files)
                {
                    boolean success = delete(f);
                    if (!success)
                    {
                        stringBuffer.append(f.getName()+":   文件删除失败"+"\n");
                        return false;
                    }
                }
            }
        }
        //否则就直接删除文件
        stringBuffer.append(file.getName()+":   文件删除成功"+"\n");
        return file.delete();
    }

}
