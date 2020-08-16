package com.example.app;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    //private int numOfTabs;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.Mon, R.string.Tue,R.string.Wed,
                        R.string.Thur,R.string.Fri};
    private final Context mContext;

    public PagerAdapter(Context context,@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        //this.numOfTabs = numOfTabs;
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Monday();
            case 1:
                return  new Tuesday();
            case 2:
                return  new Wednesday();
            case 3:
                return new Thursday();
            case 4:
                return new Friday();
            default:
                return null;
        }
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 5;
    }
}
