package com.example.kartellderliebe.json.food

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kartellderliebe.R
import com.example.kartellderliebe.json.lineUp.ActsHolder
import com.example.kartellderliebe.json.lineUp.JSONObject

class FoodTruckAdapter (var jsonObject: JSONObject, var mContext : Context, var inflater: LayoutInflater? = LayoutInflater.from(mContext)) : RecyclerView.Adapter<FoodTruckHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodTruckHolder {
        val itemView = inflater?.inflate(R.layout.actssingleelement, parent, false)
        return itemView?.let { FoodTruckHolder(it) }!!
    }

    override fun getItemCount(): Int {
        return jsonObject.stage[0].acts.size
    }

    override fun onBindViewHolder(
        holder: FoodTruckHolder,
        position: Int
    ) {
        holder.actsTextView.text = jsonObject.stage[0].acts[position].name
        holder.actsTextView.background = ContextCompat.getDrawable(mContext, R.drawable.ic_launcher_background)
    }

}