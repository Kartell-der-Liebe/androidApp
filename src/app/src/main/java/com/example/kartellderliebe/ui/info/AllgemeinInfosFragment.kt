package com.example.kartellderliebe.ui.info

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment

import com.example.kartellderliebe.databinding.FragmentAllgemeinInfosBinding

class AllgemeinInfosFragment : Fragment() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val html = "<iframe src=\"https://google.com&output=embed\" style=\"border:solid 1px #777\" width=\"400\" height=\"500\" frameborder=\"0\" scrolling=\"no\"></iframe>"
        // Inflate the layout for this fragment
        val binding = FragmentAllgemeinInfosBinding.inflate(inflater, container, false)
        val webView = binding.webView
        val contentView = binding.contentView
        contentView.movementMethod = ScrollingMovementMethod()

        class MyJavaScriptInterface(aContentView: TextView) {
            private var contentView1: TextView? = null

            init {
                contentView1 = aContentView
            }

            @JavascriptInterface
            fun processContent(aContent: String) {
                contentView1?.post { contentView.text = aContent }
            }
        }

        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(MyJavaScriptInterface(contentView), "INTERFACE")
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                view.loadUrl("javascript:window.INTERFACE.processContent(document.getElementById(\"tab-5eef502ca56e3-1\").textContent)")
            }
        }

        webView.loadUrl("https://eineliebe.de/infos/")

        /*webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show()
            }

            @TargetApi(Build.VERSION_CODES.M)
            override fun onReceivedError(
                view: WebView,
                req: WebResourceRequest,
                rerr: WebResourceError
            ) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(
                    view,
                    rerr.errorCode,
                    rerr.description.toString(),
                    req.url.toString()
                )
            }
        }
        webView.loadUrl("https://eineliebe.de/infos/")

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.builtInZoomControls = true
        webSettings.pluginState = WebSettings.PluginState.ON
        webView.loadData("<iframe src=\"http://www.google.com\"></iframe>", "text/html",
            "utf-8")*/


        return binding.root
    }
}