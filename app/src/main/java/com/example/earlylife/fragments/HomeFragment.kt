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
import android.os.Handler
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

/**
 * This class represents the Home Page Fragment of the application and is where the weekly usage graph resides
 * as well as the logic for the connection to the arduino.
 *
 */
class HomeFragment : Fragment() {

    lateinit var barList: ArrayList<BarEntry>
    lateinit var barDataSet: BarDataSet
    lateinit var barData: BarData
    lateinit var barChart: HorizontalBarChart
    lateinit var syncButton: Button
    lateinit var activityList: ActivityList
    var connected: Boolean = false
    lateinit var appDataHandler : AppDataHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * On View Created the Bar Chart and Sync Button are created, The bar is populated with the data from the activityList stored locally on the
         * device
         * The method which checks the connection with the arduino every x seconds is triggered here as well
         */

        barChart = view.findViewById<HorizontalBarChart>(R.id.usage_report_chart)

        syncButton = view.findViewById<Button>(R.id.sync_quilt_button)

        syncButton.setOnClickListener() {
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
        //barChart.xAxis.isEnabled = false
        barChart.axisLeft.isEnabled = false
        barChart.axisRight.isEnabled = false

        //bar labels
        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
        barChart.xAxis.isEnabled = true
        barChart.xAxis.setDrawAxisLine(false)

        barChart.xAxis.setLabelCount(4)
        val axisLabels = arrayOf(
            activityList.activityList[0].activityName,
            activityList.activityList[1].activityName,
            activityList.activityList[2].activityName,
            activityList.activityList[3].activityName )
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(axisLabels)


        //setting background color of chart to white
        barChart.setBackgroundColor(Color.WHITE)

        //removing pinch zoom feature
        barChart.setScaleEnabled(false)
        barChart.setPinchZoom(false)

        //axis limit of 3000 minutes of game usage
        barChart.axisLeft.axisMaximum = 24f
        barChart.axisLeft.axisMinimum = 0f

        //refresh graph
        barChart.animateY(1800)

        val delay = 10000L
        RepeatHelper.repeatDelayed(delay) {
            isConnected()
        }


    }

    private fun getData() {
        /**
         * Creates a Popup Dialog. The Dialog warns the user that they will be using their mobile data
         * and storage to sync with the quilt and asks for permission
         * Once permission is granted - the Retrofit service is built and the arduino starts sending API calls to the device
         *
         */
        val dialogClickListener =
            DialogInterface.OnClickListener { _, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        val compositeDisposable = CompositeDisposable()
                        compositeDisposable.add(
                            RetrofitService.ServiceBuilder.buildService().getShapes()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(
                                    { response -> onResponse(response) },
                                    { t -> onFailure(t) })
                        )
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {

                    }
                }
            }

        val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
        builder.setMessage("THIS WILL USE YOUR MOBILE DATA! \n YOU MUST BE CONNECTED TO THE QUILT BEFORE PRESSING YES!\n DO YOU GIVE PERMISSION TO SYNC THE QUILT DATA?")
            .setPositiveButton("Yes", dialogClickListener)
            .setNegativeButton("No", dialogClickListener).show()
    }


    private fun onFailure(t: Throwable) {
        /**
         * When the API call fails for whatever reason this method is called
         * It logs the failure and the failure message
         * It shows a alert to the user
         * @param t throwable exception - thrown when failure occurs in API connection
         */

        Log.d(ContentValues.TAG, "onFailure: " + t.message)

        val builder = AlertDialog.Builder(this.context)
        builder.setMessage("You are not connected to the Quilt!")
            .setCancelable(true)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.cancel()
            }
        val alert = builder.create()
        alert.show()

    }

    private fun onResponse(response: Quilt) {
        /**
         * If the API call was successful this method updates the activityList with the new data from the Arduino
         * and updates the graph
         *
         * @param response : API message from the Arduino
         */

        activityList.update(response)

        appDataHandler.saveActivityData(activityList)

        //barChart.invalidate()
        barChart.animateY(1800)

        barList.clear()

        barList.add(BarEntry(1f, activityList.activityList[0].usage.weeklyUsage))
        barList.add(BarEntry(2f, activityList.activityList[1].usage.weeklyUsage))
        barList.add(BarEntry(3f, activityList.activityList[2].usage.weeklyUsage))
        barList.add(BarEntry(4f, activityList.activityList[3].usage.weeklyUsage))


    }

    private fun isConnected(){
        /**
         * Checks if the device is connected to the Arduino by checking if you can do a successful API call without storing or recieving the data
         *
         */
            val compositeDisposable = CompositeDisposable()
            compositeDisposable.add(
                RetrofitService.ServiceBuilder.buildService().getShapes()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ response -> emptyResponse(response) }, { t -> emptyFailure(t) })
            )
    }

    private fun emptyFailure(t: Throwable?) {
        /**
         * Cannot connect to the Arduino so change the connected global variable to false
         */
        connected = false
    }

    private fun emptyResponse(response: Quilt?) {
        /**
         * Can connect to the Arduino so change the connected global variable to true
         */
        connected = true
    }

    object RepeatHelper {
        /**
         * Helper object to allow the scheduling of the connection checking method
         */
        fun repeatDelayed(delay: Long, todo: () -> Unit) {
            val handler = Handler()
            handler.postDelayed(object : Runnable {
                override fun run() {
                    todo()
                    handler.postDelayed(this, delay)
                }
            }, delay)
        }
    }



}
