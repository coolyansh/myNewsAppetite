package com.example.rays.apinewsfeed;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by rays on 8/27/2017.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public CategoryAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return new HomeFragment();
        else if(position==1)
            return new WorldFragment();
        else if (position==2)
            return new CricketFragment();
        else if(position==3)
            return new SportsFragment();
        else if(position==4)
            return new BusinessFragment();
        else if(position==5)
            return new TechFragment();
        else
            return new ScienceFragment();

    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0)
            return mContext.getString(R.string.home);
        else if(position==1)
            return mContext.getString(R.string.world);
        else if (position==2)
            return mContext.getString(R.string.cricket);
        else if(position==3)
            return mContext.getString(R.string.sports);
        else if(position==4)
            return mContext.getString(R.string.business);
        else if(position==5)
            return mContext.getString(R.string.tech);
        else
            return mContext.getString(R.string.science);

    }
}
