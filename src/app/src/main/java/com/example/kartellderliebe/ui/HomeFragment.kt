package com.example.kartellderliebe.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.kartellderliebe.R
import com.example.kartellderliebe.databinding.HomeBinding

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        val binding = HomeBinding.inflate(inflater, container, false)
        binding.TextViewLineUp.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_actsMainFragment)
        }
        binding.TextViewFood.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_foodMainFragment)
        }
        binding.TextViewNews.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_festivalNewsFragment)
        }
        binding.TextViewInfos.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_festivalInfosMainFragment)
        }
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