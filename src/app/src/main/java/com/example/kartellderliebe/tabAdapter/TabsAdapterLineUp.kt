package com.example.kartellderliebe.tabAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.kartellderliebe.ui.lineUp.*

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