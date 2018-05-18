package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cn.com.cybertech.tcfunctiondemo.R;
import cn.com.cybertech.tcfunctiondemo.bean.UserInfo;
import cn.com.cybertech.tcfunctiondemo.gen.DaoMaster;
import cn.com.cybertech.tcfunctiondemo.gen.DaoSession;
import cn.com.cybertech.tcfunctiondemo.gen.UserInfoDao;

/**
 * 测试greendao
 * Created by miao on 2017/6/16.
 */

public class GreenDaoActivity extends AppCompatActivity
{
    private UserInfoDao userDao;
    private TextView tvQueryDatabase;
    private Button btnQueryDatabase;

    private StringBuffer queryBuffer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("GREENDAO");
        setContentView(R.layout.activity_greendao);
        //初始化数据库
        initDateBase();
        //插入数据库
        insertData();
        tvQueryDatabase = (TextView) findViewById(R.id.tv_query_database);
        btnQueryDatabase = (Button) findViewById(R.id.btn_query_database);
        btnQueryDatabase.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                queryBuffer = null;
                queryBuffer = new StringBuffer();
                List<UserInfo> list = queryDataBase();
                if (list!=null)
                {
                    if (list.size()>0)
                    {
                        for (UserInfo userInfo : list)
                        {
                            queryBuffer.append(userInfo.getUserId()+"---");
                            queryBuffer.append(userInfo.getAddress()+"---");
                            queryBuffer.append(userInfo.getAge()+"---");
                            queryBuffer.append(userInfo.getEducation()+"---");
                            queryBuffer.append(userInfo.getSex()+"---");
                            queryBuffer.append(userInfo.getUserName()+"---");
                            queryBuffer.append(userInfo.getWork()+"---");
                            queryBuffer.append("\n\n");
                        }
                        tvQueryDatabase.setText(queryBuffer);
                    }
                }
            }
        });
    }

    /**
     * "user-db"是我们自定的数据库名字
     * 我们之前创建了一个Entity叫做UserInfo,所以greenDAO自定帮我们生成的UserInfoDao,拿到了这个UserInfoDao，我们就可以操作UserInfo这张表了
     * 一个DaoMaster就代表着一个数据库的连接
     * DaoSession可以让我们使用一些Entity的基本操作和获取Dao操作类，DaoSession可以创建多个，每一个都是属于同一个数据库连接的
     */
    private void initDateBase()
    {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(GreenDaoActivity.this, "user-db", null);
        DaoMaster               daoMaster     = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession              daoSession    = daoMaster.newSession();
        userDao = daoSession.getUserInfoDao();
    }

    private void insertData()
    {
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUserId(1);
        userInfo1.setAddress("苏州");
        userInfo1.setUserName("姑苏");
        userInfo1.setAge(2000);
        userInfo1.setSex("女");
        userInfo1.setEducation("大学");
        userInfo1.setWork("昆曲");

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setUserId(2);
        userInfo2.setAddress("扬州");
        userInfo2.setUserName("扬州");
        userInfo2.setAge(1000);
        userInfo2.setSex("男");
        userInfo2.setEducation("中庸");
        userInfo2.setWork("扬剧");

        UserInfo userInfo3 = new UserInfo();
        userInfo3.setUserId(3);
        userInfo3.setAddress("荆州");
        userInfo3.setUserName("荆州");
        userInfo3.setAge(1500);
        userInfo3.setSex("男");
        userInfo3.setEducation("论语");
        userInfo3.setWork("关羽");
        if (userDao!=null)
        {
            try
            {
                userDao.insertOrReplace(userInfo1);
                userDao.insertOrReplace(userInfo2);
                userDao.insertOrReplace(userInfo3);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    //查询数据库
    private List<UserInfo> queryDataBase()
    {
        List<UserInfo> list = userDao.queryBuilder()
//                .where(UserInfoDao.Properties.UserId.notEq(9999)) //判断，id不等于999的
//                .orderAsc(UserInfoDao.Properties.UserId)
//                .limit(10)  //10条
//                .build()
                .list();
        return list;
    }

    private void updateDatabase()
    {

    }

}
