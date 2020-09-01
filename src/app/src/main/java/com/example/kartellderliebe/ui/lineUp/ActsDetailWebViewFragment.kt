package com.example.kartellderliebe.ui.lineUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kartellderliebe.databinding.FragmentActsDetailWebViewBinding


class ActsDetailWebViewFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentActsDetailWebViewBinding.inflate(inflater, container, false)
        val args = arguments?.let { ActsDetailWebViewFragmentArgs.fromBundle(it) }
        binding.webViewNActsDetails.loadUrl(args?.actDetailsURL)
        return binding.root
    }

}