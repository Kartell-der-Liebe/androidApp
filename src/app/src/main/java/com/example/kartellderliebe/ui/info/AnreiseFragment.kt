package com.example.kartellderliebe.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kartellderliebe.R
import com.example.kartellderliebe.databinding.FragmentAnreiseBinding

class AnreiseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAnreiseBinding.inflate(inflater, container, false)
        val expTv1 = binding.expandTextView1
        expTv1.text = getString(R.string.anreiseInformationenText1)

        val myTextView = binding.myTextView
        myTextView.text = getString(R.string.anreiseInformationenText2)
        return binding.root
    }
}