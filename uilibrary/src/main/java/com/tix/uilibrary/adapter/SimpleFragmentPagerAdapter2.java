package com.tix.uilibrary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.tix.uilibrary.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ztcaoon 2018/1/26
 */

public class SimpleFragmentPagerAdapter2<T extends BaseFragment> extends FragmentPagerAdapter {

    private T mCurrentFragment;
    private List<BaseFragment> fragments = new ArrayList<>();

    public SimpleFragmentPagerAdapter2(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments == null || fragments.size() == 0) {
            return null;
        }
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentFragment = (T) object;
        super.setPrimaryItem(container, position, object);
    }

    public T getCurrentFragment() {
        return mCurrentFragment;
    }
}
