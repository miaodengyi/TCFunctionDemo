package cn.com.cybertech.tcfunctiondemo.activity;

import android.Manifest;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import com.mpt.aidlservice.IApnAndSafeKey;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 我的手机页面
 * Created by miao on 2017/9/27.
 */

public class MyPhoneActivity extends AppCompatActivity
{
    private TextView tvMyphoneTitle;//标题
    private TextView tvNameId;//警员姓名和警号
    private TextView tvDeptNameId;//部门名和部门编号
    private TextView tvOutOfLineTimes;//违规次数
    private TextView tvPhoneNum;//手机号
    private TextView tvCarrieroperator;//运营商
    private TextView tvImei;//手机的imei
    private TextView tvSdcardId;//安全卡id
    private TextView tvSimId;//sim卡id

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_phone);
        init();
        // 创建所需绑定服务的Intent
        Intent intent = new Intent();
        intent.setAction("com.mpt.aidlservice.AidlService");
        // 绑定远程服务
        intent.setPackage("com.xdja.safeclient");
        bindService(intent, conn, Service.BIND_AUTO_CREATE);
        getPhoneImei();
    }

    private void init()
    {
        tvMyphoneTitle = (TextView) findViewById(R.id.tv_myphone_title);
        tvNameId = (TextView) findViewById(R.id.tv_name_id);
        tvDeptNameId = (TextView) findViewById(R.id.tv_dept_id);
        tvOutOfLineTimes = (TextView) findViewById(R.id.tv_out_of_line_times);
        tvPhoneNum = (TextView) findViewById(R.id.tv_phone_num);
        tvCarrieroperator = (TextView) findViewById(R.id.tv_carrieroperator);
        tvImei = (TextView) findViewById(R.id.tv_imei);
        tvSdcardId = (TextView) findViewById(R.id.tv_sdcard);
        tvSimId = (TextView) findViewById(R.id.tv_sim);
    }


    private TelephonyManager tm;

    /**
     * 手机imei号,手机号,运营商,sim卡序列号
     */
    private void getPhoneImei()
    {
        tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        tvImei.setText(tm.getDeviceId());
        tvPhoneNum.setText(tm.getLine1Number());
        String netOperator = tm.getNetworkOperator();
        if (netOperator.startsWith("46000") || netOperator.startsWith("46002") || netOperator.startsWith("46007"))
        {
            tvCarrieroperator.setText("中国移动");
        }
        //中国联通 2g的部分是gsm，3g以上是国际通用的WCDMA
        else if (netOperator.startsWith("46001") || netOperator.startsWith("46006"))
        {
            tvCarrieroperator.setText("中国联通");
        }
        //中国电信 2g部分使用的是cdma中的CDMA2000 1X EV-DO，3g以上是WCDMA
        else if (netOperator.startsWith("46003") || netOperator.startsWith("46005"))
        {
            tvCarrieroperator.setText("中国电信");
        }
        tvSimId.setText(tm.getSubscriberId());
    }

    /**
     * 获取信大捷安的sd安全卡id
     */
    private IApnAndSafeKey iApnAndSafeKey;
    private ServiceConnection conn = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            // 获取远程Service的onBind方法返回的对象的代理
            Log.v("AidlClient", " ---------onServiceConnected------------ ");
            iApnAndSafeKey = IApnAndSafeKey.Stub.asInterface(service);
            getInfoFromSevice();
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            Log.v("AidlClient", " ---------onServiceDisconnected------------ ");
            iApnAndSafeKey = null;
        }
    };

    /**
     * 从service中获取sd安全卡的id
     */
    private void getInfoFromSevice()
    {
        try
        {
            // 获取、并显示远程Service的状态
            if (iApnAndSafeKey != null)
            {
                //获取卡id
                getCardId(iApnAndSafeKey);
            }
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 获取sd安全卡的id
     *
     * @param iApnAndSafeKey
     * @throws RemoteException
     */
    private void getCardId(IApnAndSafeKey iApnAndSafeKey) throws RemoteException
    {
        String cardId = iApnAndSafeKey.XKF_GetCardID();
        if (cardId != null)
        {
            Log.d("AidlClient", "cardId = " + cardId);
            tvSdcardId.setText(cardId);
        }
    }

    @Override
    protected void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
        unbindService(conn);
    }

}
