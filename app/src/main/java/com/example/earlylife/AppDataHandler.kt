package com.example.earlylife

import android.content.Context
import java.io.*

private const val FILE_NAME = "activityData.txt"

class AppDataHandler constructor (private val context: Context){

    fun saveActivityData (activityList: ActivityList){
        var line: String
        var fileContents = ""

        for (activity in activityList.activityList){
            line = activity.activityName + "," +
                    activity.activityDetails + "," +
                    activity.ecdDetails + "," +
                    activity.usage
            for (tip in activity.tips){
                line = "$line,$tip"
            }
            fileContents = "$fileContents$line/n"
        }

        val fileOutputStream: FileOutputStream

        try {
            fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
            fileOutputStream.write(fileContents.toByteArray())
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun loadActivityData(): ActivityList{
        try {
            val inputStream = context.openFileInput(FILE_NAME)
            val activityList = mutableListOf<Activity>()

            inputStream.bufferedReader().forEachLine {
                val activityData = it.split(",")
                val activity = Activity(activityData[0], activityData[1], activityData[2],
                    activityData.subList(5, activityData.lastIndex),
                    UsageStatistics(activityData[3].toFloat(), activityData[4].toFloat())
                )
                activityList.add(activity)
            }

            return ActivityList(activityList)

        } catch (ex: FileNotFoundException) {
                val inputStream = context.assets.open(FILE_NAME)
                val activityList = mutableListOf<Activity>()

                inputStream.bufferedReader().forEachLine {
                    val activityData = it.split(",")
                    val activity = Activity(activityData[0], activityData[1], activityData[2],
                        activityData.subList(3, activityData.lastIndex), UsageStatistics())
                    activityList.add(activity)
                }

                return ActivityList(activityList)

        }
    }
}