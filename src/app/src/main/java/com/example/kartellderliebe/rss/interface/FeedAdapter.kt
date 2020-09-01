package com.example.kartellderliebe.rss.`interface`

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kartellderliebe.R
import com.example.kartellderliebe.rss.model.RSSObject
import com.example.kartellderliebe.ui.FestivalNewsFragmentDirections


class FeedAdapter(var rssObject: RSSObject, var mContext : Context, var inflater: LayoutInflater? = LayoutInflater.from(mContext)) : RecyclerView.Adapter<FeedViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val itemView = inflater?.inflate(R.layout.rssrow, parent, false)
        return itemView?.let { FeedViewHolder(it) }!!
    }

    override fun getItemCount(): Int {
        return rssObject.items.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val startChar = "<p>"
        val endChar = "</p>"
        holder.txtTitle.text = rssObject.items[position].title
        holder.txtPubDate.text = rssObject.items[position].pubDate
        val startDescription = rssObject.items[position].description.indexOf(startChar, 0)
        val endDescription = rssObject.items[position].description.indexOf(endChar, 0)
        holder.txtContent.text = rssObject.items[position].description.subSequence(startDescription + startChar.length, endDescription)

        holder.itemClickListener = object : ItemClickListener {
            override fun onClick(view: View, position: Int, isLongClick: Boolean) {
                if(!isLongClick){
                    view.findNavController().navigate(FestivalNewsFragmentDirections.actionFestivalNewsFragmentToNewsFeedDetailFragment(rssObject.items[position].link))

                }
            }
        }
    }
}