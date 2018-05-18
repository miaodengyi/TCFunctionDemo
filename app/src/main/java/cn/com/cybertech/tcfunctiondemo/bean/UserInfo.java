package cn.com.cybertech.tcfunctiondemo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 测试greenDao
 * Created by miao on 2017/6/16.
 */

@Entity
public class UserInfo
{
    @Id
    private long userId;//主键
    private String userName;
    private String sex;
    private String address;
    private int age;
    private String education;
    private String work;
    @Transient
    private String location;//临时存储的字段，不存入数据库
    @Generated(hash = 461967648)
    public UserInfo(long userId, String userName, String sex, String address,
            int age, String education, String work) {
        this.userId = userId;
        this.userName = userName;
        this.sex = sex;
        this.address = address;
        this.age = age;
        this.education = education;
        this.work = work;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEducation() {
        return this.education;
    }
    public void setEducation(String education) {
        this.education = education;
    }
    public String getWork() {
        return this.work;
    }
    public void setWork(String work) {
        this.work = work;
    }
}
