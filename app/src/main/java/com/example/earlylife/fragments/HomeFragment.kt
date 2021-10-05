package com.example.earlylife.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.earlylife.R
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class HomeFragment : Fragment() {

    lateinit var barList: ArrayList<BarEntry>
    lateinit var barDataSet: BarDataSet
    lateinit var barData: BarData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val barChart = view.findViewById<HorizontalBarChart>(R.id.usage_report_chart)

        barList = ArrayList()
        barList.add(BarEntry(1f, 500f))
        barList.add(BarEntry(2f, 100f))
        barList.add(BarEntry(3f, 300f))
        barList.add(BarEntry(4f, 800f))

        barDataSet = BarDataSet(barList, "name")

        //setting colors of individual bars
        barDataSet.setColors(
            ContextCompat.getColor(barChart.context, R.color.graph_blue),
            ContextCompat.getColor(barChart.context, R.color.graph_green),
            ContextCompat.getColor(barChart.context, R.color.graph_orange),
            ContextCompat.getColor(barChart.context, R.color.graph_red),
        )

        barData = BarData(barDataSet)
        barChart.data = barData

        //remove gridlines
        barChart.xAxis.isEnabled = false
        barChart.axisLeft.isEnabled = false
        barChart.axisRight.isEnabled = false
        barChart.description.isEnabled = false

        //barDataSet.valueTextColor = Color.BLACK
        //barDataSet.valueTextSize = 15f

        //refresh graph
        barChart.invalidate()

    }




}