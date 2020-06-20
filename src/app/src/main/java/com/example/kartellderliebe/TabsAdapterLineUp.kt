package com.example.kartellderliebe

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabsAdapterLineUp(fm : FragmentManager, var numOfTabs : Int) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ActsFragment()
            }
            else-> {
                TimeTableFragment()
            }
        }
    }

    override fun getCount() = numOfTabs


}