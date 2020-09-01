package com.example.kartellderliebe.json.food

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kartellderliebe.R

class FoodTruckAdapter(
    var jsonObject: JSONFoodObject,
    var mContext: Context,
    var inflater: LayoutInflater? = LayoutInflater.from(mContext)
) : RecyclerView.Adapter<FoodTruckHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodTruckHolder {
        val itemView = inflater?.inflate(R.layout.foodsingleelement, parent, false)
        return itemView?.let { FoodTruckHolder(it) }!!
    }

    override fun getItemCount(): Int {
        return jsonObject.food.size
    }

    override fun onBindViewHolder(
        holder: FoodTruckHolder,
        position: Int
    ) {
        holder.foodTextView.text = jsonObject.food[position].name
    }

}