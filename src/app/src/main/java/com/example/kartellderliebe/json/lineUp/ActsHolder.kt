package com.example.kartellderliebe.json.lineUp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kartellderliebe.R
import com.example.kartellderliebe.rss.`interface`.ItemClickListener

//1
class ActsHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {
    //2
    var actsTextView : TextView = itemView.findViewById(R.id.itemDate)
    var actsCalender : ImageView = itemView.findViewById(R.id.calender)
    lateinit var itemClickListener: ItemClickListener

    //3
    init {
        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }

    //4
    override fun onClick(v: View) {
        itemClickListener.onClick(v, adapterPosition, false)
    }

    override fun onLongClick(v: View?): Boolean {
        if (v != null) {
            itemClickListener.onClick(v, adapterPosition, true)
        }
        return true
    }
}