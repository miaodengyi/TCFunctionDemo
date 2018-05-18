package cn.com.cybertech.tcfunctiondemo.view;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * 自定义拍照插件，实现类似签到效果
 * Created by miao on 2017/7/18.
 */

public class MyCamera extends RelativeLayout
{
    private Context  context;
    private TextView tvNum;
    private TextView tvCamera;
    private Activity activity;

    //以时间来命名文件
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //保存照片路径的集合
    private ArrayList<String> photoPathList;

    /**
     * gradview显示图片
     */
    private GridView       gridView;

    public MyCamera(Context context)
    {
        super(context);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.my_camera, this, true);
        tvNum = (TextView) findViewById(R.id.tv_num);
        tvCamera = (TextView) findViewById(R.id.tv_camera);
        photoPathList = new ArrayList<>();
        /**
         * 使用gradview显示
         */
        gridView = (GridView) findViewById(R.id.gridview);
//        horizontalScrollView.setHorizontalScrollBarEnabled(false);//隐藏滚动条
        GradviewAdapter adapter = new GradviewAdapter(context, photoPathList);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        /**
         * 使用recyclerview方式显示
         */
        //        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_show);
        //        //实现横向滚动效果
        //        LinearLayoutManager manager = new LinearLayoutManager(context);
        //        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //        recyclerView.setLayoutManager(manager);
        //
        //        PicturesAdapter adapter = new PicturesAdapter(context, photoPathList);
        //        recyclerView.setAdapter(adapter);
        //        adapter.notifyDataSetChanged();
    }

    public void setOnCameraClick(final Activity activity)
    {
        this.activity = activity;
        tvCamera.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                takePhoto(activity);
            }
        });
    }

    //拍照方法
    private void takePhoto(Activity activity)
    {
        //激活系统摄像机进行拍照
        Intent intent = new Intent();
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        intent.addCategory("android.intent.category.DEFAULT");
        //保存照片到指定路径
        String photoPath = context.getExternalFilesDir(null).getPath() + "/" + getDetailTime() + ".jpg";
        //把路劲添加到list
        photoPathList.add(photoPath);
        //保存文件
        File file = new File(photoPath);
        if (Build.VERSION.SDK_INT < 24)
        {
            //从文件中创建uri
            Uri uri = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        else
        {
            //兼容android7.0 使用共享文件的形式
            ContentValues values = new ContentValues(1);
            values.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
            Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        //加这句话，不然可能报错
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        try
        {
            activity.startActivityForResult(intent, 0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //获得更细致的时分秒
    public String getDetailTime()
    {
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * gradview的适配器
     */
    private class GradviewAdapter extends BaseAdapter
    {
        private Context               context;
        private ArrayList<String>     list;
        private BitmapFactory.Options options;

        GradviewAdapter(Context context, ArrayList<String> photoList)
        {
            this.context = context;
            this.list = photoList;
            options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inSampleSize = 4;
        }

        @Override
        public int getCount()
        {
            tvNum.setText(String.valueOf(list.size()));
            //设置水平横向滑动的参数
            //        // item宽度
            int itemWidth = dip2px(context, 100);
            //        // item之间的间隔
            int itemPaddingH = dip2px(context, 1);
            //默认10张
            int size = photoPathList.size();
            //        // 计算GridView宽度
            int gridviewWidth = size * (itemWidth + itemPaddingH);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
            gridView.setLayoutParams(params);
            gridView.setColumnWidth(itemWidth);
            gridView.setHorizontalSpacing(itemPaddingH);
            gridView.setStretchMode(GridView.NO_STRETCH);
            gridView.setNumColumns(size);
            return list.size();
        }

        @Override
        public Object getItem(int position)
        {
            return list.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            GradViewHolder viewHolder = null;
            if (convertView == null)
            {
                viewHolder = new GradViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_recycler_grid, null);
                viewHolder.iv_pic = (ImageView) convertView.findViewById(R.id.item_img);
                viewHolder.iv_close = (ImageView) convertView.findViewById(R.id.item_close);
                convertView.setTag(viewHolder);
            }
            else
            {
                viewHolder = (GradViewHolder) convertView.getTag();
            }
            final String path = list.get(position);
            File         file = new File(path);
            if (file.exists())
            {
                Bitmap bitmap = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(path, options), 500, 400);
                viewHolder.iv_pic.setImageBitmap(bitmap);
            }
            viewHolder.iv_pic.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(context, ShowBigImageActivity.class);
                    intent.putExtra("path", path);
                    context.startActivity(intent);
                }
            });
            viewHolder.iv_close.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    list.remove(position);
                    photoPathList.remove(position);
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @param context  上下文
     * @param dpValue  dp值
     * @return px值
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * gradview的viewholder
     */
    class GradViewHolder
    {
        ImageView iv_pic;
        ImageView iv_close;
    }

    /**
     * recyclerview的适配器
     */
    //recyclerview的adapter部分
    private class PicturesAdapter extends RecyclerView.Adapter<MyViewHolder>
    {
        Context           mContext;
        ArrayList<String> list;
        LayoutInflater    mInflater;

        BitmapFactory.Options options;

        PicturesAdapter(Context context, ArrayList<String> list)
        {
            this.mContext = context;
            this.list = list;
            mInflater = LayoutInflater.from(mContext);
            options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inSampleSize = 4;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler_grid, parent, false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position)
        {
            final String path = list.get(position);
            File         file = new File(path);
            if (file.exists())
            {
                Bitmap bitmap = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(path, options), 500, 400);
                holder.iv_pic.setImageBitmap(bitmap);
            }
            holder.itemView.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(mContext, ShowBigImageActivity.class);
                    intent.putExtra("path", path);
                    mContext.startActivity(intent);
                }
            });
            holder.iv_close.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount()
        {
            tvNum.setText(String.valueOf(list.size()));
            return list.size();
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_pic;
        ImageView iv_close;
        View      itemView;

        MyViewHolder(View itemView)
        {
            super(itemView);
            this.itemView = itemView;
            iv_pic = (ImageView) itemView.findViewById(R.id.item_img);
            iv_close = (ImageView) itemView.findViewById(R.id.item_close);
        }
    }
}
