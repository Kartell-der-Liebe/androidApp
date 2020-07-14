package com.example.kartellderliebe.ui.lineUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kartellderliebe.R
import com.example.kartellderliebe.databinding.FragmentActsBinding
import com.example.kartellderliebe.getJsonDataFromAsset
import com.example.kartellderliebe.json.lineUp.ActsAdapter
import com.example.kartellderliebe.json.lineUp.JSONObject
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_acts.*

class ActsFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentActsBinding.inflate(inflater, container, false)
        linearLayoutManager = LinearLayoutManager(context)
        val jsonObject = Gson().fromJson(context?.let { getJsonDataFromAsset(it,"test.json") }, JSONObject::class.java)
        val recyclerViewActs = binding.recyclerViewActs
        recyclerViewActs.layoutManager = linearLayoutManager
        val adapter = context?.let { ActsAdapter(jsonObject, it) }
        recyclerViewActs.adapter = adapter
        adapter?.notifyDataSetChanged()



        return binding.root
    }
}