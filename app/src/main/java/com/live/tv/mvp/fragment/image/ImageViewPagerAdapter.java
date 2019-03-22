package com.live.tv.mvp.fragment.image;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by taoh on 2017/11/16.
 */

public class ImageViewPagerAdapter extends FragmentPagerAdapter {


    private static final String IMAGE_URL = "image";

    List<String> mDatas;
    int mposition;

    public ImageViewPagerAdapter(FragmentManager fm, List data, int position) {
        super(fm);
        this.mDatas = data;
        this.mposition = position;
    }

    @Override
    public Fragment getItem(int position) {
        String url = mDatas.get(position);
        Fragment fragment = ImageFragment.newInstance(url);
        return fragment;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }
}
