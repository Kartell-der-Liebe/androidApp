package com.example.kartellderliebe.ui.info

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.kartellderliebe.R
import com.example.kartellderliebe.tabAdapter.TabsAdapterFestivalInfos
import com.example.kartellderliebe.databinding.FragmentFestivalInfosMainBinding
import com.google.android.material.tabs.TabLayout

class FestivalInfosMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val binding = FragmentFestivalInfosMainBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabLayout
        tabLayout.addTab(tabLayout.newTab().setText("Allgemein"))
        tabLayout.addTab(tabLayout.newTab().setText("Anreise"))
        tabLayout.addTab(tabLayout.newTab().setText("Camping"))
        tabLayout.addTab(tabLayout.newTab().setText("Unter 18"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val viewPager = binding.viewPager
        val tabsAdapter = activity?.supportFragmentManager?.let {
            TabsAdapterFestivalInfos(
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

}