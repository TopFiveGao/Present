package com.administrator.givegiftmodel.adpters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> titleList;
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titleList!=null){
            return titleList.get(position);
        }else{
            return super.getPageTitle(position);
        }
    }



    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }


    /**
     * get the number of pager
     * @return
     */
    @Override
    public int getCount() {
        if (fragmentList!=null){
            return fragmentList.size();
        }else {
            return 0;
        }
    }

    /**
     * get the fragmentList
     * @return
     */
    public List<Fragment> getFragmentList() {
        return fragmentList;
    }

    /**
     * set the fragmentList
     * @param fragmentList
     */
    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }
}
