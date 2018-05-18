package cn.com.cybertech.tcfunctiondemo.activity;

import android.content.Intent;
import android.net.TrafficStats;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 流量监控
 * Created by miao on 2017/8/9.
 */

public class TrafficStatsActivity extends AppCompatActivity
{
    private TextView tvTotalRx, tvWifiRx, tvGprsRx;//接受量
    private TextView tvTotalTx, tvWifiTx, tvGprsTx;//发送量
    private Button btnTrafficApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("流量监控");
        setContentView(R.layout.activity_trafficstats);
        tvTotalRx = (TextView) findViewById(R.id.tv_total_rx);
        tvTotalTx = (TextView) findViewById(R.id.tv_total_tx);
        tvWifiRx = (TextView) findViewById(R.id.tv_wifi_rx);
        tvWifiTx = (TextView) findViewById(R.id.tv_wifi_tx);
        tvGprsRx = (TextView) findViewById(R.id.tv_gprs_rx);
        tvGprsTx = (TextView) findViewById(R.id.tv_gprs_tx);
        getTrafficStatsData();
        btnTrafficApp = (Button) findViewById(R.id.btn_traffic_app);
        btnTrafficApp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(TrafficStatsActivity.this,TrafficAppActivity.class));
            }
        });
    }

    private void getTrafficStatsData()
    {
        double totalRx = TrafficStats.getTotalRxBytes();
        double totalTx = TrafficStats.getTotalTxBytes();
        double gprsRx = TrafficStats.getMobileRxBytes();
        double gprsTx = TrafficStats.getMobileTxBytes();
        double wifiRx = totalRx - gprsRx;
        double wifiTx = totalTx - gprsTx;
        tvTotalRx.setText(totalRx+"");
        tvTotalTx.setText(totalTx+"");
        tvWifiRx.setText(wifiRx+"");
        tvWifiTx.setText(wifiTx+"");
        tvGprsRx.setText(gprsRx+"");
        tvGprsTx.setText(gprsTx+"");
    }
}
