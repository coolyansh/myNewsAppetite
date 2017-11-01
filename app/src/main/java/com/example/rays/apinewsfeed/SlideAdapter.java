package com.example.rays.apinewsfeed;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by rays on 8/27/2017.
 */

public class SlideAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SlideAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return new Slide_one();
        else if(position==1)
            return new Slide_two();
        else if (position==2)
            return new Slide_three();
        else if (position==3)
            return new Slide_four();
        else if (position==4)
            return new Slide_five();
        else
            return new Slide_six();
    }

    @Override
    public int getCount() {
        return 6;
    }
}
