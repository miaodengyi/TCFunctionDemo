package cn.com.cybertech.tcfunctiondemo.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import cn.com.cybertech.tcfunctiondemo.App;
import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.net.HttpUrlConnectionUtil;
import cn.com.cybertech.tcfunctiondemo.net.MyOkHttpManager;
import okhttp3.Response;

/**
 * webview下载附件问题
 * Created by miao on 2017/7/4.
 */

public class WebViewActivity extends AppCompatActivity
{
    private WebView webView;
        private static final String url = "https://ui.ptlogin2.qq.com/cgi-bin/login?style=9&appid=522005705&daid=4&s_url=https%3A%2F%2Fw.mail.qq.com%2Fcgi-bin%2Flogin%3Fvt%3Dpassport%26vm%3Dwsk%26delegate_url%3D%26f%3Dxhtml%26target%3D&hln_css=http%3A%2F%2Fmail.qq.com%2Fzh_CN%2Fhtmledition%2Fimages%2Flogo%2Fqqmail%2Fqqmail_logo_default_200h.png&low_login=1&hln_autologin=%E8%AE%B0%E4%BD%8F%E7%99%BB%E5%BD%95%E7%8A%B6%E6%80%81&pt_no_onekey=1";
//    public static final String url = "http://20.65.2.12:4529/coremail/xphone/index.jsp";

    private String cookieStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("WEBVIEW下载附件");
        setContentView(R.layout.activity_webview);
        webView = (WebView) findViewById(R.id.webview);
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
        //支持插件
        //        webSettings.setPluginsEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
        //不使用缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
                CookieManager cookieManager = CookieManager.getInstance();
                cookieStr = cookieManager.getCookie(url);
                Toast.makeText(WebViewActivity.this, "url:" + url + "\n" + "cookie:" + cookieStr, Toast.LENGTH_SHORT).show();
            }

        });
        webView.setDownloadListener(new DownloadListener()
        {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength)
            {
                Log.d("文件地址", url);
                HttpUrlConnectionUtil util = new HttpUrlConnectionUtil();
                util.execute(url, cookieStr);
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        webView.clearCache(true); // 清除资源缓存。请注意，缓存是每个应用程序的，所以这将清除所有使用的WebViews的缓存。false的话，只会清除RAM上的缓存。
        webView.clearHistory(); // 清除历史记录
        webView.clearFormData(); // //从当前关注的表单字段中移除自动填充弹出窗口
        webView.clearSslPreferences(); // 清除存储的SSL首选项表
    }

}