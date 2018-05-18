package cn.com.cybertech.tcfunctiondemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 监测信号
 * Created by miao on 2017/6/15.
 */

public class SignalActivity extends AppCompatActivity
{
    private TextView tvSignal;
    private TextView tvSimType;
    private StringBuffer stringBuffer = new StringBuffer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("监测信号变化");
        setContentView(R.layout.activity_signal);
        tvSignal = (TextView) findViewById(R.id.tv_signal);
        tvSimType = (TextView) findViewById(R.id.tv_sim_type);
        getCurrentNetDBM(SignalActivity.this);
    }

    /**
     * 得到当前的手机蜂窝网络信号强度
     * 获取LTE网络和3G/2G网络的信号强度的方式有一点不同，
     * LTE网络强度是通过解析字符串获取的，
     * 3G/2G网络信号强度是通过API接口函数完成的。
     * asu 与 dbm 之间的换算关系是 dbm=-113 + 2*asu
     */
    public void getCurrentNetDBM(final Context context)
    {

        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        PhoneStateListener mylistener = new PhoneStateListener()
        {
            @Override
            public void onSignalStrengthsChanged(SignalStrength signalStrength)
            {
                super.onSignalStrengthsChanged(signalStrength);
                String   signalInfo = signalStrength.toString();
                String[] params     = signalInfo.split(" ");

                String IMSI = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();

                //中国移动 2g的部分是gsm，3g以上是中国专利的TD-SCDMA所以获取不到网络信号的好与差
                if (IMSI.startsWith("46000") || IMSI.startsWith("46002") || IMSI.startsWith("46007"))
                {
                    tvSimType.setText("中国移动的不可获取");
                    setDBM(0 + "");//中国移动3G不可获取，故在此返回0
                }
                //中国联通 2g的部分是gsm，3g以上是国际通用的WCDMA
                else if (IMSI.startsWith("46001") || IMSI.startsWith("46006"))
                {
                    tvSimType.setText("中国联通");
                    int cdmaDbm = signalStrength.getCdmaDbm();
                    int level = signalStrength.getLevel();
                    setDBM("cdmaDbm:"+cdmaDbm + "\n"+"level:"+level+"\n");
                }
                //中国电信 2g部分使用的是cdma中的CDMA2000 1X EV-DO，3g以上是WCDMA
                else if (IMSI.startsWith("46003") || IMSI.startsWith("46005"))
                {
                    tvSimType.setText("中国电信");
                    //evdoDbm是电信的通信用的1x的2G网络
                    //
                    int evdoDbm = signalStrength.getEvdoDbm();
                    //getCdmaDbm
                    //  >= -75    网络非常好   SIGNAL_STRENGTH_GREAT
                    //  >= -85    网络好       SIGNAL_STRENGTH_GOOD
                    //  >= -95    网络一般     SIGNAL_STRENGTH_MODERATE
                    //  >= -100   网络不好     SIGNAL_STRENGTH_POOR
                    int cdmaDbm = signalStrength.getCdmaDbm();
                    //getLevel是先获取的cdmaLevel，如不行获取evdoLevel
                    //等级从0到5
                    int level = signalStrength.getLevel();
                    setDBM("\n" + "evdoDbm:  " + evdoDbm + "\n" + "cdmaDbm:  " + cdmaDbm + "\n" + "level:  " + level + "\n");
                }
            }
        };
        //开始监听
        tm.listen(mylistener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
    }

    void setDBM(String s)
    {
        stringBuffer = stringBuffer.append(s + "\n");
        if (!TextUtils.isEmpty(stringBuffer))
        {
            tvSignal.setText(stringBuffer + "");
//            Toast.makeText(this, "Go to Firstdroid!!! GSM Cinr = " + s, Toast.LENGTH_SHORT).show();
        }
    }
}
