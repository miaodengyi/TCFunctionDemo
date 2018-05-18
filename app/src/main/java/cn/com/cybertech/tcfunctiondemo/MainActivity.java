package cn.com.cybertech.tcfunctiondemo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import cn.com.cybertech.tcfunctiondemo.activity.ApkInfoActivity;
import cn.com.cybertech.tcfunctiondemo.activity.AppActivity;
import cn.com.cybertech.tcfunctiondemo.activity.BatteryActivity;
import cn.com.cybertech.tcfunctiondemo.activity.CameraActivity;
import cn.com.cybertech.tcfunctiondemo.activity.CountDownTimerActivity;
import cn.com.cybertech.tcfunctiondemo.activity.DeleteFileActivity;
import cn.com.cybertech.tcfunctiondemo.activity.FileActivity;
import cn.com.cybertech.tcfunctiondemo.activity.FragmentActivity;
import cn.com.cybertech.tcfunctiondemo.activity.GifActivity;
import cn.com.cybertech.tcfunctiondemo.activity.GpsActivity;
import cn.com.cybertech.tcfunctiondemo.activity.GreenDaoActivity;
import cn.com.cybertech.tcfunctiondemo.activity.GridViewActivity;
import cn.com.cybertech.tcfunctiondemo.activity.HardwareInfoActivity;
import cn.com.cybertech.tcfunctiondemo.activity.ImeiActivity;
import cn.com.cybertech.tcfunctiondemo.activity.InstallSlientActivity;
import cn.com.cybertech.tcfunctiondemo.activity.ListViewSelectActivity;
import cn.com.cybertech.tcfunctiondemo.activity.MachineReportActivity;
import cn.com.cybertech.tcfunctiondemo.activity.MaterialActivity;
import cn.com.cybertech.tcfunctiondemo.activity.Md5Activity;
import cn.com.cybertech.tcfunctiondemo.activity.MdmPageActivity;
import cn.com.cybertech.tcfunctiondemo.activity.MyCameraActivity;
import cn.com.cybertech.tcfunctiondemo.activity.NfcActivity;
import cn.com.cybertech.tcfunctiondemo.activity.PassDataActivity;
import cn.com.cybertech.tcfunctiondemo.activity.PathActivity;
import cn.com.cybertech.tcfunctiondemo.activity.PrintLogActivity;
import cn.com.cybertech.tcfunctiondemo.activity.ProgressActivity;
import cn.com.cybertech.tcfunctiondemo.activity.QrCodeActivity;
import cn.com.cybertech.tcfunctiondemo.activity.RestartActivity;
import cn.com.cybertech.tcfunctiondemo.activity.RootActivity;
import cn.com.cybertech.tcfunctiondemo.activity.SdcardActivity;
import cn.com.cybertech.tcfunctiondemo.activity.SdcardRecordActivity;
import cn.com.cybertech.tcfunctiondemo.activity.ShakeActivity;
import cn.com.cybertech.tcfunctiondemo.activity.SignalActivity;
import cn.com.cybertech.tcfunctiondemo.activity.SimInfoActivity;
import cn.com.cybertech.tcfunctiondemo.activity.SkipAppActivity;
import cn.com.cybertech.tcfunctiondemo.activity.StepCountActivity;
import cn.com.cybertech.tcfunctiondemo.activity.TimeFileActivity;
import cn.com.cybertech.tcfunctiondemo.activity.TrafficStatsActivity;
import cn.com.cybertech.tcfunctiondemo.activity.TransforActivity;
import cn.com.cybertech.tcfunctiondemo.activity.TransforDataActivity;
import cn.com.cybertech.tcfunctiondemo.activity.VoiceActivity;
import cn.com.cybertech.tcfunctiondemo.activity.WebViewActivity;
import cn.com.cybertech.tcfunctiondemo.activity.WindowManagerActivity;
import cn.com.cybertech.tcfunctiondemo.view.MyCamera;

public class MainActivity extends AppCompatActivity
{

    private Button btnApp, btnFile, btnDeleteFile, btnApkInfo, btnBattery, btnSdcard, btnPrintLog, btnCamera, btnSdcardRecord;
    private Button btnMd5, btnPath, btnProgress, btnSignal, btnGreenDao, btnOkHttp, btnStepCount, btnWebView, btnPassDataCompputer;
    private Button btnCountDownTimer, btnMyPhoto, btnGridView, btnListViewSelect, btnMachineReport, btnTrafficStats, btnRestart;
    private Button btnInstallSlient, btnWindowManager, btnImei,btnGps,btnSimInfo,btnQrCode,btnNfc,btnHardwareInfo;
    private Button btnGif,btnTransforData,btnSkipApp,btnShake,btnMaterialSpinner,btnKeyBoard,btnTimeFile,btnRoot,btnFragment;
    private Button btnVoice,btnTransfor;

