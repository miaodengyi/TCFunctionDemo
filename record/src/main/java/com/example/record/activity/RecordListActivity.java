package com.example.record.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.record.R;
import com.example.record.adapter.VoiceAdapter;
import com.example.record.bean.VoiceBean;
import com.example.record.util.AudioRecoderUtils;
import com.example.record.util.FileSizeUtil;
import com.example.record.view.ItemDivider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 录音文件列表
 * Created by miao on 2018/5/17.
 */

public class RecordListActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private static final String path = Environment.getExternalStorageDirectory()+"/record/";

    private AudioRecoderUtils audioRecoderUtils;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("录音列表");
        setContentView(R.layout.activity_voice_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.rly_voice);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecordListActivity.this));
        recyclerView.addItemDecoration(new ItemDivider(RecordListActivity.this, R.drawable.divider_bg));
        final List<VoiceBean> list = listFile(path);
        VoiceAdapter adapter = new VoiceAdapter(RecordListActivity.this,list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new VoiceAdapter.OnItemClickListener()
        {
            @Override
            public void onClick(View parent, int position)
            {
                //需要放到里面初始化，不然如果到这个页面没有播放直接退出会报错
                if (audioRecoderUtils==null)
                {
                    audioRecoderUtils = new AudioRecoderUtils(RecordListActivity.this);
                }
                Toast.makeText(RecordListActivity.this,list.get(position).getPath(),Toast.LENGTH_LONG).show();
                if (!TextUtils.isEmpty(list.get(position).getPath()))
                {
                    audioRecoderUtils.playRecordFile(new File(list.get(position).getPath()));
                }
            }
        });
    }

    /**
     * 遍历文件夹下所有文件，并且得出文件名称，大小，修改时间等信息
     * @param path
     * @return
     */
    public List<VoiceBean> listFile(String path)
    {
        if (path == null)
        {
            return null;
        }
        List<VoiceBean> lists        = new ArrayList<>();

        if (!TextUtils.isEmpty(path))
        {
            try
            {
                File f     = new File(path);
                File[] files = f.listFiles();
                if (files != null)
                {
                    if (files.length > 0)
                    {
                        try
                        {
                            for (File file : files)
                            {
                                String   path2 = null;
                                VoiceBean bean  = new VoiceBean();
                                String   name  = file.getName();
                                bean.setName(name);
                                if (path.endsWith("/"))
                                {
                                    path2 = path + name;
                                }
                                else
                                {
                                    path2 = path + "/" + name;
                                }
                                bean.setPath(path2);
                                double size = FileSizeUtil.getFileOrFilesSize(path2,3);
                                bean.setSize(String.valueOf(size)+"M");
                                bean.setTime(longToDate(file.lastModified()));
                                lists.add(bean);
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return lists;
    }

    /**
     * @Description: long类型转换成日期
     *
     * @param lo 毫秒数
     * @return String yyyy-MM-dd HH:mm:ss
     */
    public static String longToDate(long lo){
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }

    /**
     * 左上角的返回键
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (audioRecoderUtils!=null)
                {
                    audioRecoderUtils.stopPalyer();
                }
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 返回键
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //需要判空，不然退出时会闪退
        if (audioRecoderUtils!=null)
        {
            audioRecoderUtils.stopPalyer();
        }
        finish();
    }

}
