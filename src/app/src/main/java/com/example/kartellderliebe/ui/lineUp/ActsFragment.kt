package com.example.kartellderliebe.ui.lineUp

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kartellderliebe.R
import com.example.kartellderliebe.databinding.FragmentActsBinding
import com.example.kartellderliebe.getJsonDataFromAsset
import com.example.kartellderliebe.json.lineUp.ActsAdapter
import com.example.kartellderliebe.json.lineUp.JSONObject
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_acts.*
import java.lang.reflect.Array.set
import java.util.*

class ActsFragment : Fragment() {

    private lateinit var gridLayoutManager: GridLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentActsBinding.inflate(inflater, container, false)
        gridLayoutManager = GridLayoutManager(context, 1)
        val jsonObject = Gson().fromJson(context?.let { getJsonDataFromAsset(it,"test.json") }, JSONObject::class.java)
        val recyclerViewActs = binding.recyclerViewActs
        recyclerViewActs.layoutManager = gridLayoutManager
        val adapter = context?.let { ActsAdapter(jsonObject, it) }
        recyclerViewActs.adapter = adapter
        adapter?.notifyDataSetChanged()

        return binding.root
    }
}