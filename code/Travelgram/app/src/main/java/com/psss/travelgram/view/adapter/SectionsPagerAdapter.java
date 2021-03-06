package com.psss.travelgram.view.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.psss.travelgram.R;
import com.psss.travelgram.view.fragment.JournalFragment;
import com.psss.travelgram.view.fragment.PlaceholderFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{
            R.string.MyMemories,
            R.string.Memories,
            R.string.Reviews,
            R.string.Guides};
    private final Context mContext;
    private String countryName;


    // costruttore
    public SectionsPagerAdapter(Context context, FragmentManager fm, String countryName) {
        super(fm);
        this.countryName = countryName;
        mContext = context;
    }


    // getItem is called to instantiate the fragment for the given page.
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return JournalFragment.newInstance(countryName,false);
            case 1:
                return JournalFragment.newInstance(countryName,true);
            case 2:
                return PlaceholderFragment.newInstance("Future Updates - Reviews", R.drawable.ic_star_50);
            case 3:
                return PlaceholderFragment.newInstance("Future Updates - Guides", R.drawable.ic_guide_50);
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