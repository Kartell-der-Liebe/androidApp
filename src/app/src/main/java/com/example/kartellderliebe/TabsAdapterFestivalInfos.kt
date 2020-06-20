package com.example.kartellderliebe

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabsAdapterFestivalInfos(fm: FragmentManager, var numOfTabs: Int) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                AllgemeinInfosFragment()
            }
            1 -> {
                AnreiseFragment()
            }
            2 -> {
                CampingFragment()
            }
            else -> {
                Unter18Fragment()
            }
        }
    }

    override fun getCount() = numOfTabs


}