    public static final int CAMERA_CODE = 0x00;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if (shouldAskPermissions())
        {
            askPermissions();
        }
        printLog();
        btnApp = (Button) findViewById(R.id.btn_app);
        btnApp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, AppActivity.class));
            }
        });
        btnFile = (Button) findViewById(R.id.btn_file);
        btnFile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, FileActivity.class));
            }
        });
        btnDeleteFile = (Button) findViewById(R.id.btn_delete_file);
        btnDeleteFile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, DeleteFileActivity.class));
            }
        });
        btnApkInfo = (Button) findViewById(R.id.btn_apk_info);
        btnApkInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, ApkInfoActivity.class));
            }
        });
        btnBattery = (Button) findViewById(R.id.btn_battery);
        btnBattery.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, BatteryActivity.class));
            }
        });
        btnSdcard = (Button) findViewById(R.id.btn_sdcard);
        btnSdcard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, SdcardActivity.class));
            }
        });
        btnPrintLog = (Button) findViewById(R.id.btn_print_log);
        btnPrintLog.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, PrintLogActivity.class));
            }
        });
        btnCamera = (Button) findViewById(R.id.camera);
        btnCamera.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivityForResult(new Intent(MainActivity.this, CameraActivity.class), CAMERA_CODE);
            }
        });
        btnSdcardRecord = (Button) findViewById(R.id.btn_sdcard_record);
        btnSdcardRecord.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, SdcardRecordActivity.class));
            }
        });
        btnMd5 = (Button) findViewById(R.id.btn_md5);
        btnMd5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, Md5Activity.class));
            }
        });
        btnPath = (Button) findViewById(R.id.btn_path);
        btnPath.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, PathActivity.class));
            }
        });
        btnProgress = (Button) findViewById(R.id.btn_progress);
        btnProgress.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, ProgressActivity.class));
            }
        });
        btnSignal = (Button) findViewById(R.id.btn_signal);
        btnSignal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, SignalActivity.class));
            }
        });
        btnGreenDao = (Button) findViewById(R.id.btn_greendao);
        btnGreenDao.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, GreenDaoActivity.class));
            }
        });
        btnOkHttp = (Button) findViewById(R.id.btn_okhttp);
        btnOkHttp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
        btnStepCount = (Button) findViewById(R.id.btn_step_count);
        btnStepCount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, StepCountActivity.class));
            }
        });
        btnWebView = (Button) findViewById(R.id.btn_webview);
        btnWebView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, WebViewActivity.class));
            }
        });
        btnPassDataCompputer = (Button) findViewById(R.id.btn_pass_data_computer);
        btnPassDataCompputer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, PassDataActivity.class));
            }
        });
        btnCountDownTimer = (Button) findViewById(R.id.btn_count_down_timer);
        btnCountDownTimer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, CountDownTimerActivity.class));
            }
        });
        btnMyPhoto = (Button) findViewById(R.id.btn_my_camera);
        btnMyPhoto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, MyCameraActivity.class));
            }
        });
        btnGridView = (Button) findViewById(R.id.btn_gridview);
        btnGridView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, GridViewActivity.class));
            }
        });
        btnListViewSelect = (Button) findViewById(R.id.btn_listview_select);
        btnListViewSelect.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, ListViewSelectActivity.class));
            }
        });
        btnMachineReport = (Button) findViewById(R.id.btn_machine_report);
        btnMachineReport.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, MdmPageActivity.class));
            }
        });
        btnTrafficStats = (Button) findViewById(R.id.btn_traffic_stats);
        btnTrafficStats.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, TrafficStatsActivity.class));
            }
        });
        btnRestart = (Button) findViewById(R.id.btn_restart);
        btnRestart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, RestartActivity.class));
            }
        });
        btnInstallSlient = (Button) findViewById(R.id.btn_install_slient);
        btnInstallSlient.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, InstallSlientActivity.class));
            }
        });
        btnWindowManager = (Button) findViewById(R.id.btn_window_manager);
        btnWindowManager.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, WindowManagerActivity.class));
            }
        });
        btnImei = (Button) findViewById(R.id.btn_get_imei);
        btnImei.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, ImeiActivity.class));
            }
        });
        btnGps = (Button) findViewById(R.id.btn_gps);
        btnGps.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, GpsActivity.class));
            }
        });
        btnSimInfo = (Button) findViewById(R.id.btn_sim_info);
        btnSimInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, SimInfoActivity.class));
            }
        });
        btnQrCode = (Button) findViewById(R.id.btn_qrcode);
        btnQrCode.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, QrCodeActivity.class));
            }
        });
        btnNfc = (Button) findViewById(R.id.btn_nfc);
        btnNfc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, NfcActivity.class));
            }
        });
        btnHardwareInfo = (Button) findViewById(R.id.btn_hardware_info);
        btnHardwareInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, HardwareInfoActivity.class));
            }
        });
        btnGif = (Button) findViewById(R.id.btn_gif);
        btnGif.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, GifActivity.class));
            }
        });
        btnTransforData = (Button) findViewById(R.id.btn_transfor_data);
        btnTransforData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, TransforDataActivity.class));
            }
        });
        btnSkipApp = (Button) findViewById(R.id.btn_skip);
        btnSkipApp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, SkipAppActivity.class));
            }
        });
        btnShake = (Button) findViewById(R.id.btn_shake);
        btnShake.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, ShakeActivity.class));
            }
        });
        btnMaterialSpinner = (Button) findViewById(R.id.btn_material_spinner);
        btnMaterialSpinner.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, MaterialActivity.class));
            }
        });
        btnKeyBoard = (Button) findViewById(R.id.btn_keyboard);
        btnKeyBoard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
        btnTimeFile = (Button) findViewById(R.id.btn_time_file);
        btnTimeFile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, TimeFileActivity.class));
            }
        });
        btnRoot = (Button) findViewById(R.id.btn_root);
        btnRoot.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, RootActivity.class));
            }
        });
        btnFragment = (Button) findViewById(R.id.btn_fragment);
        btnFragment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, FragmentActivity.class));
            }
        });
        btnVoice = (Button) findViewById(R.id.btn_voice);
        btnVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VoiceActivity.class));
            }
        });
        btnTransfor = (Button) findViewById(R.id.btn_transfor);
        btnTransfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TransforActivity.class));
            }
        });
    }

    protected boolean shouldAskPermissions()
    {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions()
    {
        String[] permissions = {
                "android.permission.INTERNET",
                "android.permission.READ_PHONE_STATE",
                "android.permission.VIBRATE",
                "android.permission.ACCESS_NETWORK_STATE",
                "android.permission.ACCESS_COARSE_LOCATION",
                "android.permission.ACCESS_FINE_LOCATION",
                "android.permission.WAKE_LOCK",
                "android.permission.CAMERA",
                "android.permission.WRITE_EXTERNAL_STORAGE",
                "android.permission.MOUNT_UNMOUNT_FILESYSTEMS",
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.READ_LOGS",
                "android.permission.RECORD_AUDIO",
                "android.permission.CHANGE_NETWORK_STATE",
                "android.permission.WRITE_SETTINGS",
                "android.permission.RECEIVE_BOOT_COMPLETED",
                "android.permission.SYSTEM_ALERT_WINDOW",
                "android.permission.NFC",
                "android.permission.VIBRATE"
        };
        int      requestCode = 200;
        requestPermissions(permissions, requestCode);
    }

    /**
     * 执行Timer进度
     **/
    public static final int LOAD_PROGRESS  = 0;
    /**
     * 关闭Timer
     **/
    public static final int CLOSE_PROGRESS = 1;
    /**
     * Timer对象
     **/
    Timer     mTimer     = null;
    /**
     * timerTask对象
     **/
    TimerTask mTimerTask = null;
    /**
     * 记录TimerId
     **/
    int       mTimerID   = 0;

    Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case LOAD_PROGRESS:
                    System.out.println("当前得TimerID为：" + msg.arg1);
                    break;
                case CLOSE_PROGRESS:
                    System.out.println("当前Timer已经关闭请重新启动");
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void printLog()
    {
        startTimer();
    }

    public void startTimer()
    {
        if (mTimer == null)
        {
            mTimerTask = new TimerTask()
            {
                @Override
                public void run()
                {
                    mTimerID++;
                    Message msg = new Message();
                    msg.what = LOAD_PROGRESS;
                    msg.arg1 = (int) (mTimerID);
                    handler.sendMessage(msg);
                }
            };
            mTimer = new Timer();
            //第一个参数为执行的mTimerTask
            //第二个参数为延迟得事件，这里写1000得意思是mTimerTask将延迟1秒执行
            //第三个参数为多久执行一次，这里写1000表示没1秒执行一次mTimerTask的Run方法
            mTimer.schedule(mTimerTask, 1000, 1000);
        }
    }

    public void closeTimer()
    {
        if (mTimer != null)
        {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null)
        {
            mTimerTask = null;
        }
        mTimerID = 0;
        handler.sendEmptyMessage(CLOSE_PROGRESS);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        closeTimer();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_CODE && data != null)
        {
            String videoPath = data.getStringExtra("videoPath");
            Toast.makeText(MainActivity.this, videoPath, Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
