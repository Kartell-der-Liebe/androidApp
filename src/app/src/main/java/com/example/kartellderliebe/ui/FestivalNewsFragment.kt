package com.example.kartellderliebe.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.kartellderliebe.R
import com.example.kartellderliebe.databinding.FragmentFestivalNewsBinding
import com.example.kartellderliebe.rss.`interface`.FeedAdapter
import com.example.kartellderliebe.rss.common.HTTPDataHandler
import com.example.kartellderliebe.rss.model.RSSObject
import com.google.gson.Gson

class FestivalNewsFragment : Fragment(){

    lateinit var swipeToRefresh : SwipeRefreshLayout
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

        recyclerView = binding.recyclerView

        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager

        swipeToRefresh = binding.swiperefresh
        swipeToRefresh.setOnRefreshListener { loadRSS() }
        swipeToRefresh.setColorSchemeColors(Color.parseColor("#002c3c"), Color.parseColor("#fdea04"))

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
                return result
            }

            override fun onPostExecute(result: String?) {
                if (!result.equals("")) {
                    rssObject = Gson().fromJson(result, RSSObject::class.java)
                    val adapter = context?.let { FeedAdapter(rssObject, it) }
                    recyclerView.adapter = adapter
                    adapter?.notifyDataSetChanged()
                }
            }
        }
        val url_get_data = StringBuilder(RSS2JSONAPI)
        url_get_data.append(RSS_LINK)
        loadRSSAsync.execute(url_get_data.toString())
        swipeToRefresh.isRefreshing = false
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
}