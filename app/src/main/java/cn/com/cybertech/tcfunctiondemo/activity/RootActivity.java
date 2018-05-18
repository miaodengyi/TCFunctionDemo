package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 查看手机是否被root
 * Created by miao on 2018/1/19.
 */

public class RootActivity extends AppCompatActivity
{
    private TextView tvSysVersion;
    private TextView tvSuperUser;
    private TextView tvSuExist1,tvSuExist2;
    private TextView tvBusyBox;
    private TextView tvData;
    private TextView tvSuExist3;
    private Button btnRunSu;
    private Button btnRunSu2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("查看手机是否被root");
        setContentView(R.layout.activity_root);
        init();
    }

    private void init()
    {
        btnRunSu = (Button) findViewById(R.id.btn_run_su);
        tvSysVersion = (TextView) findViewById(R.id.tv_sys_version);
        if (checkDeviceDebuggable())
        {
            tvSysVersion.setText("测试版");
        }else
        {
            tvSysVersion.setText("正式版");
        }
        tvSuperUser = (TextView) findViewById(R.id.tv_superuser);
        if (checkSuperuserApk())
        {
            tvSuperUser.setText("存在");
        }else
        {
            tvSuperUser.setText("不存在");
        }
        tvSuExist1 = (TextView) findViewById(R.id.tv_su_exist_1);
        if (checkSuFile())
        {
            tvSuExist1.setText("存在");
        }else
        {
            tvSuExist1.setText("不存在");
        }
        tvSuExist2 = (TextView) findViewById(R.id.tv_su_exist_2);
        if (checkRootFile()!=null)
        {
            tvSuExist2.setText("存在");
        }else
        {
            tvSuExist2.setText("不存在");
        }
        tvBusyBox = (TextView) findViewById(R.id.tv_busybox);
        if (checkBusybox())
        {
            tvBusyBox.setText("已安装");
        }else
        {
            tvBusyBox.setText("没有装");
        }
        tvData = (TextView) findViewById(R.id.tv_data);
        if (checkAccessRootData())
        {
            tvData.setText("可读写");
        }
        else
        {
            tvData.setText("不可");
        }
        tvSuExist3 = (TextView) findViewById(R.id.tv_su_exist_3);
        if (isRoot())
        {
            tvSuExist3.setText("有");
        }else
        {
            tvSuExist3.setText("没有");
        }
        btnRunSu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkRootExecutable();
            }
        });
        btnRunSu2 = (Button) findViewById(R.id.btn_run_su_2);
        btnRunSu2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkGetRootAuth();
            }
        });
    }

    /**
     * 方法1
     * 查看系统是否是测试版
     * @return
     */
    public static boolean checkDeviceDebuggable(){
        String buildTags = android.os.Build.TAGS;
        if (buildTags != null && buildTags.contains("test-keys")) {
            Log.i("TcFunctionDemo","buildTags="+buildTags);
            return true;
        }
        return false;
    }

    /**
     * 方法2
     * Superuser.apk是一个被广泛使用的用来root安卓设备的软件，所以可以检查这个app是否存在。
     * @return
     */
    public static boolean checkSuperuserApk(){
        try {
            File file = new File("/system/app/Superuser.apk");
            if (file.exists()) {
                Log.i("TcFunctionDemo","/system/app/Superuser.apk exist");
                return true;
            }
        } catch (Exception e) { }
        return false;
    }

    /**
     * 方法3
     * 用switch命令检查手机是否有su文件
     * @return
     */
    private static boolean checkSuFile()
    {
        String[]          strCmd     = new String[] {"/system/xbin/which","su"};
        ArrayList<String> execResult = executeCommand(strCmd);
        if (execResult != null){
            Log.i("TcFunctionDemo","execResult="+execResult.toString());
            return true;
        }else{
            Log.i("TcFunctionDemo","execResult=null");
            return false;
        }
    }

    public static ArrayList<String> executeCommand(String[] shellCmd){
        String line = null;
        ArrayList<String> fullResponse = new ArrayList<String>();
        Process localProcess = null;
        try {
            Log.i("TcFunctionDemo","to shell exec which for find su :");
            localProcess = Runtime.getRuntime().exec(shellCmd);
        } catch (Exception e) {
            return null;
        }
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(localProcess.getOutputStream()));
        BufferedReader in  = new BufferedReader(new InputStreamReader(localProcess.getInputStream()));
        try {
            while ((line = in.readLine()) != null) {
                Log.i("TcFunctionDemo","–> Line received: " + line);
                fullResponse.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("TcFunctionDemo","–> Full response was: " + fullResponse);
        return fullResponse;
    }

    /**
     * 方法4
     * 制定目录下有没有su文件
     * @return
     */
    private static File checkRootFile()
    {
        File file = null;
        String[] paths = {"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"};
        for (String path : paths)
        {
            file = new File(path);
            if (file.exists())
            {
                return file;
            }
        }
        return file;
    }

    /**
     * 方法5
     * 执行su命令
     * @return
     */
    public static synchronized boolean checkGetRootAuth()
    {
        Process process = null;
        DataOutputStream os = null;
        try
        {
            Log.i("TcFunctionDemo","to exec su");
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("exit\n");
            os.flush();
            int exitValue = process.waitFor();
            Log.i("TcFunctionDemo", "exitValue="+exitValue);
            if (exitValue == 0)
            {
                return true;
            } else
            {
                return false;
            }
        } catch (Exception e)
        {
            Log.i("TcFunctionDemo", "Unexpected error - Here is what I know: "
                    + e.getMessage());
            return false;
        } finally
        {
            try
            {
                if (os != null)
                {
                    os.close();
                }
                process.destroy();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 方法6
     *  这种方式会弹窗，如果用户点击拒绝授权那么判断依然是没有root
     */
    private static boolean checkRootExecutable() {

        Process          process = null;
        DataOutputStream os      = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("exit\n");
            os.flush();
            int exitValue = process.waitFor();
            if (exitValue == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.d("*** DEBUG ***", "Unexpected error - Here is what I know: " + e.getMessage());
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 方法7
     * busybox是否被安装上
     * @return
     */
    public static synchronized boolean checkBusybox()
    {
        try
        {
            Log.i("TcFunctionDemo","to exec busybox df");
            String[] strCmd = new String[] {"busybox","df"};
            ArrayList<String> execResult = executeCommand(strCmd);
            if (execResult != null){
                Log.i("TcFunctionDemo","execResult="+execResult.toString());
                return true;
            }else{
                Log.i("TcFunctionDemo","execResult=null");
                return false;
            }
        } catch (Exception e)
        {
            Log.i("TcFunctionDemo", "Unexpected error - Here is what I know: "
                    + e.getMessage());
            return false;
        }
    }

    /**
     * 方法8
     * 查看data目录是否可读写
     * @return
     */
    public static synchronized boolean checkAccessRootData()
    {
        try
        {
            Log.i("TcFunctionDemo","to write /data");
            String fileContent = "test_ok";
            Boolean writeFlag = writeFile("/data/su_test",fileContent);
            if (writeFlag){
                Log.i("TcFunctionDemo","write ok");
            }else{
                Log.i("TcFunctionDemo","write failed");
            }

            Log.i("TcFunctionDemo","to read /data");
            String strRead = readFile("/data/su_test");
            Log.i("TcFunctionDemo","strRead="+strRead);
            if(fileContent.equals(strRead)){
                return true;
            }else {
                return false;
            }
        } catch (Exception e)
        {
            Log.i("TcFunctionDemo", "Unexpected error - Here is what I know: "
                    + e.getMessage());
            return false;
        }
    }

    //写文件
    public static Boolean writeFile(String fileName,String message){
        try{
            FileOutputStream fout  = new FileOutputStream(fileName);
            byte []          bytes = message.getBytes();
            fout.write(bytes);
            fout.close();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    //读文件
    public static String readFile(String fileName){
        File file = new File(fileName);
        try {
            FileInputStream       fis   = new FileInputStream(file);
            byte[]                bytes = new byte[1024];
            ByteArrayOutputStream bos   = new ByteArrayOutputStream();
            int                   len;
            while((len=fis.read(bytes))>0){
                bos.write(bytes, 0, len);
            }
            String result = new String(bos.toByteArray());
            Log.i("TcFunctionDemo", result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 方法九
     * 查看2个文件夹下是否有su文件判断手机是否ROOT
     * 返回true，被root过
     * 返回false，没有被root过
     */
    public boolean isRoot() {

        boolean root = false;

        try {
            if ((!new File("/system/bin/su").exists())
                    && (!new File("/system/xbin/su").exists())) {
                root = false;
            } else {
                root = true;
            }

        } catch (Exception e) {
        }

        return root;
    }

}
