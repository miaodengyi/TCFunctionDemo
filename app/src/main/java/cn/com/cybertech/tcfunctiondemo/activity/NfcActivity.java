package cn.com.cybertech.tcfunctiondemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * nfc
 * Created by miao on 2017/10/19.
 */

public class NfcActivity extends AppCompatActivity
{
    private Intent inintent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("NFC");
        setContentView(R.layout.activity_nfc);
        inintent = NfcActivity.this.getIntent();
        /**
         * 进行判断，如果是nfc的action，就进入handler里面的方法进行解析
         */
        if (inintent!=null)
        {
            if (inintent.getAction().equals("android.nfc.action.TAG_DISCOVERED"))
            {
                Toast.makeText(NfcActivity.this,"获取到数据",Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 界面在最顶层的时候接受到的数据
     * @param intent
     */
    @Override
    public void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        inintent = intent;
        if (inintent!=null)
        {
            if (inintent.getAction().equals("android.nfc.action.TAG_DISCOVERED"))
            {
                Toast.makeText(NfcActivity.this,"获取到数据",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
