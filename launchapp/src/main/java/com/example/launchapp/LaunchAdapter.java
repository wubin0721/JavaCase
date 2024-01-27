package com.example.launchapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LaunchAdapter extends FragmentPagerAdapter {

    private final int[] mImageArray;

    public LaunchAdapter(@NonNull FragmentManager fm,int[] imageArray) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mImageArray = imageArray;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        return LanunchFragment.newInstance(mImageArray.length,position,mImageArray[position]);
    }

    @Override
    public int getCount() {
        return mImageArray.length;
    }
}
