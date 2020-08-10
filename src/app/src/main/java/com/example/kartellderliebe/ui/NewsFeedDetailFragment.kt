package com.example.kartellderliebe.ui

import android.R.attr.defaultValue
import android.R.attr.key
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kartellderliebe.databinding.FragmentNewsFeedDetailBinding


class NewsFeedDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentNewsFeedDetailBinding.inflate(inflater, container, false)
        val bundle = arguments
        binding.webViewNewsFeedDetail.loadUrl("http://google.com")
        val args =arguments?.let { NewsFeedDetailFragmentArgs.fromBundle(it) }
        binding.webViewNewsFeedDetail.loadUrl(args?.feedURL)

        return binding.root
    }
}