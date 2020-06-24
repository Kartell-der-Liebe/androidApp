package com.example.kartellderliebe.ui

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kartellderliebe.MainActivity
import com.example.kartellderliebe.R
import com.example.kartellderliebe.databinding.FragmentFestivalNewsBinding
import com.example.kartellderliebe.rss.`interface`.FeedAdapter
import com.example.kartellderliebe.rss.common.HTTPDataHandler
import com.example.kartellderliebe.rss.model.Feed
import com.example.kartellderliebe.rss.model.RSSObject
import com.google.gson.Gson

class FestivalNewsFragment : Fragment() {

    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var recyclerView: RecyclerView
    lateinit var rssObject: RSSObject

    val RSS_LINK : String = "https://eineliebe.de/feed/"
    val RSS2JSONAPI : String = " https://api.rss2json.com/v1/api.json?rss_url="
    lateinit var binding: FragmentFestivalNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFestivalNewsBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        toolbar = binding.toolbar
        recyclerView = binding.recyclerView
        toolbar.title = "News"

        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager

        loadRSS()


        return binding.root
    }

    private fun loadRSS() {
        val loadRSSAsync = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<String, String, String>(){

            override fun doInBackground(vararg params: String?): String {
                val result : String
                val http = HTTPDataHandler()
                result = params[0]?.let { http.getHTTPData(it) }.toString()
                println(result + "Hello")
                return result
            }

            override fun onPostExecute(result: String?) {
                if (!result.equals("")) {
                    rssObject = Gson().fromJson(result, RSSObject::class.java)
                    val adapter = context?.let { FeedAdapter(rssObject, it) }
                    recyclerView.adapter = adapter
                    adapter?.notifyDataSetChanged()
                    binding.newsConnectionFailedTextView.text = ""
                }
            }
        }
        val url_get_data = StringBuilder(RSS2JSONAPI)
        url_get_data.append(RSS_LINK)
        loadRSSAsync.execute(url_get_data.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
}