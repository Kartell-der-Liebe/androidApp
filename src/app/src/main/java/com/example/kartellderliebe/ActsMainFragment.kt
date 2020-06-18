package com.example.kartellderliebe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.adapters.ViewBindingAdapter
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.viewbinding.ViewBinding
import com.example.kartellderliebe.databinding.FragmentActsMainBinding

class ActsMainFragment : Fragment(R.layout.fragment_acts_main) {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentActsMainBinding.inflate(inflater, container, false)
        drawerLayout = binding.drawerLayoutActs
        val navController = this.findNavController()
        NavigationUI.setupWithNavController(binding.ActsNavView, navController)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        // Inflate the layout for this fragment
        return binding.root
    }


}