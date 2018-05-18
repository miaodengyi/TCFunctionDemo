package cn.com.cybertech.tcfunctiondemo.net;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 陆登宇 on 2016/10/27.
 */
public class HttpUtils
{
    private static final String TAG      = HttpUtils.class.getSimpleName();
    private static final int    TIME_OUT = 10 * 10000000;
    private static final String CHARSET  = "utf-8";
    private static final String PREFIX   = "--";
    private static final String CRLF     = "\r\n";

    public static void downloadFile(Context context, String url, final String path,String mimeType, final FileDownloadListener listener)
    {
        if (context == null || TextUtils.isEmpty(url) || TextUtils.isEmpty(path) || listener == null)
        {
            return;
        }
        Uri                     uri = Uri.parse(url);
        DownloadManager.Request req = new DownloadManager.Request(uri);

        // 通过setAllowedNetworkTypes方法可以设置允许在何种网络下下载，
        // 也可以使用setAllowedOverRoaming方法，它更加灵活
        //        req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);

        req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        //        req.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        req.setDestinationUri(Uri.fromFile(new File(path)));
        req.setMimeType(mimeType);
        final DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        final long            downloadId      = downloadManager.enqueue(req);

        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        final BroadcastReceiver receiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

                if (downloadId == reference && reference != -1)
                {
                    Uri downloadFileUri = downloadManager.getUriForDownloadedFile(reference);
                    if (downloadFileUri == null)
                    {
                        listener.onError();
                        Log.e("downloadFile", "downloadFile error");
                        return;
                    }

                    listener.onFinish(path);
                }
            }
        };
        context.registerReceiver(receiver, filter);
    }

    public static void uploadFile(String host, File file, Map<String, String> params, FileUploadListener listener)
    {
        //边界标识 随机生成 String PREFIX = "--" , CRLF = "\r\n";
        String BOUNDARY = UUID.randomUUID().toString();
        //内容类型
        String CONTENT_TYPE = "multipart/form-data";
        try
        {
            URL               url  = new URL(host);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", CHARSET);
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
            //允许输入流
            conn.setDoInput(true);
            //允许输出流
            conn.setDoOutput(true);
            //不允许使用缓存
            conn.setUseCaches(false);
            if (file != null)
            {
                OutputStream     outputSteam = conn.getOutputStream();
                DataOutputStream dos         = new DataOutputStream(outputSteam);
                StringBuilder    startData   = new StringBuilder();
                startData.append(CRLF);
                if (params != null)
                {
                    //根据格式，开始拼接文本参数
                    for (Map.Entry<String, String> entry : params.entrySet())
                    {
                        startData.append(PREFIX).append(BOUNDARY).append(CRLF);//分界符
                        startData.append("Content-Disposition: form-data; name=\"").append(entry.getKey()).append("\"").append(CRLF);
                        startData.append("Content-Type: text/plain; charset=" + CHARSET + CRLF);
                        startData.append("Content-Transfer-Encoding: 8bit" + CRLF);
                        startData.append(CRLF);
                        startData.append(entry.getValue());
                        startData.append(CRLF);//换行！
                    }
                }

                //开始拼接文件参数
                startData.append(PREFIX);
                startData.append(BOUNDARY);
                startData.append(CRLF);
                /**
                 * 这里重点注意：
                 * name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后缀名的 比如:abc.png
                 */
                startData.append("Content-Disposition: form-data; name=\"filename\"; filename=\"").append(file.getName()).append("\"").append(CRLF);
                startData.append("Content-Type: application/octet-stream; charset=" + CHARSET + CRLF);
                startData.append(CRLF);
                //写入文件数据
                dos.write(startData.toString().getBytes());
                InputStream is         = new FileInputStream(file);
                byte[]      bytes      = new byte[1024];
                long        totalBytes = file.length();
                long        curBytes   = 0;
                Log.i(TAG, "total=" + totalBytes);
                int len;
                while ((len = is.read(bytes)) != -1)
                {
                    curBytes += len;
                    dos.write(bytes, 0, len);
                    listener.onProgress(curBytes, 1.0d * curBytes / totalBytes);
                }
                is.close();

                //一定还有换行
                dos.write(CRLF.getBytes());
                byte[] endData = (PREFIX + BOUNDARY + PREFIX + CRLF).getBytes();
                dos.write(endData);
                dos.flush();
                /**
                 * 获取响应码 200=成功
                 * 当响应成功，获取响应的流
                 */
                int code = conn.getResponseCode();
                startData.setLength(0);
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String         line;
                while ((line = br.readLine()) != null)
                {
                    startData.append(line);
                }
                listener.onFinish(code, startData.toString(), conn.getHeaderFields());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public interface FileUploadListener
    {
        void onProgress(long pro, double precent);

        void onFinish(int code, String res, Map<String, List<String>> headers);
    }

    public interface FileDownloadListener
    {
        void onError();

        void onFinish(String fileName);
    }
}