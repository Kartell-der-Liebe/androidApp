package com.example.kartellderliebe.ui.info

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import com.example.kartellderliebe.R
import com.example.kartellderliebe.databinding.FragmentAllgemeinInfosBinding
import com.example.kartellderliebe.databinding.FragmentAnreiseBinding

class AnreiseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAnreiseBinding.inflate(inflater, container, false)
        val expTv = binding.expandTextView
        expTv.text = getString(R.string.anreiseInformationenText1)
        return binding.root
    }
}