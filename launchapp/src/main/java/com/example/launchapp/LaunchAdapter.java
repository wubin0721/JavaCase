package com.example.launchapp;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class LaunchAdapter extends FragmentStateAdapter {

    private final int[] mImageArray;

    private Lifecycle lifecycle;


    public LaunchAdapter(@NonNull FragmentManager fm,@NonNull Lifecycle lifecycle,int[] imageArray) {
        super(fm, lifecycle);
        this.mImageArray = imageArray;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return LanunchFragment.newInstance(mImageArray.length,position,mImageArray[position]);
    }

    @Override
    public int getItemCount() {
        return mImageArray.length;
    }
}
