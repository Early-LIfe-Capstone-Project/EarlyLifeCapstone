/**
 * This class is used to persist user data once the app closes and ensure that
 * the app works even when it is not connected to the quilt or the internet.
 * It uses a blank file with no usage statistics only quilt activity details
 * to create the activity list if the user has never pulled from the quilt API.
 * Once the user pulls from the API a new storage file will be created on the
 * internal storage of the users phone.
 *
 * @property FILE_NAME the name of the file that is used to read data from and save data to
 * @constructor there is one primary constructor which takes in the context of the calling activity
 * so that the files can be accessed
 */

package com.example.earlylife

import android.content.Context
import com.example.earlylife.fragments.HomeFragment
import java.io.*

private const val FILE_NAME = "activityData.txt"

class AppDataHandler(private val context: MainActivity){

    fun saveActivityData (activityList: ActivityList){
        /**
         * This function saves the activity data by writing it to a file in the internal storage
         * of the phone with the specified file name. The data is written in a predefined way to
         * make reading in the data easier. The data is separated by commas and the tips are written
         * last so that the data can be read in with an unknown number of tips
         *
         * @param activityList the activity list being written to the file after it has been updated
         * @exception e file cannot be created or written to
         */
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
            fileContents = "$fileContents$line\n"
        }

        val fileOutputStream: FileOutputStream

        try {
            fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
            fileOutputStream.write(fileContents.toByteArray())
            fileOutputStream.close()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun loadActivityData(): ActivityList{
        /**
         * This function loads data from a text file to create the ActivityList which will be used
         * by the MainActivity. If data has never been pulled from the quilt, the default text file
         * is used which is located in the assets folder. Else, the data will be pulled from the
         * text file located in the internal storage.
         * The data is read in by splitting each line by the comma delimiter as predefined.
         * The data is used to create Activity object which are stored in a list and used to
         * create an ActivityList object.
         *
         * @return an ActivityList created from the text file
         * @catches FileIOException if the internal storage file has not been created, in which case
         * the default text file is used.
         */
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