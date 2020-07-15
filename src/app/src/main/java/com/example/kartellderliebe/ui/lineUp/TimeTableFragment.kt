package com.example.kartellderliebe.ui.lineUp

import android.R
import android.graphics.Color
import android.graphics.Color.BLACK
import android.graphics.Color.WHITE
import android.graphics.Typeface
import android.os.Bundle
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.marginEnd
import androidx.core.view.setPadding
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
        jsonObject.stage[0].acts.sortedBy { it.time }

        println(jsonObject.year)


        return binding.root
    }

    private fun parseJSONContext() {
        for (stages in jsonObject.stage){

            val stageName = TextView(context)
            stageName.textSize = 30f
            stageName.gravity = Gravity.CENTER_HORIZONTAL
            stageName.text = stages.name
            stageName.setPadding(0,50,0,50)
            binding.TimeTableLinearLayout.addView(stageName)

            val table = TableLayout(context)

            table.isStretchAllColumns = true
            table.isShrinkAllColumns = true

            val rowTitle = TableRow(context)
            rowTitle.gravity = Gravity.CENTER_HORIZONTAL
            rowTitle.setBackgroundColor(BLACK)


            val params: TableRow.LayoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT)
            params.span = stages.header.lastIndex - 1
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
            table.addView(rowTitle)

            for (x in stages.acts.indices step params.span + 1) {
                val rowAct = TableRow(context)
                rowAct.setPadding(0,0,0,30)
                rowAct.gravity = Gravity.CENTER_HORIZONTAL
                rowAct.setBackgroundColor(WHITE)

                val actTime = TextView(context)
                actTime.text = stages.time[x/params.span + 1]
                actTime.setTextColor(BLACK)
                actTime.textSize = 16.0F
                actTime.textAlignment = View.TEXT_ALIGNMENT_CENTER
                rowAct.addView(actTime, params)

                for (i in 0 until params.span + 1){
                    val actText = TextView(context)
                    actText.text = stages.acts[x + i].name
                    actText.setTextColor(BLACK)
                    actText.textSize = 16.0F
                    actText.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    rowAct.addView(actText)
                }

                table.addView(rowAct)
            }

            binding.TimeTableLinearLayout.addView(table)
        }
    }
}