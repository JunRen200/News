package comqq.example.asus_pc.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by asus-pc on 2017/5/15.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<String> list;
    private List<Fragment> fragmentList;
    public ViewPagerAdapter(FragmentManager fm, List<String> list, List<Fragment> fragmentLis) {
        super(fm);
        this.list=list;
        this.fragmentList=fragmentLis;
    }
    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentLis) {
        super(fm);
        this.fragmentList=fragmentLis;
    }



    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(list!=null&&list.size()>0) {
            return list.get(position);
        }
        return super.getPageTitle(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
