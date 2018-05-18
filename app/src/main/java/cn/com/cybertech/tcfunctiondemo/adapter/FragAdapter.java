package cn.com.cybertech.tcfunctiondemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * viewPage的adapter
 * Created by miao on 2017/9/5.
 */

public class FragAdapter extends FragmentPagerAdapter {

    private List<String> titles;
    private List<Fragment> mFragments;

    public FragAdapter(FragmentManager fm,List<String> titles, List<Fragment> list) {
        super(fm);
        this.titles = titles;
        mFragments = list;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}

