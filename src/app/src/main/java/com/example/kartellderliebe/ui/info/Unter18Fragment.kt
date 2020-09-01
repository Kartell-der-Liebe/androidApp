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
import com.example.kartellderliebe.databinding.FragmentUnter18Binding

class Unter18Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUnter18Binding.inflate(inflater, container, false)
        val expTv1 = binding.expandTextView1
        expTv1.setAttr(R.id.expandable_text1, R.id.expand_collapse1)
        expTv1.text = getString(R.string.unter18Text)
        return binding.root
    }
}