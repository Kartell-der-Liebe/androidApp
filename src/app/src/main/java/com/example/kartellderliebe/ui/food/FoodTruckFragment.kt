package com.example.kartellderliebe.ui.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kartellderliebe.databinding.FragmentFoodBinding
import com.example.kartellderliebe.getJsonDataFromAsset
import com.example.kartellderliebe.json.food.FoodTruckAdapter
import com.example.kartellderliebe.json.food.JSONFoodObject
import com.google.gson.Gson

class FoodTruckFragment : Fragment() {

    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFoodBinding.inflate(inflater, container, false)
        gridLayoutManager = GridLayoutManager(context, 1)
        val jsonObject = Gson().fromJson(
            context?.let { getJsonDataFromAsset(it, "food.json") },
            JSONFoodObject::class.java
        )
        val recyclerViewActs = binding.recyclerViewFoodTruck
        recyclerViewActs.layoutManager = gridLayoutManager
        val adapter = context?.let { FoodTruckAdapter(jsonObject, it) }
        recyclerViewActs.adapter = adapter
        adapter?.notifyDataSetChanged()



        return binding.root
    }
}