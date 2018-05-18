package cn.com.cybertech.tcfunctiondemo.bean;

/**
 * Created by 20201002 on 2016/8/31.
 */
public class FileInfo
{
    private boolean isDirectory;
    private String  name;
    private long    length;
    private String  lastModified;

    public boolean isDirectory()
    {
        return isDirectory;
    }

    public void setDirectory(boolean isDirectory)
    {
        this.isDirectory = isDirectory;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getLength()
    {
        return length;
    }

    public void setLength(long length)
    {
        this.length = length;
    }

    public String getLastModified()
    {
        return lastModified;
    }

    public void setLastModified(String lastModified)
    {
        this.lastModified = lastModified;
    }
}
