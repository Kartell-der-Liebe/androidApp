package com.example.kartellderliebe.rss.`interface`

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kartellderliebe.R
import com.example.kartellderliebe.rss.model.RSSObject


class FeedAdapter(var rssObject: RSSObject, var mContext : Context, var inflater: LayoutInflater? = LayoutInflater.from(mContext)) : RecyclerView.Adapter<FeedViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val itemView = inflater?.inflate(R.layout.row, parent, false)
        return itemView?.let { FeedViewHolder(it) }!!
    }

    override fun getItemCount(): Int {
        return rssObject.items.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.txtTitle.text = rssObject.items[position].title
        holder.txtPubDate.text = rssObject.items[position].pubDate
        holder.txtContent.text = rssObject.items[position].content

        holder.itemClickListener = object : ItemClickListener {
            override fun onClick(view: View, position: Int, isLongClick: Boolean) {
                if(!isLongClick){
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri. parse(rssObject.items[position].link))
                    mContext.startActivity(browserIntent)
                }
            }
        }
    }
}