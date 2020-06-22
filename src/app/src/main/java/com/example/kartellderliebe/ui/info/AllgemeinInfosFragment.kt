package com.example.kartellderliebe.ui.info

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.kartellderliebe.R

class AllgemeinInfosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_allgemein_infos, container, false)
    }
}