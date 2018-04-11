package com.example.basto.moviesgo;

import android.app.FragmentManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class viewpageradapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> listTitles = new ArrayList<>();

    public viewpageradapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitles.get(position);
    }

    public void AddFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        listTitles.add(title);
    }

}
