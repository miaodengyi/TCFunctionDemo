package cn.com.cybertech.tcfunctiondemo.bean;

import java.util.ArrayList;

/**
 * 个人信息
 * Created by miao on 2017/7/20.
 */

public class PersonBean
{
    private int id;//身份证号码
    private String name;//姓名
    private String sex;//性别
    private String age;//年龄
    private ArrayList<String> tags;//标签

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public ArrayList<String> getTags()
    {
        return tags;
    }

    public void setTags(ArrayList<String> tags)
    {
        this.tags = tags;
    }
}
