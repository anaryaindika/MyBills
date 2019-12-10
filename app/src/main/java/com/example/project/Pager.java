package com.example.project;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class Pager extends FragmentStatePagerAdapter {
    //integer to count number of tabs
    int tabCount;
    Context context;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount , Context context) {
        super(fm);
        //Initializing tab count
        this.tabCount = tabCount;
        this.context = context;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment(context);
                return fragment;
            case 1:
                SettingFragment setting = new SettingFragment(context);
                return setting;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
