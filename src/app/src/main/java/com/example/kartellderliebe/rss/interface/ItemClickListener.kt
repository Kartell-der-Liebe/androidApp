package com.example.kartellderliebe.rss.`interface`

import android.view.View

interface ItemClickListener {
    fun onClick(view: View, position : Int, isLongClick : Boolean)
}