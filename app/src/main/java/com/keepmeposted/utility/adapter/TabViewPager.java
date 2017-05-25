package com.keepmeposted.utility.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.keepmeposted.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dishan on 9/9/2016.
 */

public class TabViewPager extends FragmentPagerAdapter{

    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    public static final int SHOPPING_TAB = 0;
    public static final int TODO_TAB = 1;
    public static final int BUDGET_TAB = 2;

    private Context context;

    public TabViewPager(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
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
        switch (position) {
            case SHOPPING_TAB:
                return "Shopping";
            case TODO_TAB:
                return "ToDo";
            case BUDGET_TAB:
                return "Budget";
            default:
                return "";
        }
    }
}
