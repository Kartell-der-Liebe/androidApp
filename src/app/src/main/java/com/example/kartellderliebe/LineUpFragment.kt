package com.example.kartellderliebe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView

class LineUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val navListener = BottomNavigationView.OnNavigationItemReselectedListener { menuItem ->
            when (menuItem.itemId){
                R.id.bb_menu_arac ->{
                    val fragment = LineUpFragment()
                }
            }
        }
        return inflater.inflate(R.layout.fragment_line_up, container, false)
    }
}