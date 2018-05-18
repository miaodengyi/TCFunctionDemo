package cn.com.cybertech.tcfunctiondemo.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 北京互联网域管控界面
 * Created by miao on 2017/8/1.
 */

public class MdmPageActivity extends AppCompatActivity
{
    private Button btnLogin, btnMain, btnActivate, btnWarning, btnNewMain, btnEmpower, btnNewEmpower,btnMyPhone;
    public  Dialog dialog;
    private View   inflate;
    private Button btnCancel, btnAgree;
    private ListView listView;
    private TextView tvEmpowerProtocal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("管控界面");
        setContentView(R.layout.activity_mdm_pages);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MdmPageActivity.this, MdmLoginActivity.class));
            }
        });
        btnMain = (Button) findViewById(R.id.btn_main);
        btnMain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MdmPageActivity.this, MachineReportActivity.class));
            }
        });
        btnActivate = (Button) findViewById(R.id.btn_activate);
        btnActivate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MdmPageActivity.this, ActivateActivity.class));
            }
        });
        btnWarning = (Button) findViewById(R.id.btn_warning);
        btnWarning.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MdmPageActivity.this, WarningActivity.class));
            }
        });
        btnNewMain = (Button) findViewById(R.id.btn_new_main);
        btnNewMain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MdmPageActivity.this, NewMachineReportActivity.class));
            }
        });
        btnEmpower = (Button) findViewById(R.id.btn_empower);
        btnEmpower.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showEmpowerDialog();
                //showUpdateDialog();
            }
        });
        btnNewEmpower = (Button) findViewById(R.id.btn_new_empower);
        btnNewEmpower.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showNewEmpowerDialog();
            }
        });
        btnMyPhone = (Button) findViewById(R.id.btn_my_phone);
        btnMyPhone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MdmPageActivity.this,MyPhoneActivity.class));
            }
        });
    }

    private void showUpdateDialog()
    {
        final AlertDialog.Builder builder  = new AlertDialog.Builder(this, R.style.AlertDialog);
        AlertDialog               dialog;
        TextView                  textView = new TextView(this);
        textView.setText("提示：警灯工程程序已经发布新版本，程序更加稳定与友好，请退出后在管理平台，应用列表中下载“警灯工程2.0”安装使用。");
        textView.setTextSize(getResources().getDimension(R.dimen.five));
        textView.setTextColor(getResources().getColor(R.color.status_green));
        textView.setPadding(50, 90, 0, 0);
        builder.setView(textView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //使dialog消失
                if (dialog != null)
                {
                    dialog.dismiss();
                }
            }
        });
        dialog = builder.show();
    }

    private ArrayList<String> list;
    private ArrayAdapter      arrayAdapter;

    void showEmpowerDialog()
    {
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.dialog_empower, null);
        listView = (ListView) inflate.findViewById(R.id.lv_empower);
        list = new ArrayList<>();
        list.add("1.启用/禁用WLAN;");
        list.add("2.启用/禁用WLAN热点;");
        list.add("3.启用/禁用USB调试模式、数据传输;");
        list.add("4.启用/禁用存储访问(MicroSD卡);");
        list.add("5.启用/禁用NFC;");
        list.add("6.启用/禁用数据连接;");
        list.add("7.启用/禁用状态栏下拉菜单;");
        list.add("8.启用/禁用通话;");
        list.add("9.启用/禁用短信;");
        list.add("10.重启、关机;");
        list.add("11.获取root状态;");
        list.add("12.挂断当前通话;");
        list.add("13.静默安装/卸载某应用;");
        list.add("14.删除某应用数据;");
        arrayAdapter = new ArrayAdapter(MdmPageActivity.this, R.layout.item_empower_function, R.id.item_tv, list);
        listView.setAdapter(arrayAdapter);

        //借助SpannableString类实现超链接文字
        tvEmpowerProtocal = (TextView) inflate.findViewById(R.id.tv_empower_protocal);
        tvEmpowerProtocal.setText(getClickableSpan());
        //设置超链接可点击
        tvEmpowerProtocal.setMovementMethod(LinkMovementMethod.getInstance());

        btnCancel = (Button) inflate.findViewById(R.id.btn_cancel);
        btnAgree = (Button) inflate.findViewById(R.id.btn_agree);
        //将布局设置给dialog
        dialog.setContentView(inflate);
        //获取当前activity所在窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.CENTER);
        //获取窗体的属性
        //        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //        lp.y = 20;//设置Dialog距离底部的距离
        //        //将属性设置给窗体
        //        dialogWindow.setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();//显示对话框
        btnAgree.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });
    }

    void showNewEmpowerDialog()
    {
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.dialog_new_empower, null);
        btnCancel = (Button) inflate.findViewById(R.id.btn_cancel);
        btnAgree = (Button) inflate.findViewById(R.id.btn_agree);
        //将布局设置给dialog
        dialog.setContentView(inflate);
        //获取当前activity所在窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.CENTER);
        //获取窗体的属性
        //        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //        lp.y = 20;//设置Dialog距离底部的距离
        //        //将属性设置给窗体
        //        dialogWindow.setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();//显示对话框
        btnAgree.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });
    }

    /**
     * 获取可点击的SpannableString
     *
     * @return
     */
    private SpannableString getClickableSpan()
    {
        SpannableString spannableString = new SpannableString("若你点击同意并开始使用本软件，代表您已阅读并接受《免责声明与软件许可协议》");
        //设置下划线文字
        spannableString.setSpan(new UnderlineSpan(), 24, 37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的单击事件
        spannableString.setSpan(new ClickableSpan()
        {
            @Override
            public void onClick(View widget)
            {
                Toast.makeText(MdmPageActivity.this, "《免责声明与软件许可协议》", Toast.LENGTH_SHORT).show();
            }
        }, 24, 37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的前景色
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textBlue)), 24, 37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //        //设置下划线文字
        //        spannableString.setSpan(new UnderlineSpan(), 21, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //        //设置文字的单击事件
        //        spannableString.setSpan(new ClickableSpan() {
        //            @Override
        //            public void onClick(View widget) {
        //                Toast.makeText(MdmPageActivity.this,"隐私政策",Toast.LENGTH_SHORT).show();
        //            }
        //        }, 21, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //        //设置文字的前景色
        //        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 21, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
