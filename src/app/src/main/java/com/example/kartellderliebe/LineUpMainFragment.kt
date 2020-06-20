package com.example.kartellderliebe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration
import com.example.kartellderliebe.databinding.FragmentActsMainBinding
import com.example.myapplication.TabsAdapterLineUp
import com.google.android.material.tabs.TabLayout

class LineUpMainFragment : Fragment(R.layout.fragment_acts_main) {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentActsMainBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabLayout
        tabLayout.addTab(tabLayout.newTab().setText("Acts"))
        tabLayout.addTab(tabLayout.newTab().setText("Time Table"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val viewPager = binding.viewPager
        val tabsAdapter = activity?.supportFragmentManager?.let {
            TabsAdapterLineUp(
                it,
                tabLayout.tabCount
            )
        }
        viewPager.adapter = tabsAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}

            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
        })
        return binding.root
    }


}