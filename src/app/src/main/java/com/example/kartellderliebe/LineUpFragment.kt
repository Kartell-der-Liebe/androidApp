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
        println("Ho")
        val view = inflater.inflate(R.layout.fragment_line_up, container, false)
        // Inflate the layout for this fragment
        val navListener = BottomNavigationView.OnNavigationItemReselectedListener { menuItem ->
            when (menuItem.itemId){
                R.id.lineUpFragment1 ->{
                    println("Hallo 1")
                }
                R.id.lineUpFragment2 ->{
                    println("Hallo 2")
                }
            }
        }
        return view
    }
}