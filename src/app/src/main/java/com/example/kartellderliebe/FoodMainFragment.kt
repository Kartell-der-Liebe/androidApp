package com.example.kartellderliebe

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.kartellderliebe.databinding.FragmentFoodMainBinding
import com.google.android.material.tabs.TabLayout

class FoodMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val binding = FragmentFoodMainBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabLayout
        tabLayout.addTab(tabLayout.newTab().setText("Foodtrucks"))
        tabLayout.addTab(tabLayout.newTab().setText("Food Sharing"))
        tabLayout.addTab(tabLayout.newTab().setText("Dorfladen"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val viewPager = binding.viewPager
        val tabsAdapter = activity?.supportFragmentManager?.let {
            TabsAdapterFood(
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