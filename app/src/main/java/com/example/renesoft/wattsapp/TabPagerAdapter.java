package com.example.renesoft.wattsapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by android3 on 9/1/16.
 */
public class TabPagerAdapter   extends FragmentPagerAdapter

    {

        int tabCount;

        public TabPagerAdapter(FragmentManager fm, int numOfTabs)
        {
            super(fm);
            this.tabCount = numOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    BlankFragment tab1 = new BlankFragment();

                    return tab1;
                case 1:
                    Activities tab2 = new Activities();
                    return tab2;
               case 2:
                    WeekoverviewFragment tab3 = new WeekoverviewFragment();
                    return tab3;
             //   case 3:
                 //   Tab4Fragment tab4 = new Tab4Fragment();
                 //   return tab4;
             //    */
                default:
                    return null;


            }
        }

        @Override
        public int getCount() {
            return tabCount;
        }
}
