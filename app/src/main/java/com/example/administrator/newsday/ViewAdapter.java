package com.example.administrator.newsday;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;
/**
 * Created by Administrator on 2016/8/31.
 */
public class ViewAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> title;
    public ViewAdapter(FragmentManager fm, List<Fragment> list, List<String> title) {
        super(fm);
        this.list = list;
        this.title = title;
    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
