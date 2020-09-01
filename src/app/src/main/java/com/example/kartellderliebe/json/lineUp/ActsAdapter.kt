package com.example.kartellderliebe.json.lineUp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kartellderliebe.R
import com.example.kartellderliebe.rss.`interface`.ItemClickListener
import com.example.kartellderliebe.ui.FestivalNewsFragmentDirections
import java.util.*

class ActsAdapter(
    var jsonObject: JSONActsObject,
    var mContext: Context,
    var inflater: LayoutInflater? = LayoutInflater.from(mContext)
) : RecyclerView.Adapter<ActsHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActsHolder {
        val itemView = inflater?.inflate(R.layout.actssingleelement, parent, false)
        return itemView?.let { ActsHolder(it) }!!
    }

    override fun getItemCount(): Int {
        return jsonObject.acts.size
    }

    override fun onBindViewHolder(
        holder: ActsHolder,
        position: Int
    ) {
        when (position %5) {
            0 -> { holder.actsImageView.background = getDrawable(mContext, R.drawable.line_up_bluemchen)}
            1 -> { holder.actsImageView.background = getDrawable(mContext, R.drawable.line_up_britney)}
            2 -> { holder.actsImageView.background = getDrawable(mContext, R.drawable.line_up_diedoofen)}
            3 -> { holder.actsImageView.background = getDrawable(mContext, R.drawable.line_up_schluempfe)}
            else -> { holder.actsImageView.background = getDrawable(mContext, R.drawable.line_up_scooter)}
        }

        holder.actsTextView.text = jsonObject.acts[position].name
        holder.actsCalender.setOnClickListener {
            val startMillis: Long = Calendar.getInstance().run {
            set(jsonObject.year.toInt(), 9, 3, jsonObject.acts[position].time.toInt(), 30)
            timeInMillis
            }
            val endMillis: Long = Calendar.getInstance().run {
                set(2012, 0, 19, 8, 30)
                timeInMillis
            }
            val intent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
                .putExtra(CalendarContract.Events.TITLE, "Yoga")
                .putExtra(CalendarContract.Events.DESCRIPTION, "Group class")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, jsonObject.acts[position].stage)
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.ALLOWED_AVAILABILITY)
                .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com")
            startActivity(mContext,intent, Bundle.EMPTY)
        }
        holder.itemClickListener = object : ItemClickListener {
            override fun onClick(view: View, position: Int, isLongClick: Boolean) {
                if(!isLongClick){
                    view.findNavController().navigate(FestivalNewsFragmentDirections.actionFestivalNewsFragmentToNewsFeedDetailFragment(jsonObject.link))
                }
            }
        }
    }


}