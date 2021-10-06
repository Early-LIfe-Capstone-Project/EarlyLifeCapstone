/**
 * This class represents the Quilt Details Fragment which is accessed by selecting the corresponding tab
 * in the Bottom Navigation tab. It displays the quilt activities, which can be clicked on to access
 * more details about an activity displayed on the ActivityDetailsPage, and a short user manual for
 * the app.
 *
 */

package com.example.earlylife.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.earlylife.R
import android.widget.ArrayAdapter
import com.example.earlylife.ActivityDetailsPage
import com.example.earlylife.MainActivity

const val EXTRA_MESSAGE = "com.example.earlylife.ACTIVITY"

class QuiltDetailsFragment : Fragment() {

    override fun onCreateView(
        /**
         * Creates the fragment view and also allows the user to switch to the activity details page
         * when an activity is selected.
         */
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_quilt_details, container, false)

        val activityNames: List<String> = (activity as MainActivity).activityList.getActivityNames()
        val listView = view.findViewById(R.id.activity_info_list) as ListView
        val adapter: ArrayAdapter<String> = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, activityNames)
        listView.adapter = adapter

        listView.setOnItemClickListener{parent, v, position, id ->
            val message = (activity as MainActivity).activityList.getActivity(activityNames[position]).activityAsArray()
            val intent = Intent(view.context, ActivityDetailsPage::class.java).apply{putExtra(
                EXTRA_MESSAGE, message)}
            startActivity(intent)
        }

        // Inflate the layout for this fragment
        return view
    }

}