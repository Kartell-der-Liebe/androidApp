package com.example.kartellderliebe.rss.`interface`

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kartellderliebe.R

class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener{

    var txtTitle : TextView = itemView.findViewById(R.id.txtTitle)
    var txtPubDate : TextView = itemView.findViewById(R.id.txtPubDate)
    var txtContent : TextView = itemView.findViewById(R.id.txtContent)
    lateinit var itemClickListener: ItemClickListener

    init {
        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            itemClickListener.onClick(v, adapterPosition, false)
        }
    }

    override fun onLongClick(v: View?): Boolean {
        if (v != null) {
            itemClickListener.onClick(v, adapterPosition, true)
        }
        return true
    }

}