package com.example.kartellderliebe.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kartellderliebe.databinding.FragmentAllgemeinInfosBinding


class AllgemeinInfosFragment : Fragment() {


    private val FLAG_HIDE_UNHIDE = 0
    private val totalAmount = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAllgemeinInfosBinding.inflate(inflater, container, false)

        return binding.root
    }
}