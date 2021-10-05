package com.example.earlylife.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.earlylife.R
import android.widget.ArrayAdapter
import com.example.earlylife.MainActivity

class QuiltDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_quilt_details, container, false)

        val activityNames: List<String> = (activity as MainActivity).activityList.getActivityNames()
        val listView = view.findViewById(R.id.activity_info_list) as ListView
        val adapter: ArrayAdapter<String> = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, activityNames)
        listView.adapter = adapter
        // Inflate the layout for this fragment
        return view
    }

    

}