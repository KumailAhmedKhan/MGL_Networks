package com.app.sample.social.MGLNetworks.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.sample.social.MGLNetworks.Fragment.HomeFragment;
import com.app.sample.social.MGLNetworks.Fragment.MembershipFragment;

import java.util.ArrayList;
import java.util.List;

public class PageFragmentAdapter extends FragmentPagerAdapter
{
    private final List<Fragment> mFragments ;
    private final List<String> mFragmentTitles = new ArrayList<>();
    private Bundle bundle;
    public PageFragmentAdapter(FragmentManager fm)
    {
        super(fm);
        this.mFragments = new ArrayList<>();

    }

    public void addFragment(Fragment fragment, String title) {
       this.mFragments.add(fragment);
        this.mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position)
    {

        if (position == 0)
        {
            Fragment fragment = new HomeFragment();
            fragment.setArguments(bundle);
            return fragment;
        }  else{
            Fragment fragment = new MembershipFragment();
            fragment.setArguments(bundle);
            return fragment;
        }

        }


    @Override
    public int getCount() {
        return this.mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    public String getTitle(int position){
        return this.mFragmentTitles.get(position);
    }
}