package com.example.kartellderliebe.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kartellderliebe.R
import com.example.kartellderliebe.databinding.FragmentAllgemeinInfosBinding
import com.example.kartellderliebe.databinding.FragmentAnreiseBinding


class AllgemeinInfosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAllgemeinInfosBinding.inflate(inflater, container, false)
        val expTv1 = binding.expandTextView1
        expTv1.setAttr(R.id.expandable_text1, R.id.expand_collapse1)
        expTv1.text = getString(R.string.allgemeineInformationenText1)
        val expTv2 = binding.expandTextView2
        expTv2.setAttr(R.id.expandable_text2, R.id.expand_collapse2)
        expTv2.text = getString(R.string.allgemeineInformationenText2)
        val expTv3 = binding.expandTextView3
        expTv3.setAttr(R.id.expandable_text3, R.id.expand_collapse3)
        expTv3.text = getString(R.string.allgemeineInformationenText3)
        return binding.root
    }
}