package com.example.kartellderliebe.ui.lineUp

import android.R
import android.graphics.Color
import android.graphics.Color.BLACK
import android.graphics.Color.WHITE
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kartellderliebe.databinding.FragmentTimeTableBinding
import com.example.kartellderliebe.getJsonDataFromAsset
import com.example.kartellderliebe.json.lineUp.JSONObject
import com.google.gson.Gson


class TimeTableFragment : Fragment() {

    lateinit var jsonObject : JSONObject
    lateinit var binding: FragmentTimeTableBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for context fragment
        binding = FragmentTimeTableBinding.inflate(inflater, container, false)
        jsonObject = Gson().fromJson(context?.let { getJsonDataFromAsset(it,"test.json") }, JSONObject::class.java)

        parseJSONContext()

        println(jsonObject.year)


        return binding.root
    }

    private fun parseJSONContext() {
        for (stages in jsonObject.stage){

            val table = TableLayout(context)

            table.isStretchAllColumns = true
            table.isShrinkAllColumns = true

            val rowTitle = TableRow(context)
            rowTitle.gravity = Gravity.CENTER_HORIZONTAL
            rowTitle.setBackgroundColor(BLACK)

            val params: TableRow.LayoutParams = TableRow.LayoutParams()
            params.span = stages.header.lastIndex + 1
            params.height = TableRow.LayoutParams.WRAP_CONTENT
            params.width = TableRow.LayoutParams.MATCH_PARENT

            for (header in stages.header){
                val headerText = TextView(context)
                headerText.text = header
                headerText.setTextColor(WHITE)
                headerText.textSize = 24.0F
                headerText.textAlignment = View.TEXT_ALIGNMENT_CENTER
                rowTitle.addView(headerText, params)
            }

            val rowAct = TableRow(context)
            rowAct.gravity = Gravity.CENTER_HORIZONTAL
            rowAct.setBackgroundColor(WHITE)

            for (act in stages.acts){
                val actText = TextView(context)
                actText.text = act.name
                actText.setTextColor(BLACK)
                actText.textSize = 16.0F
                actText.textAlignment = View.TEXT_ALIGNMENT_CENTER
                rowAct.addView(actText, params)
            }

            table.addView(rowTitle)
            table.addView(rowAct)

            binding.TimeTableLinearLayout.addView(table)
        }
    }
}