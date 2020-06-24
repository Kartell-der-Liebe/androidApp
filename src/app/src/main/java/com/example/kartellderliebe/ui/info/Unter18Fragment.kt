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

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUnter18Binding.inflate(inflater, container, false)
        val webView = binding.webView
        val contentView = binding.textViewInfoUnter18
        contentView.movementMethod = ScrollingMovementMethod()

        class JavaScriptInterface(aContentView: TextView) {
            private var contentView1: TextView? = null

            init {
                contentView1 = aContentView
            }

            @JavascriptInterface
            fun processContent(aContent: String) {
                if(aContent != "") {
                    contentView1?.post { contentView.text = aContent }
                }
            }
        }

        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(JavaScriptInterface(contentView), "INTERFACE4")
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                view.loadUrl("javascript:window.INTERFACE4.processContent(document.getElementById('Content').getElementsByClassName('content_wrapper clearfix')[0].getElementsByClassName('sections_group')[0].getElementsByClassName('entry-content')[0].getElementsByClassName('section mcb-section mcb-section-mplxyfnn4')[0].getElementsByClassName('section_wrapper mcb-section-inner')[0].getElementsByClassName('wrap mcb-wrap mcb-wrap-gkw9kg93q one  valign-top clearfix')[0].getElementsByClassName('mcb-wrap-inner')[0].getElementsByClassName('column mcb-column mcb-item-s50p4zqqo one column_tabs')[0].getElementsByClassName('jq-tabs tabs_wrapper tabs_centered')[0].getElementsByTagName( 'div' )[3].innerText)")
            }
        }
        webView.loadUrl("https://eineliebe.de/infos/")

        return binding.root
    }
}