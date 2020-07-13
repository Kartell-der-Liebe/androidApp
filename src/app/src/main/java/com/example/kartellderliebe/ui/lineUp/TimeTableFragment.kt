package com.example.kartellderliebe.ui.lineUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kartellderliebe.databinding.FragmentTimeTableBinding
import com.example.kartellderliebe.getJsonDataFromAsset
import com.example.kartellderliebe.json.lineUp.JSONObject
import com.google.gson.Gson
import java.io.FileReader

class TimeTableFragment : Fragment() {

    lateinit var jsonObject : JSONObject

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentTimeTableBinding.inflate(inflater, container, false)
        jsonObject = Gson().fromJson(context?.let { getJsonDataFromAsset(it,"test.json") }, JSONObject::class.java)
        println(jsonObject.year)


        return binding.root
    }
}