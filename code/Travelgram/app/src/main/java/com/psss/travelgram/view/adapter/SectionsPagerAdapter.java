package com.psss.travelgram.view.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.psss.travelgram.R;
import com.psss.travelgram.view.fragment.FollowingJournalFragment;
import com.psss.travelgram.view.fragment.JournalFragment;
import com.psss.travelgram.view.fragment.NotificationsFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.MyMemories, R.string.Memories, R.string.Reviews, R.string.Guides};
    private final Context mContext;
    private String countryName;

    public SectionsPagerAdapter(Context context, FragmentManager fm, String countryName) {
        super(fm);
        this.countryName = countryName;
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        switch(position){
            case 0:
                return JournalFragment.newInstance(countryName);
            case 1:
                return FollowingJournalFragment.newInstance(countryName);
            case 2:
                return new NotificationsFragment();
            case 3:
                return new NotificationsFragment();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }
}