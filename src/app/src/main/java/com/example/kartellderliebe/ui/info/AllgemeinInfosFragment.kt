package com.example.kartellderliebe.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kartellderliebe.Investment
import com.example.kartellderliebe.ListViewAdapter
import com.example.kartellderliebe.databinding.FragmentAllgemeinInfosBinding


class AllgemeinInfosFragment : Fragment() {


    private val FLAG_HIDE_UNHIDE = 0
    private val totalAmount = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAllgemeinInfosBinding.inflate(inflater, container, false)

        val elvInvestments = binding.elvInvestments

        // Create the groups
        // Create the groups
        val lstGroups: MutableList<String> = ArrayList()
        lstGroups.add("Renda Variavél")
        lstGroups.add("Renda Fixa")
        lstGroups.add("Extrato")


        // Create items of each group


        // Create items of each group
        val lstRendaVariavel: MutableList<Investment> = ArrayList()
        lstRendaVariavel.add(Investment("ITSA4", 100, 1000000.00))
        lstRendaVariavel.add(Investment("KLBN11", 500, 5673.33))
        lstRendaVariavel.add(Investment("PETR4", 1000, 10325.26))

        val lstRendaFixa: MutableList<Investment> = ArrayList()
        lstRendaFixa.add(Investment("CDB NBC", 0, 5000.00))
        lstRendaFixa.add(Investment("CDB Modal Pré-Fixado", 0, 2500.00))
        lstRendaFixa.add(Investment("LCA Indusval", 0, 4000.00))

        val lstExtrato: MutableList<Investment> = ArrayList()
        lstExtrato.add(Investment("Transferência AG000/CC0000-0", 0, 2500.00))

        // Create the relationship of groups and your items

        // Create the relationship of groups and your items
        val lstItemsGroup: HashMap<String, List<Investment>> = HashMap()
        lstItemsGroup[lstGroups[0]] = lstRendaVariavel
        lstItemsGroup[lstGroups[1]] = lstRendaFixa
        lstItemsGroup[lstGroups[2]] = lstExtrato

        // Create an adapter (BaseExpandableListAdapter) with the data above

        // Create an adapter (BaseExpandableListAdapter) with the data above
        val listViewAdapter = ListViewAdapter(requireContext(), lstGroups, lstItemsGroup)
        // defines the ExpandableListView adapter
        // defines the ExpandableListView adapter
        elvInvestments.setAdapter(listViewAdapter)
        return binding.root
    }
}