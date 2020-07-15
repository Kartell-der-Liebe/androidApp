package com.example.kartellderliebe.json.lineUp

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kartellderliebe.R
import com.example.kartellderliebe.getJsonDataFromAsset
import com.example.kartellderliebe.rss.`interface`.ItemClickListener
import com.example.kartellderliebe.rss.model.RSSObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.actssingleelement.*

class ActsAdapter(var jsonObject: JSONObject, var mContext : Context, var inflater: LayoutInflater? = LayoutInflater.from(mContext)) : RecyclerView.Adapter<ActsHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActsHolder {
        val itemView = inflater?.inflate(R.layout.actssingleelement, parent, false)
        return itemView?.let { ActsHolder(it) }!!
    }

    override fun getItemCount(): Int {
        return jsonObject.acts.size
    }

    override fun onBindViewHolder(
        holder: ActsHolder,
        position: Int
    ) {
        holder.actsTextView.text = jsonObject.acts[position].name
        holder.actsTextView.background = ContextCompat.getDrawable(mContext, R.drawable.ic_launcher_background)
    }


}