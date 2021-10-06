package com.example.earlylife.fragments

import android.app.AlertDialog
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.earlylife.ActivityList
import com.example.earlylife.AppDataHandler
import com.example.earlylife.R
import com.example.earlylife.model.Quilt
import com.example.earlylife.retrofit.RetrofitService
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import android.content.DialogInterface
import android.graphics.Color
import com.example.earlylife.WeeklyReport


class HomeFragment : Fragment() {

    lateinit var barList: ArrayList<BarEntry>
    lateinit var barDataSet: BarDataSet
    lateinit var barData: BarData
    lateinit var barChart: HorizontalBarChart
    lateinit var syncButton: Button
    lateinit var activityList : ActivityList
    var downloadPermission : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        barChart = view.findViewById<HorizontalBarChart>(R.id.usage_report_chart)

        syncButton = view.findViewById<Button>(R.id.sync_quilt_button)

        syncButton.setOnClickListener(){
            getData()
        }

        barList = ArrayList()
        barList.add(BarEntry(1f, activityList.activityList[0].usage.weeklyUsage))
        barList.add(BarEntry(2f, activityList.activityList[1].usage.weeklyUsage))
        barList.add(BarEntry(3f, activityList.activityList[2].usage.weeklyUsage))
        barList.add(BarEntry(4f, activityList.activityList[3].usage.weeklyUsage))

        barDataSet = BarDataSet(barList, "")
        //remove legend and description
        barChart.description.isEnabled = false
        barChart.legend.isEnabled = false

        //setting colors of individual bars
        barDataSet.setColors(
            ContextCompat.getColor(barChart.context, R.color.graph_blue),
            ContextCompat.getColor(barChart.context, R.color.graph_green),
            ContextCompat.getColor(barChart.context, R.color.graph_orange),
            ContextCompat.getColor(barChart.context, R.color.graph_red),
        )

        //set bar shadows
        barChart.setDrawBarShadow(true)
        barDataSet.barShadowColor = Color.argb(40, 150, 150, 150)
        

        barData = BarData(barDataSet)
        barData.barWidth = 0.9f //set bar width
        barChart.data = barData

        //remove gridlines
        barChart.xAxis.isEnabled = false
        barChart.axisLeft.isEnabled = false
        barChart.axisRight.isEnabled = false

        //setting background color of chart to white
        barChart.setBackgroundColor(Color.WHITE)

        //removing pinch zoom feature
        barChart.setScaleEnabled(false)
        barChart.setPinchZoom(false)

        //axis limit of 3000 minutes of game usage
        barChart.axisLeft.axisMaximum = 24f
        barChart.axisLeft.axisMinimum = 0f

        //refresh graph
        barChart.invalidate()
        }

    private fun getData () {
        downloadPermission = getPermission()

        if (downloadPermission) {
            val connected = true
            if (connected) {
                val compositeDisposable = CompositeDisposable()
                compositeDisposable.add(
                    RetrofitService.ServiceBuilder.buildService().getShapes()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
                )
            }
        }

    }

    private fun onFailure(t: Throwable) {
        //Toast.makeText(this,t.message, Toast.LENGTH_SHORT).show()
        Log.d(ContentValues.TAG, "onFailure: " + t.message)

    }

    private fun onResponse(response: Quilt) {

        activityList.update(response)
        barChart.invalidate()

        barList.clear()

        barList.add(BarEntry(1f, activityList.activityList[0].usage.weeklyUsage))
        barList.add(BarEntry(2f, activityList.activityList[1].usage.weeklyUsage))
        barList.add(BarEntry(3f, activityList.activityList[2].usage.weeklyUsage))
        barList.add(BarEntry(4f, activityList.activityList[3].usage.weeklyUsage))


    }

    private fun getPermission() : Boolean{
            val dialogClickListener =
                DialogInterface.OnClickListener { _, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            downloadPermission = true
                        }
                        DialogInterface.BUTTON_NEGATIVE -> {
                            downloadPermission = false
                        }
                    }
                }

            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
            builder.setMessage("THIS WILL USE YOUR MOBILE DATA! \n YOU MUST BE CONNECTED TO THE QUILT BEFORE PRESSING YES!\n DO YOU GIVE PERMISSION TO SYNC THE QUILT DATA?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show()

        return downloadPermission
    }

    private fun isConnected() : Boolean{
        return true
    }







}