package com.example.kartellderliebe.json.lineUp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
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
        return jsonObject.stage[0].acts.size
    }

    override fun onBindViewHolder(
        holder: ActsHolder,
        position: Int
    ) {
        Picasso.get().load(getJsonDataFromAsset(mContext, jsonObject.stage[0].acts[position].image)).into(holder.actImage)
        holder.actsDate.text = jsonObject.stage[0].acts[position].time
        holder.actsDescription.text = jsonObject.stage[0].acts[position].name
    }


}

//1
class ActsHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {
    //2
    var actImage : ImageView = itemView.findViewById(R.id.itemImage)
    var actsDate : TextView = itemView.findViewById(R.id.itemDate)
    var actsDescription : TextView = itemView.findViewById(R.id.itemDescription)
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