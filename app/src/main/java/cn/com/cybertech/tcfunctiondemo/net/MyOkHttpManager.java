package cn.com.cybertech.tcfunctiondemo.net;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 用okhttp实现get，post，文件的上传和下载
 * Created by miao on 2017/7/5.
 */

public class MyOkHttpManager
{
    /**
     * 同步的get请求
     *
     * @param url
     * @return
     * @throws IOException OKHttp请求成功的返回值为Response ，
     *                     一般可以通过response.body().string()获取返回的字符串；
     *                     也可以通过response.body().bytes()获取返回的二进制字节数组；
     *                     也可以通过response.body().byteStream()获取返回的inputStream。
     */
    public static String getSync(String url) throws IOException
    {
        //创建okHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个request
        Request request = new Request.Builder().url(url).build();
        Call    call    = okHttpClient.newCall(request);
        //返回值为response
        Response response = call.execute();
        //将response转化为String
        String responseStr = response.body().string();
        return responseStr;
    }

    /**
     * 异步的get请求
     *
     * @param url
     * @return
     * @throws IOException OKHttp请求成功的返回值为Response ，
     *                     一般可以通过response.body().string()获取返回的字符串；
     *                     也可以通过response.body().bytes()获取返回的二进制字节数组；
     *                     也可以通过response.body().byteStream()获取返回的inputStream。
     *                     值得注意的是它的返回值是在子线程中而不是UI线程，想要在UI线程中使用，还需要使用handler等
     */
    public static void getAync(String url) throws IOException
    {
        //创建okHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个request
        Request request = new Request.Builder().url(url).build();
        Call    call    = okHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback()
        {
            //请求失败的回调
            @Override
            public void onFailure(Call call, IOException e)
            {
                e.printStackTrace();
            }

            //请求成功的回调
            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                String responseStr = response.body().string();
                //此处加上handler
            }
        });
    }

    /**
     * 同步的post请求
     *
     * @param url
     * @param params 大家知道，发送post请求时，参数是包含在请求体中的，
     *               所以我们去构造RequestBody，最后完成我们Request的构造。
     */
    public static String postSync(String url, Map<String, String> params) throws IOException
    {
        //requestBody
        RequestBody requestBody;
        if (params == null)
        {
            params = new HashMap<>();
        }
        //创建okhttpClient对象
        OkHttpClient     okHttpClient = new OkHttpClient();
        FormBody.Builder builder      = new FormBody.Builder();
        /**
         * 在这对添加的参数进行遍历
         */
        for (Map.Entry<String, String> map : params.entrySet())
        {
            String key = map.getKey();
            String value;
            /**
             * 判断值是否是空的
             */
            if (map.getValue() == null)
            {
                value = "";
            }
            else
            {
                value = map.getValue();
            }
            /**
             * 把key和value添加到formBody中
             */
            builder.add(key, value);
        }
        requestBody = builder.build();
        //创建一个Request
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Call    call    = okHttpClient.newCall(request);
        //返回值为response
        Response response = call.execute();
        //response转化为string
        String responseStr = response.body().string();
        return responseStr;
    }

    /**
     * 异步的post请求
     *
     * @param url
     * @param params 大家知道，发送post请求时，参数是包含在请求体中的，
     *               所以我们去构造RequestBody，最后完成我们Request的构造。
     *               以上是基于http的异步post请求，上面的例子添加的请求参数是Map，当然也可以添加其他类型的参数，如：
     *               post传参（byte）
     *               RequestBody byteBody = RequestBody.create(MediaType.parse("application/octet-stream; charset=utf-8"), mapToBytes(params));
     *               mapToBytes(params)方法是将params转化成byte
     */
    public static void postAync(String url, Map<String, String> params) throws IOException
    {
        //requestBody
        RequestBody requestBody;
        if (params == null)
        {
            params = new HashMap<>();
        }
        //创建okhttpClient对象
        OkHttpClient     okHttpClient = new OkHttpClient();
        FormBody.Builder builder      = new FormBody.Builder();
        /**
         * 在这对添加的参数进行遍历
         */
        for (Map.Entry<String, String> map : params.entrySet())
        {
            String key = map.getKey();
            String value;
            /**
             * 判断值是否是空的
             */
            if (map.getValue() == null)
            {
                value = "";
            }
            else
            {
                value = map.getValue();
            }
            /**
             * 把key和value添加到formBody中
             */
            builder.add(key, value);
        }
        requestBody = builder.build();
        //创建一个Request
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Call    call    = okHttpClient.newCall(request);
        call.enqueue(new Callback()
        {
            //请求失败的回调
            @Override
            public void onFailure(Call call, IOException e)
            {
                e.printStackTrace();
            }

            //请求成功的回调
            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                //将response转化为string
                String responseStr = response.body().string();
                //此处添加handler
            }
        });
    }

    /**
     * 基于http的文件上传
     * 通过addFormDataPart添加多个上传的文件
     */
    public void upLoadMultiFile(String url)
    {
        //创建okHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        File         file         = new File(Environment.getExternalStorageDirectory(), "pic.png");
        RequestBody  fileBody     = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "pic.png", fileBody)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback()
        {
            //提交失败
            @Override
            public void onFailure(Call call, IOException e)
            {
                e.printStackTrace();
            }

            //提交成功
            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                String responseStr = response.body().string();
                //此处添加handler刷新UI
            }
        });
    }

    public DownFileListener listener;
    /**
     * 基于http的文件下载
     * @param url 下载地址
     */
    public void downLoadFile(String url, final DownFileListener listener)
    {
        this.listener = listener;
        //cookie自动保存
        final ConcurrentHashMap<String,List<Cookie>> cookieStore = new ConcurrentHashMap<>();

        //创建okhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJar()
        {
            //这里可以做cookie传递，保存等操作
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies)
            {
                //可以做保存cookies操作
                cookieStore.put(url.host(),cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url)
            {
                //加载新的cookies
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        }).build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                e.printStackTrace();
                listener.Error(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                //下载成功后执行的操作
                listener.Success(response);
            }
        });
    }

    public interface DownFileListener{
        public void Error(Exception e);
        public void Success(Response response);
    }

}
