package com.example.kartellderliebe

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kartellderliebe.databinding.FragmentAllgemeinInfosBinding


class AllgemeinInfosFragment : Fragment() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val html = "<iframe src=\"https://calendar.google.com/calendar/embed?height=600&amp;wkst=1&amp;bgcolor=%23ffffff&amp;ctz=Europe%2FBerlin&amp;src=YmpvZXJuMi5wZXRlcnNlbkBnbWFpbC5jb20&amp;src=YWRkcmVzc2Jvb2sjY29udGFjdHNAZ3JvdXAudi5jYWxlbmRhci5nb29nbGUuY29t&amp;src=ZGUuZ2VybWFuI2hvbGlkYXlAZ3JvdXAudi5jYWxlbmRhci5nb29nbGUuY29t&amp;src=bjlwN3NuMGtmNGhtN2dmYmVubzZxazJ2MHRlMG00YnRAaW1wb3J0LmNhbGVuZGFyLmdvb2dsZS5jb20&amp;src=bWk5amowczdmY2prOTNnZnU4dGN1MXMyaGxmYnMzbGVAaW1wb3J0LmNhbGVuZGFyLmdvb2dsZS5jb20&amp;src=bW82bjBucmI1ZXU5aW0wZDI2YmpibnQ4a2Z2YWU1YnNAaW1wb3J0LmNhbGVuZGFyLmdvb2dsZS5jb20&amp;src=Y3NuMzI0OG9tM3RkMGM3b2locWVkajM5ZTA4YjE1MmFAaW1wb3J0LmNhbGVuZGFyLmdvb2dsZS5jb20&amp;src=YWRrOWhrczBkbGY1dmtzcGIzc3Npbmxpdmc0c2FidW5AaW1wb3J0LmNhbGVuZGFyLmdvb2dsZS5jb20&amp;color=%23EF6C00&amp;color=%233F51B5&amp;color=%23009688&amp;color=%23C0CA33&amp;color=%23AD1457&amp;color=%23D81B60&amp;color=%239E69AF&amp;color=%23AD1457\" style=\"border:solid 1px #777\" width=\"400\" height=\"500\" frameborder=\"0\" scrolling=\"no\"></iframe>"
        // Inflate the layout for this fragment
        val binding = FragmentAllgemeinInfosBinding.inflate(inflater, container, false)
        val webView = binding.WebviewInfosAllgemein
        /*webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        binding.WebviewInfosAllgemein.loadData(html, "text/html", null)*/

        webView.webViewClient = object : WebViewClient() {
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

        return binding.root
    }
}