package com.example.kartellderliebe.tabAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.kartellderliebe.ui.food.*

class TabsAdapterFood(fm: FragmentManager, var numOfTabs: Int) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FoodTruckFragment()
            }
            1 -> {
                FoodSharingFragment()
            }
            else -> {
                DorfladenFragment()
            }
        }
    }

    override fun getCount() = numOfTabs


}