package cn.com.cybertech.tcfunctiondemo.activity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 通过startactivityforresult传递数据
 * Created by miao on 2017/11/8.
 */

public class TransforDataActivity extends AppCompatActivity
{
    private TextView  tvTransfordata;
    private ImageView ivPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("通过startActivityForResult传递数据");
        setContentView(R.layout.activity_transfordata);
        tvTransfordata = (TextView) findViewById(R.id.tv_transfordata);
        ivPhoto = (ImageView) findViewById(R.id.iv_photo);
        /**
         * 启动康佳x1身份证模块的组件
         */
        try
        {
            Intent intent = new Intent();
            intent.setAction("cybertech.intent.action.IDCARD_READER");
            intent.setPackage("cn.com.cybertech.konka_read_card");
            startActivityForResult(intent, 1234);
        }
        catch (ActivityNotFoundException e)
        {
            new AlertDialog.Builder(this).setTitle("提示").setMessage("请先安装康盾二代证核查插件！").setPositiveButton("确定", null).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234)
        {
            if (data != null)
            {
                //身份证号码
                String cardNo    = data.getStringExtra("cardno");
                //地址
                String address   = data.getStringExtra("address");
                //姓名
                String name      = data.getStringExtra("name");
                //性别
                String sex       = data.getStringExtra("sex");
                //民族
                String nation    = data.getStringExtra("nation");
                //生日
                String birthday  = data.getStringExtra("birthday");
                //发证机关
                String grantdept = data.getStringExtra("grantdept");
                //开始有效期
                String dateStart = data.getStringExtra("dateStart");
                //结束有效期
                String dateEnd   = data.getStringExtra("dateEnd");
                //本人照片
                Bitmap bitmap    = (Bitmap) data.getParcelableExtra("bitmap");
                tvTransfordata.setText(cardNo + "\n" + address + "\n" + name + "\n" + sex + "\n" + nation + "\n" + birthday + "\n" + grantdept + "\n" + dateStart + "\n" + dateEnd);
                ivPhoto.setImageBitmap(bitmap);
            }
        }
    }
}
