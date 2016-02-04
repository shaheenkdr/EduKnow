package com.mobloo.eduknow;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class TabsPagerAdapter extends FragmentPagerAdapter
{
    int mNumOfTabs;

    public TabsPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;

    }
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FeedsFragment tab1 = new FeedsFragment();
                return tab1;
            case 1:
                StreamFragment tab2 = new StreamFragment();
                return tab2;

            case 2:
                ReceiptFragment tab3 = new ReceiptFragment();
                return tab3;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
